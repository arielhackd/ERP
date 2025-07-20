package com.techxperts.erp.inventario.repository;

import com.techxperts.erp.inventario.model.DetalleDocumentoInventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleDocumentoInventarioRepository extends JpaRepository<DetalleDocumentoInventario, Long> {
}