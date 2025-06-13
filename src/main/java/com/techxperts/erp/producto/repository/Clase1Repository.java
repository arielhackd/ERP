package com.techxperts.erp.producto.repository;

import com.techxperts.erp.producto.model.Clase1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Clase1Repository extends JpaRepository<Clase1, Long> {
    Optional<Clase1> findByNombre(String nombre);
    List<Clase1> findByEmpresaId(Long empresaId);
}