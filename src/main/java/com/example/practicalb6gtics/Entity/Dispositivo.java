package com.example.practicalb6gtics.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "dispositivos")
@Getter
@Setter
public class Dispositivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddispositivos", nullable = false)
    private Integer idDispositivos;

    @NotBlank
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotNull
    @Positive
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @NotNull
    @Positive
    @Column(name = "disponible", nullable = false)
    private Integer disponible;

    @Column(name = "habilitado", nullable = false)
    private Boolean habilitado;

}
