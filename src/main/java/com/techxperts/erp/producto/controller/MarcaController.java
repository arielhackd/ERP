package com.techxperts.erp.producto.controller;

import com.techxperts.erp.producto.model.Marca;
import com.techxperts.erp.producto.service.MarcaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marcas")
@RequiredArgsConstructor
public class MarcaController {

    private final MarcaService marcaService;

    @PostMapping("/{empresaId}")
    public ResponseEntity<Marca> crear(@RequestBody Marca marca, @PathVariable Long empresaId) {
        Marca creada = marcaService.crear(marca, empresaId);
        return new ResponseEntity<>(creada, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Marca> listar() {
        return marcaService.listar();
    }

    @GetMapping("/{id}")
    public Marca obtener(@PathVariable Long id) {
        return marcaService.obtenerPorId(id);
    }

    @GetMapping("/empresa/{empresaId}")
    public List<Marca> obtenerMarcasPorEmpresa(@PathVariable Long empresaId) {
        return marcaService.obtenerPorEmpresa(empresaId);
    }
}