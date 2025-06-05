package com.techxperts.erp.producto.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "procedencias")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Procedencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
}