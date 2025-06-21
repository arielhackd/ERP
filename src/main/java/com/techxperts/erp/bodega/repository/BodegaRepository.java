package com.techxperts.erp.bodega.repository;

import com.techxperts.erp.bodega.model.Bodega;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BodegaRepository extends JpaRepository<Bodega, Long> {
    List<Bodega> findByEmpresaId(Long empresaId);
    Optional<Bodega> findByIdAndEmpresaId(Long id, Long empresaId);
}