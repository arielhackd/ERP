package com.techxperts.erp.inventario.model;

import com.techxperts.erp.bodega.model.Bodega;
import com.techxperts.erp.empresa.model.Empresa;
import com.techxperts.erp.periodo.model.Periodo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "documento_inventario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentoInventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Empresa empresa;

    @ManyToOne
    private Periodo periodo;

    private String tipoDocumento; // INVENTARIO
    private String gestion; // SUMA, RESTA o TRASLADO

    private String codigoDocumento; // AJUEN, AJUSA, etc.
    private String numeroDocumento;

    private LocalDateTime fechaOperado;
    private String referencia;

    @ManyToOne
    private Bodega bodegaSalida;

    @ManyToOne
    private Bodega bodegaEntrada;

    private String observaciones;
    private String estatus;

    private LocalDateTime fechaCreado;
    private LocalDateTime fechaImpresion;

    private String usuario;
}