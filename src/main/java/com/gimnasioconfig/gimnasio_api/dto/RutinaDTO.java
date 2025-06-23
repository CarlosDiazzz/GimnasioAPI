package com.gimnasioconfig.gimnasio_api.dto;

import com.gimnasioconfig.gimnasio_api.models.Rutina;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RutinaDTO {
    private Long id;
    private String descripcion;
    private LocalDate fecha;
    private ClienteDTO cliente;

    public static RutinaDTO fromEntity(Rutina rutina){
        if (rutina == null) return null;
        return RutinaDTO.builder()
                .id(rutina.getId())
                .descripcion(rutina.getDescripcion())
                .fecha(rutina.getFecha())
                .cliente(ClienteDTO.fromEntity(rutina.getCliente()))
                .build();
    }
}
