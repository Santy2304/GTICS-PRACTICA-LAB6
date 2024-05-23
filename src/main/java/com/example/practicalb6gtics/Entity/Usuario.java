package com.example.practicalb6gtics.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuarios", nullable = false)
    private Integer idUsuario;

    @Size(max = 45)
    @NotNull
    @Column(name = "correo", nullable = false)
    private String correo;

    @Size(max = 45)
    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idrol", nullable = false)
    private Rol idrol;

    @Size(max = 45)
    @NotNull
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Size(max = 45)
    @NotNull
    @Column(name = "apellido", nullable = false)
    private String apellido;


    @NotNull
    @Column(name = "activo", nullable = false)
    private Byte activo;

}
