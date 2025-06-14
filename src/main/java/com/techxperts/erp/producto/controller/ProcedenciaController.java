package com.techxperts.erp.producto.controller;

import com.techxperts.erp.producto.model.Procedencia;
import com.techxperts.erp.producto.service.ProcedenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/procedencias")
@RequiredArgsConstructor
public class ProcedenciaController {

    private final ProcedenciaService procedenciaService;

    @PostMapping("/{empresaId}")
    public ResponseEntity<Procedencia> crear(@RequestBody Procedencia procedencia, @PathVariable Long empresaId) {
        Procedencia creada = procedenciaService.crear(procedencia, empresaId);
        return new ResponseEntity<>(creada, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Procedencia> listar() {
        return procedenciaService.listar();
    }

    @GetMapping("/{id}")
    public Procedencia obtener(@PathVariable Long id) {
        return procedenciaService.obtenerPorId(id);
    }

    @GetMapping("empresa/{empresaId}")
    public List<Procedencia> obtenerProcedenciaPorEmpresa (@PathVariable Long empresaId){
        return procedenciaService.obtenerPorEmpresa(empresaId);
    }
}