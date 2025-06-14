package com.techxperts.erp.producto.controller;

import com.techxperts.erp.producto.model.Clase2;
import com.techxperts.erp.producto.service.Clase2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clase2")
@RequiredArgsConstructor
public class Clase2Controller {

    private final Clase2Service clase2Service;

    @PostMapping("/{empresaId}")
    public ResponseEntity<Clase2> crear(@RequestBody Clase2 clase2, @PathVariable Long empresaId) {
        Clase2 creada = clase2Service.crear(clase2, empresaId);
        return new ResponseEntity<>(creada, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Clase2> listar() {
        return clase2Service.listar();
    }

    @GetMapping("/empresa/{empresaId}")
    public List<Clase2> obtenerClase2PorEmpresa(@PathVariable Long empresaId){
        return clase2Service.obtenerPorEmpresa(empresaId);
    }

}