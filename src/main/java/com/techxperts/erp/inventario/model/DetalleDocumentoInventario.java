package com.techxperts.erp.inventario.model;

import com.techxperts.erp.bodega.model.Bodega;
import com.techxperts.erp.empresa.model.Empresa;
import com.techxperts.erp.periodo.model.Periodo;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "detalle_documento_inventario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleDocumentoInventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Empresa empresa;

    @ManyToOne
    private Periodo periodo;

    @ManyToOne
    private DocumentoInventario documentoInventario;

    @ManyToOne
    private Bodega bodegaEntrada;

    @ManyToOne
    private Bodega bodegaSalida;

    private String codigoProducto;
    private String descripcion;
    private BigDecimal cantidad;

    private String estatus;
}