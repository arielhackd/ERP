package com.techxperts.erp.producto.controller;

import com.techxperts.erp.producto.model.Medida;
import com.techxperts.erp.producto.service.MedidaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medidas")
@RequiredArgsConstructor
public class MedidaController {

    private final MedidaService medidaService;

    @PostMapping("/{empresaId}")
    public ResponseEntity<Medida> crear(@RequestBody Medida medida, @PathVariable Long empresaId) {
        Medida creada = medidaService.crear(medida,empresaId);
        return new ResponseEntity<>(creada, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Medida> listar() {
        return medidaService.listar();
    }

    @GetMapping("/{id}")
    public Medida obtener(@PathVariable Long id) {
        return medidaService.obtenerPorId(id);
    }

    @GetMapping("/empresa/{empresaId}")
    public List<Medida> obtenerMedidaPorEmpresa(@PathVariable Long empresaId){
        return medidaService.obtenerPorEmpresa(empresaId);
    }
}