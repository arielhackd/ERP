package com.techxperts.erp.producto.dto;

import lombok.Data;

@Data
public class ProductoDTO {
    private Long id;
    private boolean activo;
    private String codigo;
    private String nombre;
    private String marca;
    private String clase1;
    private String clase2;
    private String clase3;
    private String medida;
    private String procedencia;
}