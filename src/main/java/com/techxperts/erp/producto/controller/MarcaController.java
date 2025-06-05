package com.techxperts.erp.producto.controller;

import com.techxperts.erp.producto.model.Marca;
import com.techxperts.erp.producto.service.MarcaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marcas")
@RequiredArgsConstructor
public class MarcaController {

    private final MarcaService marcaService;

    @PostMapping
    public Marca crear(@RequestBody Marca marca) {
        return marcaService.crear(marca);
    }

    @GetMapping
    public List<Marca> listar() {
        return marcaService.listar();
    }

    @GetMapping("/{id}")
    public Marca obtener(@PathVariable Long id) {
        return marcaService.obtenerPorId(id);
    }
}