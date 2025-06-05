package com.techxperts.erp.producto.controller;

import com.techxperts.erp.producto.model.Clase3;
import com.techxperts.erp.producto.service.Clase3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clase3")
@RequiredArgsConstructor
public class Clase3Controller {

    private final Clase3Service clase3Service;

    @PostMapping
    public Clase3 crear(@RequestBody Clase3 clase3) {
        return clase3Service.crear(clase3);
    }

    @GetMapping
    public List<Clase3> listar() {
        return clase3Service.listar();
    }

    @GetMapping("/{id}")
    public Clase3 obtener(@PathVariable Long id) {
        return clase3Service.obtenerPorId(id);
    }
}