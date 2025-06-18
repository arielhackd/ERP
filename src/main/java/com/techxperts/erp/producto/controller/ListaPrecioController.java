package com.techxperts.erp.producto.controller;

import com.techxperts.erp.producto.dto.ListaPrecioDTO;
import com.techxperts.erp.producto.model.ListaPrecio;
import com.techxperts.erp.producto.service.ListaPrecioService;
import lombok.*;
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
        ListaPrecio creada = listaPrecioService.crearListaPrecio(dto);
        ListaPrecioDTO respuesta = listaPrecioService.mapearADTO(creada);
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }

    @GetMapping("/empresa/{empresaId}")
    public List<ListaPrecioDTO> listarPorEmpresa(@PathVariable Long empresaId) {
        return listaPrecioService.obtenerPorEmpresa(empresaId);
    }
}