package com.example.practicalb6gtics.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "rol")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idrol", nullable = false)
    private Integer id;

    @Size(max = 45)
    @NotNull
    @Column(name = "autoridad", nullable = false)
    private String autoridad;


}
