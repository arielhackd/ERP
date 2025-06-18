package com.techxperts.erp.producto.repository;

import com.techxperts.erp.producto.model.ListaPrecio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListaPrecioRepository extends JpaRepository<ListaPrecio, Long> {
    List<ListaPrecio> findByEmpresaId(Long empresaId);
    boolean existsByNombreAndEmpresaId(String nombre, Long empresaId);
}