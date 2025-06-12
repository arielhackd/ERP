package com.techxperts.erp.producto.repository;

import com.techxperts.erp.producto.model.Medida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedidaRepository extends JpaRepository<Medida, Long> {
    Optional<Medida> findByNombre(String nombre);
}