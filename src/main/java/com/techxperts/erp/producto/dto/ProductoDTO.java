package com.techxperts.erp.producto.dto;

import lombok.Data;

@Data
public class ProductoDTO {
    private Long id;
    private boolean activo;
    private String codigo;
    private String nombre;

    private String clase1Nombre;
    private String clase2Nombre;
    private String clase3Nombre;
    private String marcaNombre;
    private String medidaNombre;
    private String procedenciaNombre;

    private Long clase1ID;
    private Long clase2ID;
    private Long clase3ID;
    private Long marcaID;
    private Long medidaID;
    private Long procedenciaID;
}