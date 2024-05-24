package com.example.practicalb6gtics.Config;

import com.example.practicalb6gtics.Repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;

import javax.sql.DataSource;

@Configuration
public class WebSecurityConfig {
    final DataSource dataSource;
    final UsuarioRepository usuarioRepository;

    public WebSecurityConfig(DataSource dataSource, UsuarioRepository usuarioRepository) {
        this.dataSource = dataSource;
        this.usuarioRepository = usuarioRepository;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.formLogin()

                .successHandler((request, response, authentication) -> {

                    HttpSession session = request.getSession();
                    session.setAttribute("usuario",usuarioRepository.findByCorreo(authentication.getName()));

                    String rol = "";
                    for(GrantedAuthority role : authentication.getAuthorities()){
                        rol = role.getAuthority();
                        break;
                    }

                    if(rol.equals("admin")){
                        response.sendRedirect("/admin/listaDispositivos");
                    }else{
                        if (rol.equals("alumno")){
                            response.sendRedirect("/alumno/listaDispositivos");

                        }else{
                            response.sendRedirect("/profesor/listaDispositivos");

                        }
                    }

                });

        http.logout()
                .logoutSuccessUrl("/login")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);


        http.authorizeHttpRequests()
                .requestMatchers("/admin", "/admin/**").hasAnyAuthority("admin")
                .requestMatchers("/alumno", "/alumno/**").hasAnyAuthority("alumno")
                .requestMatchers("/profesor", "/profesor/**").hasAnyAuthority("profesor")
                .anyRequest().permitAll();

        return http.build();
    }
    @Bean
    public UserDetailsManager users(DataSource dataSource) {
        JdbcUserDetailsManager jdbc = new JdbcUserDetailsManager(dataSource);

        String sql1 = "SELECT correo, password, activo FROM usuarios where correo = ?";
        String sql2 = "SELECT u.correo, r.autoridad FROM usuarios u INNER JOIN rol r ON (u.idrol = r.idrol) " +
                "WHERE u.correo = ?";

        jdbc.setUsersByUsernameQuery(sql1);
        jdbc.setAuthoritiesByUsernameQuery(sql2);
        return jdbc;
    }

}
