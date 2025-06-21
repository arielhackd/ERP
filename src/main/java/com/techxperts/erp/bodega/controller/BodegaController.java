package com.techxperts.erp.bodega.controller;

import com.techxperts.erp.bodega.model.Bodega;
import com.techxperts.erp.bodega.service.BodegaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        Long empresaId = 1L;
        return bodegaService.obtenerTodosPorEmpresa(empresaId);
    }

    @GetMapping("/{id}")
    public Bodega listarBodega(@PathVariable Long id) {
        Long empresaId = 1L;
        return bodegaService.obtenerPorEmpesa(id, empresaId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bodega> actualizarBodega(@PathVariable Long id, @RequestBody Bodega bodega){
        Long empresaId = 1L;
        if(!id.equals(bodega.getId())){
            return ResponseEntity.badRequest().build();
        }
        Bodega actualizada = bodegaService.actualizarBodega(id, bodega, empresaId);
        return ResponseEntity.ok(actualizada);
    }
}