package com.techxperts.erp.producto.controller;

import com.techxperts.erp.producto.model.Clase3;
import com.techxperts.erp.producto.service.Clase3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clase3")
@RequiredArgsConstructor
public class Clase3Controller {

    private final Clase3Service clase3Service;

    @PostMapping("/{empresaId}")
    public ResponseEntity<Clase3> crear(@RequestBody Clase3 clase3, @PathVariable Long empresaId) {
        Clase3 creada = clase3Service.crear(clase3,empresaId);
        return new ResponseEntity<>(creada, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Clase3> listar() {
        return clase3Service.listar();
    }

    @GetMapping("/{id}")
    public Clase3 obtener(@PathVariable Long id) {
        return clase3Service.obtenerPorId(id);
    }

    @GetMapping("/empresa/{empresaId}")
    public List<Clase3> obtenerClase3PorEmpresa (@PathVariable Long empresaId){
        return clase3Service.obtenerPorEmpresa(empresaId);
    }
}