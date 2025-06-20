package com.techxperts.erp.cliente.dto;

import com.techxperts.erp.util.dto.DireccionFELDTO;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ClienteDTO {
    private Long id;

    @NotNull(message = "La empresa es obligatoria")
    private Long empresaId;

    @NotBlank(message = "El NIT es obligatorio")
    @Size(max = 25)
    private String nit;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 150)
    private String nombre;

    @Size(max = 150)
    private String nombreFactura;

    @Size(max = 150)
    private String direccion;

    @Size(max = 150)
    private String direccionFactura;

    @Size(max = 150)
    private String direccionEntrega;

    @Size(max = 50)
    private String telefono;

    @Email(message = "Formato de email inválido")
    @Size(max = 100)
    private String email;

    @Size(max = 255)
    private String observaciones;
    private Long listaPrecioId;
    private boolean bloquearCredito; // solo contado

    @Min(value = 0)
    private Integer limiteCredito; // 0 = sin limite

    @Min(value = 0)
    @Max(value = 90)
    private Integer diasCredito;

    @Min(value = 0)
    @Max(value = 40, message = "El descuento no puede ser mayor al 40%")
    private Integer descuento; // porcentaje max 40%
    private LocalDate fechaIngreso;
    private boolean activo;

    private DireccionFELDTO direccionFEL;
}