package com.techxperts.erp.inventario.controller;

import com.techxperts.erp.inventario.model.DocumentoInventario;
import com.techxperts.erp.inventario.service.DocumentoInventarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documentos-inventario")
@RequiredArgsConstructor
public class DocumentoInventarioController {

    private final DocumentoInventarioService documentoService;

    @PostMapping
    public ResponseEntity<DocumentoInventario> crear(@RequestBody DocumentoInventario documento) {
        return ResponseEntity.ok(documentoService.guardar(documento));
    }

    @GetMapping
    public ResponseEntity<List<DocumentoInventario>> listar() {
        return ResponseEntity.ok(documentoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentoInventario> obtener(@PathVariable Long id) {
        return documentoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        documentoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
