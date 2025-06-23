package com.gimnasioconfig.gimnasio_api.dto;

import com.gimnasioconfig.gimnasio_api.models.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {
    private Long id;
    private String username;
    private String password;
    private String rol;
    private boolean activo;

    public static UsuarioDTO fromEntity(Usuario usuario){
        if(usuario == null) return null;
        return UsuarioDTO.builder()
                .id(usuario.getId())
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .rol(usuario.getRol())
                .activo(usuario.getActivo())
                .build();
    }
}
