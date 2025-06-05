package com.techxperts.erp.periodo.model;

import com.techxperts.erp.empresa.model.Empresa;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "periodos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Periodo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int mes;
    private int anio;

    @Enumerated(EnumType.STRING)
    private EstadoPeriodo estado; // ABIERTO o CERRADO

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;
}