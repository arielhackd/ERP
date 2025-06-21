package com.techxperts.erp.proveedor.model;

import com.techxperts.erp.empresa.model.Empresa;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "proveedores")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con Empresa
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @Column(nullable = false, unique = true, length = 20)
    private String nit;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 255)
    private String direccion;

    @Column(length = 20)
    private String telefono;

    @Column(length = 100)
    private String email;

    @Column(length = 255)
    private String observaciones;

    @Column(length = 100)
    private String contacto;

    @Column(length = 100)
    private String puestoContacto;

    @Column(length = 20)
    private String telContacto;

    @Column(nullable = false)
    private Integer diasCredito = 0;

    @Column(nullable = false)
    private Integer descuento = 0; // Porcentaje

    @Column(nullable = false)
    private LocalDate fechaIngreso = LocalDate.now();

    @Column(nullable = false)
    private boolean activo = true;
}