package com.techxperts.erp.producto.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class EscalaDTO {
    private Long id;
    private int cantidadMinima;
    private int cantidadMaxima;
    private BigDecimal precioUnitario;
    private Long productoId;
    private Long empresaId;
}