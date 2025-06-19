package com.techxperts.erp.util.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DireccionFELDTO {

    private Long id;
    private String pais;
    private String departamento;
    private String municipio;
    private String codigoPostal;
}
