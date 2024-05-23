package com.example.practicalb6gtics.Repository;

import com.example.practicalb6gtics.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

    Usuario findByCorreo(String correo);


}
