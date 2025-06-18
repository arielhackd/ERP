package com.techxperts.erp.util.repository;

import com.techxperts.erp.util.model.DireccionFEL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DireccionFELRepository extends JpaRepository<DireccionFEL, Long> {
    // Si en el futuro necesitas métodos custom para DirecciónFEL, aquí los agregas
}