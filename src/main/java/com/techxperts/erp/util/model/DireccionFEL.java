package com.techxperts.erp.util.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "direccion_fel")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DireccionFEL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pais;
    private String departamento;
    private String municipio;
    private String codigoPostal;

}