package com.techxperts.erp.producto.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "clase3")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Clase3 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
}