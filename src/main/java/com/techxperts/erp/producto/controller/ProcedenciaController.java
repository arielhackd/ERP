package com.techxperts.erp.producto.controller;

import com.techxperts.erp.producto.model.Procedencia;
import com.techxperts.erp.producto.service.ProcedenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/procedencias")
@RequiredArgsConstructor
public class ProcedenciaController {

    private final ProcedenciaService procedenciaService;

    @PostMapping
    public Procedencia crear(@RequestBody Procedencia procedencia) {
        return procedenciaService.crear(procedencia);
    }

    @GetMapping
    public List<Procedencia> listar() {
        return procedenciaService.listar();
    }

    @GetMapping("/{id}")
    public Procedencia obtener(@PathVariable Long id) {
        return procedenciaService.obtenerPorId(id);
    }
}