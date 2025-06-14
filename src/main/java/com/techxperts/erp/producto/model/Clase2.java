package com.techxperts.erp.producto.model;

import com.techxperts.erp.empresa.model.Empresa;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "clase2")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Clase2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private boolean activo;

    @ManyToOne
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;
}