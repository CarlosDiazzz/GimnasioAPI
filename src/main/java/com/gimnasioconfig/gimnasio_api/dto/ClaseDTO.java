package com.gimnasioconfig.gimnasio_api.dto;

import com.gimnasioconfig.gimnasio_api.models.Clase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClaseDTO {
    private Long id;
    private String nombre;
    private String dia;//Lunes, Martes, Miercoles, etc...
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Integer cupoMaximo;
    private EntrenadorDTO entrenador;
    private Boolean activo; // por defecto está activo

    public static ClaseDTO fromEntity(Clase clase){
        if (clase == null) return null;
        return ClaseDTO.builder()
                .id(clase.getId())
                .nombre(clase.getNombre())
                .dia(clase.getDia())
                .horaInicio(clase.getHoraInicio())
                .horaFin(clase.getHoraFin())
                .cupoMaximo(clase.getCupoMaximo())
                .entrenador(EntrenadorDTO.fromEntity(clase.getEntrenador()))
                .activo(clase.getActivo())
                .build();
    }
}
