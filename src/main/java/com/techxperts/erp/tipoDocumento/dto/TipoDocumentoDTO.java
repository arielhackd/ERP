package com.techxperts.erp.tipoDocumento.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoDocumentoDTO {

    private Long id;
    private Long empresaId;
    private String codigo;
    private String tipoDocumento;
    private String descripcion;
    private String serie;
    private String gestion;
    private Integer correlativo;
    private Long bodegaEntradaId;
    private Long bodegaSalidaId;
    private Integer items;
    private Long reporteId; // si manejás relación por id
    private String tipoImpresion;
    private Boolean predet;
    private Boolean documentoElectronico;
    private Boolean activo;
    private Boolean noAfecta;
    private Boolean noValida;
    private String observaciones;
}