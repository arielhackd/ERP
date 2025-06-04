package com.techxperts.erp.bodega.controller;

import com.techxperts.erp.bodega.model.Bodega;
import com.techxperts.erp.bodega.service.BodegaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bodegas")
@RequiredArgsConstructor
public class BodegaController {

    private final BodegaService bodegaService;

    @PostMapping
    public Bodega crearBodega(@RequestBody Bodega bodega) {
        return bodegaService.guardar(bodega);
    }

    @GetMapping
    public List<Bodega> listarBodegas() {
        return bodegaService.listar();
    }
}