package com.techxperts.erp.tipoDocumento.model;

import com.techxperts.erp.bodega.model.Bodega;
import com.techxperts.erp.empresa.model.Empresa;
import com.techxperts.erp.reporte.model.Reporte;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tipo_documento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoDocumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con Empresa
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    private String codigo;
    private String tipoDocumento;
    private String descripcion;
    private String serie;
    private String gestion; // "SUMA", "RESTA", "TRASLADA"
    private Integer correlativo;

    // Relación con Bodega
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bodegaEntrada_id", nullable = false)
    private Bodega bodegaEntrada;

    // Relación con Bodega
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bodegaSalida_id", nullable = false)
    private Bodega bodegaSalida;

    private Integer items;
    private String tipoImpresion; // "DIRECTO", "VIEWER"

    private Boolean predet;
    private Boolean documentoElectronico;
    private Boolean activo;
    private Boolean noAfecta;
    private Boolean noValida;

    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "reporte_id")
    private Reporte reporte;
}