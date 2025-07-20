package com.techxperts.erp.inventario.repository;

import com.techxperts.erp.inventario.model.DocumentoInventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentoInventarioRepository extends JpaRepository<DocumentoInventario, Long> {
}