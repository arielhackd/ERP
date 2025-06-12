package com.techxperts.erp.producto.repository;

import com.techxperts.erp.producto.model.Clase3;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Clase3Repository extends JpaRepository<Clase3, Long> {
    Optional<Clase3> findByNombre(String nombre);
}