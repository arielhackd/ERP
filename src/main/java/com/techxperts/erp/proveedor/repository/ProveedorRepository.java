package com.techxperts.erp.proveedor.repository;

import com.techxperts.erp.proveedor.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

    // Puedes agregar métodos personalizados si los necesitas, por ejemplo:
    List<Proveedor> findAllByEmpresaId(Long empresaId);
    boolean existsByNitAndEmpresaId(String nit, Long empresaId);
}