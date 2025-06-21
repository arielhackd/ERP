package com.techxperts.erp.reporte.repository;

import com.techxperts.erp.reporte.model.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReporteRepository extends JpaRepository<Reporte, Long> {
    List<Reporte> findByEmpresaId(Long empresaId);
}