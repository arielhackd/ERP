package com.techxperts.erp.tipoDocumento.repository;

import com.techxperts.erp.tipoDocumento.model.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento, Long>{
    List<TipoDocumento> findByEmpresaId(Long empresaId);
    Optional<TipoDocumento> findByIdAndEmpresaId(Long id, Long empresaId);
}