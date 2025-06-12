package com.techxperts.erp.producto.repository;

import com.techxperts.erp.producto.model.Procedencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProcedenciaRepository extends JpaRepository<Procedencia, Long> {
    Optional<Procedencia> findByNombre(String nombre);
}