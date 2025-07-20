package com.techxperts.erp.inventario.model;

import com.techxperts.erp.bodega.model.Bodega;
import com.techxperts.erp.empresa.model.Empresa;
import com.techxperts.erp.periodo.model.Periodo;
import com.techxperts.erp.producto.model.Producto;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventario_detalle_periodo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventarioDetallePeriodo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "periodo_id", nullable = false)
    private Periodo periodo;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "bodega_id", nullable = false)
    private Bodega bodega;

    @Column(name = "saldo_inicial", nullable = false)
    private BigDecimal saldoInicial = BigDecimal.ZERO;

    @Column(nullable = false)
    private BigDecimal entradas = BigDecimal.ZERO;

    @Column(nullable = false)
    private BigDecimal salidas = BigDecimal.ZERO;

    @Column(name = "saldo_final", nullable = false)
    private BigDecimal saldoFinal = BigDecimal.ZERO;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(name = "estado", nullable = false)
    private Boolean estado = true;
}