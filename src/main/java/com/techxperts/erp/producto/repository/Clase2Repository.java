package com.techxperts.erp.producto.repository;

import com.techxperts.erp.producto.model.Clase2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Clase2Repository extends JpaRepository<Clase2, Long> {
    Optional<Clase2> findByNombre(String nombre);
}