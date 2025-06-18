package com.techxperts.erp.producto.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.*;

@Data
@Entity
@Table(name = "precio_lista_producto")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrecioListaProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lista_precio_id")
    private ListaPrecio listaPrecio;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    // Getters y setters
}