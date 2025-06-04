package com.techxperts.erp.empresa.repository;

import com.techxperts.erp.empresa.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}