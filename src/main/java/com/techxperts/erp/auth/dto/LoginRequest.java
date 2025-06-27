package com.techxperts.erp.auth.dto;

import lombok.*;

@Data
public class LoginRequest {
    private String username;
    private String password;
}