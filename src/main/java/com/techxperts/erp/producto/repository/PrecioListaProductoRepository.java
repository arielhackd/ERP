package com.techxperts.erp.producto.repository;

import com.techxperts.erp.producto.model.PrecioListaProducto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface PrecioListaProductoRepository extends JpaRepository<PrecioListaProducto, Long> {
    Optional<PrecioListaProducto> findByProductoIdAndListaPrecioId(Long productoId, Long listaPrecioId);
    List<PrecioListaProducto> findByListaPrecioId(Long listaPrecioId);
}