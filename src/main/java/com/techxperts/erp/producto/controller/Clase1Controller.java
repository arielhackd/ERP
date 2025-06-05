package com.techxperts.erp.producto.controller;

import com.techxperts.erp.producto.model.Clase1;
import com.techxperts.erp.producto.service.Clase1Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clase1")
@RequiredArgsConstructor
public class Clase1Controller {

    private final Clase1Service clase1Service;

    @PostMapping
    public Clase1 crear(@RequestBody Clase1 clase1) {
        return clase1Service.crear(clase1);
    }

    @GetMapping
    public List<Clase1> listar() {
        return clase1Service.listar();
    }

    @GetMapping("/{id}")
    public Clase1 obtener(@PathVariable Long id) {
        return clase1Service.obtenerPorId(id);
    }
}