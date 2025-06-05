package com.techxperts.erp.producto.controller;

import com.techxperts.erp.producto.model.Clase2;
import com.techxperts.erp.producto.service.Clase2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clase2")
@RequiredArgsConstructor
public class Clase2Controller {

    private final Clase2Service clase2Service;

    @PostMapping
    public Clase2 crear(@RequestBody Clase2 clase2) {
        return clase2Service.crear(clase2);
    }

    @GetMapping
    public List<Clase2> listar() {
        return clase2Service.listar();
    }

    @GetMapping("/{id}")
    public Clase2 obtener(@PathVariable Long id) {
        return clase2Service.obtenerPorId(id);
    }
}