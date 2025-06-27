package com.techxperts.erp.usuario.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private Long id;
    private String username;
    private String nombre;
    private String rol;
    private Boolean activo;
    private Long empresaId;
}