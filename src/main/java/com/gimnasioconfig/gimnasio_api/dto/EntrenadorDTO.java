package com.gimnasioconfig.gimnasio_api.dto;

import com.gimnasioconfig.gimnasio_api.models.Entrenador;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntrenadorDTO {
    private Long id;
    private String nombre;
    private String especialidad;
    private UsuarioDTO usuario;
    private Boolean activo; // por defecto está activo

    public static EntrenadorDTO fromEntity(Entrenador entrenador){
        if (entrenador == null) return null;
        return EntrenadorDTO.builder()
                .id(entrenador.getId())
                .nombre(entrenador.getNombre())
                .especialidad(entrenador.getEspecialidad())
                .usuario(UsuarioDTO.fromEntity(entrenador.getUsuario()))
                .activo(entrenador.getActivo())
                .build();
    }
}

