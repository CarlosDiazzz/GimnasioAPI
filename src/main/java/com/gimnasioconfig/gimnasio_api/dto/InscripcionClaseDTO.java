package com.gimnasioconfig.gimnasio_api.dto;

import com.gimnasioconfig.gimnasio_api.models.InscripcionClase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InscripcionClaseDTO {
    private Long id;
    private LocalDate fecha;
    private ClaseDTO clase;
    private ClienteDTO cliente;

    public static InscripcionClaseDTO fromEntity(InscripcionClase inscripcionClase){
        if (inscripcionClase == null) return null;
        return InscripcionClaseDTO.builder()
                .id(inscripcionClase.getId())
                .fecha(inscripcionClase.getFecha())
                .clase(ClaseDTO.fromEntity(inscripcionClase.getClase()))
                .cliente(ClienteDTO.fromEntity(inscripcionClase.getCliente()))
                .build();
    }
}
