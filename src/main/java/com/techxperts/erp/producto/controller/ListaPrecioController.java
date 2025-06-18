package com.techxperts.erp.producto.controller;

import com.techxperts.erp.producto.dto.ListaPrecioDTO;
import com.techxperts.erp.producto.service.ListaPrecioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/listas-precio")
@RequiredArgsConstructor
public class ListaPrecioController {

    private final ListaPrecioService listaPrecioService;

    @PostMapping
    public ResponseEntity<ListaPrecioDTO> crear(@RequestBody ListaPrecioDTO dto) {
        return new ResponseEntity<>(listaPrecioService.crear(dto), HttpStatus.CREATED);
    }

    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<List<ListaPrecioDTO>> listarPorEmpresa(@PathVariable Long empresaId) {
        return ResponseEntity.ok(listaPrecioService.listarPorEmpresa(empresaId));
    }
}