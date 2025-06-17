package com.techxperts.erp.producto.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ProductoDTO {
    private Long id;
    private boolean activo;
    private String codigo;
    private String nombre;
    private String descripcion2;
    private String descripcion3;
    private String observaciones;
    private String tipo;

    private BigDecimal costo;
    private BigDecimal precio;
    private BigDecimal descuentoMaximo;
    private BigDecimal precioOferta;
    private LocalDate fechaInicioOferta;
    private LocalDate fechaFinOferta;
    private boolean usaEscalas;
    private boolean usaSeries;

    private String empresaNombre;
    private String clase1Nombre;
    private String clase2Nombre;
    private String clase3Nombre;
    private String marcaNombre;
    private String medidaNombre;
    private String procedenciaNombre;

    private Long empresaId;
    private Long clase1Id;
    private Long clase2Id;
    private Long clase3Id;
    private Long marcaId;
    private Long medidaId;
    private Long procedenciaId;
}