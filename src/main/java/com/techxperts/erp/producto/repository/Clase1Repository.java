package com.techxperts.erp.producto.repository;

import com.techxperts.erp.producto.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Clase1Repository extends JpaRepository<Marca, Long> {
}