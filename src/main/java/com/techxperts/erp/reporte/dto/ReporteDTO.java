package com.techxperts.erp.reporte.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReporteDTO {
    private Long id;
    private Long empresaId;
    private String descripcion;
    private String nombreArchivo;
    private Boolean activo;
    private String observaciones;
}