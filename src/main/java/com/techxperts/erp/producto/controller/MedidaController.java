package com.techxperts.erp.producto.controller;

import com.techxperts.erp.producto.model.Medida;
import com.techxperts.erp.producto.service.MedidaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medidas")
@RequiredArgsConstructor
public class MedidaController {

    private final MedidaService medidaService;

    @PostMapping
    public Medida crear(@RequestBody Medida medida) {
        return medidaService.crear(medida);
    }

    @GetMapping
    public List<Medida> listar() {
        return medidaService.listar();
    }

    @GetMapping("/{id}")
    public Medida obtener(@PathVariable Long id) {
        return medidaService.obtenerPorId(id);
    }
}