package com.techxperts.erp.cliente.dto;

import com.techxperts.erp.util.dto.DireccionFELDTO;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ClienteDTO {
    private Long id;
    private Long empresaId;
    private String nit;
    private String nombre;
    private String nombreFactura;
    private String direccion;
    private String direccionFactura;
    private String direccionEntrega;
    private String telefono;
    private String email;
    private String observaciones;
    private Long listaPrecioId;
    private boolean bloquearCredito; // solo contado
    private Integer limiteCredito; // 0 = sin limite
    private Integer diasCredito;
    private Integer descuento; // porcentaje max 40%
    private LocalDate fechaIngreso;
    private boolean activo;

    private DireccionFELDTO direccionFEL;
}