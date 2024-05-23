package com.example.practicalb6gtics.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "prestamos")
@Getter
@Setter
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idprestamos", nullable = false)
    private Integer idPrestamos;

    @NotNull
    @Column(name = "fechainicio", nullable = false)
    private LocalDate fechaInicio;

    @NotNull
    @Column(name = "fechafin", nullable = false)
    private LocalDate fechaFin;

    @NotNull
    @Column(name = "habilitado", nullable = false)
    private Byte habilitado;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idalumno", nullable = false)
    private Usuario idalumno;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idprofesor", nullable = false)
    private Usuario idprofesor;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "iddispositivos", nullable = false)
    private Dispositivo iddispositivos;

}
