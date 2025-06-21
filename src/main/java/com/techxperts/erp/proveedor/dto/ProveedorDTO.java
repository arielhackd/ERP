package com.techxperts.erp.proveedor.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProveedorDTO {

    private Long id;

    @NotNull(message = "El id de empresa es obligatorio")
    private Long empresaId;

    @NotBlank(message = "El NIT es obligatorio")
    @Size(max = 25, message = "El NIT no puede tener más de 25 caracteres")
    private String nit;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    private String nombre;

    @Size(max = 255, message = "La dirección no puede tener más de 255 caracteres")
    private String direccion;

    @Size(max = 20, message = "El teléfono no puede tener más de 20 caracteres")
    private String telefono;

    @Email(message = "El email debe ser válido")
    @Size(max = 100, message = "El email no puede tener más de 100 caracteres")
    private String email;

    @Size(max = 255, message = "Las observaciones no pueden tener más de 255 caracteres")
    private String observaciones;

    @Size(max = 100, message = "El contacto no puede tener más de 100 caracteres")
    private String contacto;

    @Size(max = 100, message = "El puesto del contacto no puede tener más de 100 caracteres")
    private String puestoContacto;

    @Size(max = 20, message = "El teléfono del contacto no puede tener más de 20 caracteres")
    private String telContacto;

    @Min(value = 0, message = "Los días de crédito no pueden ser negativos")
    private Integer diasCredito;

    @Min(value = 0, message = "El descuento no puede ser negativo")
    @Max(value = 40, message = "El descuento no puede ser mayor al 40%")
    private Integer descuento;

    private LocalDate fechaIngreso;

    private boolean activo;
}