package com.techxperts.erp.inventario.service;

import com.techxperts.erp.inventario.model.DocumentoInventario;
import com.techxperts.erp.inventario.repository.DocumentoInventarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DocumentoInventarioService {

    private final DocumentoInventarioRepository documentoRepository;

    public DocumentoInventario guardar(DocumentoInventario documento) {
        return documentoRepository.save(documento);
    }

    public List<DocumentoInventario> listar() {
        return documentoRepository.findAll();
    }

    public Optional<DocumentoInventario> buscarPorId(Long id) {
        return documentoRepository.findById(id);
    }

    public void eliminar(Long id) {
        documentoRepository.deleteById(id);
    }
}
