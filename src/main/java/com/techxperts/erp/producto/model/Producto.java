package com.techxperts.erp.producto.model;

import com.techxperts.erp.empresa.model.Empresa;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean activo;

    private String codigo;
    private String nombre;
    private String descripcion2; // para búsquedas internas
    private String descripcion3; // para impresión en documentos
    private String observaciones;

    @Column(length = 20)
    private String tipo; // "BIEN" o "SERVICIO"

    @Column(precision = 10, scale = 2)
    private BigDecimal costo;

    @Column(precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(precision = 10, scale = 2)
    private BigDecimal descuentoMaximo;

    @Column(precision = 10, scale = 2)
    private BigDecimal precioOferta;

    private LocalDate fechaInicioOferta = LocalDate.of(2000, 1, 1);
    private LocalDate fechaFinOferta = LocalDate.of(2000, 1, 1);

    private boolean usaEscalas;
    private boolean usaSeries;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clase1_id")
    private Clase1 clase1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clase2_id")
    private Clase2 clase2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clase3_id")
    private Clase3 clase3;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "marca_id")
    private Marca marca;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medida_id")
    private Medida medida;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "procedencia_id")
    private Procedencia procedencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;
}