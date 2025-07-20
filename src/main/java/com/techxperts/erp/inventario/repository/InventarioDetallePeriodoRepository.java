package com.techxperts.erp.inventario.repository;

import com.techxperts.erp.bodega.model.Bodega;
import com.techxperts.erp.empresa.model.Empresa;
import com.techxperts.erp.inventario.model.InventarioDetallePeriodo;
import com.techxperts.erp.periodo.model.Periodo;
import com.techxperts.erp.producto.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventarioDetallePeriodoRepository extends JpaRepository<InventarioDetallePeriodo, Long> {
    Optional<InventarioDetallePeriodo> findByEmpresaAndPeriodoAndProductoAndBodega(
            Empresa empresa, Periodo periodo, Producto producto, Bodega bodega);
}