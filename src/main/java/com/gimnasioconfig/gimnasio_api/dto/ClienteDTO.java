package com.gimnasioconfig.gimnasio_api.dto;

import com.gimnasioconfig.gimnasio_api.models.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String telefono;
    private LocalDate fechaRegistro;
    private UsuarioDTO usuario;
    private Boolean activo; // por defecto está activo

    public static ClienteDTO fromEntity(Cliente cliente){
        if (cliente == null) return null;
        return ClienteDTO.builder()
                .id(cliente.getId())
                .nombre(cliente.getNombre())
                .apellido(cliente.getApellido())
                .telefono(cliente.getTelefono())
                .fechaRegistro(cliente.getFechaRegistro())
                .usuario(UsuarioDTO.fromEntity(cliente.getUsuario()))
                .activo(cliente.getActivo())
                .build();
    }
}
