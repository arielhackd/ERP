package com.techxperts.erp.producto.repository;

import com.techxperts.erp.producto.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    boolean existsByCodigoAndEmpresaId(String codigo, Long empresaId);
}