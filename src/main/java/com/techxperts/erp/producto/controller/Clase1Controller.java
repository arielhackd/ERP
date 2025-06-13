package com.techxperts.erp.producto.controller;

import com.techxperts.erp.producto.model.Clase1;
import com.techxperts.erp.producto.model.Marca;
import com.techxperts.erp.producto.service.Clase1Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clase1")
@RequiredArgsConstructor
public class Clase1Controller {

    private final Clase1Service clase1Service;

    @PostMapping("/{empresaId}")
    public ResponseEntity<Clase1> crear(@RequestBody Clase1 clase1,@PathVariable Long empresaId) {
        Clase1 creada = clase1Service.crear(clase1, empresaId);
        return new ResponseEntity<>(creada, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Clase1> listar() {
        return clase1Service.listar();
    }

    @GetMapping("/empresa/{empresaId}")
    public List<Clase1> obtenerClase1PorEmpresa(@PathVariable Long empresaId) {
        return clase1Service.obtenerPorEmpresa(empresaId);
    }
}