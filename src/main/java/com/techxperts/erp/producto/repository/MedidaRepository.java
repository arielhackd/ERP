package com.techxperts.erp.producto.repository;

import com.techxperts.erp.producto.model.Medida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedidaRepository extends JpaRepository<Medida, Long> {
}