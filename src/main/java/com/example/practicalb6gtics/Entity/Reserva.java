package com.example.practicalb6gtics.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "reservas")
@Getter
@Setter
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idreservas", nullable = false)
    private Integer idReserva;

    @NotNull
    @Column(name = "fechainicio", nullable = false)
    private LocalDate fechaInicio;

    @NotNull
    @Column(name = "fechafin", nullable = false)
    private LocalDate fechaFin;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idusuarios", nullable = false)
    private Usuario idusuarios;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "iddispositivos", nullable = false)
    private Dispositivo iddispositivos;


}
