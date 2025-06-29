package com.techxperts.erp.auth.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private Long usuarioId;
    private String nombre;
    private Long empresaId;
    private String periodo;
}