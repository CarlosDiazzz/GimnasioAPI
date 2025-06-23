package com.gimnasioconfig.gimnasio_api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Clase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String dia;//Lunes, Martes, Miercoles, etc...
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Integer cupoMaximo;

    @ManyToOne
    @JoinColumn(name = "entrenador_id")
    private Entrenador entrenador;

    @OneToMany(mappedBy = "clase")
    private List<InscripcionClase> inscripciones;

    @Column(name = "activo")
    private Boolean activo = true; // por defecto está activo
}
