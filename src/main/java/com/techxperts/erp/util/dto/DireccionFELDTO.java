package com.techxperts.erp.util.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DireccionFELDTO {

    private Long id;

    @NotBlank(message = "El país es obligatorio")
    @Size(max = 100, message = "El país no debe exceder los 100 caracteres")
    private String pais;

    @NotBlank(message = "El departamento es obligatorio")
    @Size(max = 100, message = "El departamento no debe exceder los 100 caracteres")
    private String departamento;

    @NotBlank(message = "El municipio es obligatorio")
    @Size(max = 100, message = "El municipio no debe exceder los 100 caracteres")
    private String municipio;

    @NotBlank(message = "El código postal es obligatorio")
    @Size(max = 10, message = "El código postal no debe exceder los 10 caracteres")
    private String codigoPostal;
}
