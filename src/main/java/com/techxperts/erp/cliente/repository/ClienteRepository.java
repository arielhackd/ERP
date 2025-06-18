package com.techxperts.erp.cliente.repository;

import com.techxperts.erp.cliente.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Métodos personalizados si necesitas, ejemplo:
    boolean existsByNitAndEmpresaId(String nit, Long empresaId);
    // Otros queries específicos para cliente...
}