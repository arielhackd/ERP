package com.techxperts.erp.auth.dto;

import lombok.*;

@Data
@Getter
@Setter
public class LoginRequest {
    private String username;
    private String password;
    private Long empresaId;
    private String periodo; // Ejemplo: "2025-06"
}