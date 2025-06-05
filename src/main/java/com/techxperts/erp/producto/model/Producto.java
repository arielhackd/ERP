package com.techxperts.erp.producto.model;

import com.techxperts.erp.empresa.model.Empresa;
import jakarta.persistence.*;
import lombok.*;

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

    private String codigo;

    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "marca_id")
    private Marca marca;

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
    @JoinColumn(name = "procedencia_id")
    private Procedencia procedencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medida_id")
    private Medida medida;

    private boolean activo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;
}