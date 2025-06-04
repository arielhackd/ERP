package com.techxperts.erp.bodega.repository;

import com.techxperts.erp.bodega.model.Bodega;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BodegaRepository extends JpaRepository<Bodega, Long> {
}