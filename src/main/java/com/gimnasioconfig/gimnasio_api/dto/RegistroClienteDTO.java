package com.gimnasioconfig.gimnasio_api.dto;

import lombok.Data;

@Data
public class RegistroClienteDTO {
    private String username;
    private String password;
    private String nombre;
    private String apellido;
    private String telefono;
}
