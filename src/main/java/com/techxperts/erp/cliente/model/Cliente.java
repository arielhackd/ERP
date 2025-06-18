package com.techxperts.erp.cliente.model;

import com.techxperts.erp.empresa.model.Empresa;
import com.techxperts.erp.producto.model.ListaPrecio;
import com.techxperts.erp.util.model.DireccionFEL;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con Empresa
    @ManyToOne
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @Column(name = "nit", nullable = false, length = 25)
    private String nit;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "nombre_factura", length = 100)
    private String nombreFactura;

    @Column(name = "direccion", length = 150)
    private String direccion;

    @Column(name = "direccion_factura", length = 150)
    private String direccionFactura;

    @Column(name = "direccion_entrega", length = 150)
    private String direccionEntrega;

    @Column(name = "telefono", length = 50)
    private String telefono;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "observaciones", length = 255)
    private String observaciones;

    // Relación con Lista de Precio
    @ManyToOne
    @JoinColumn(name = "lista_precio_id")
    private ListaPrecio listaPrecio;

    @Column(name = "bloquear_credito")
    private boolean bloquearCredito;

    @Column(name = "limite_credito")
    private Double limiteCredito;

    @Column(name = "dias_credito")
    private Integer diasCredito;

    @Column(name = "descuento")
    private Integer descuento;

    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    @Column(name = "activo")
    private boolean activo;

    // Subentidad: Dirección FEL
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "direccion_fel_id")
    private DireccionFEL direccionFEL;

}
