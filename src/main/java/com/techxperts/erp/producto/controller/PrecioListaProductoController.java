package com.techxperts.erp.producto.controller;

import com.techxperts.erp.producto.dto.PrecioListaProductoDTO;
import com.techxperts.erp.producto.model.PrecioListaProducto;
import com.techxperts.erp.producto.service.PrecioListaProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/precios-lista-producto")
@RequiredArgsConstructor
public class PrecioListaProductoController {

    private final PrecioListaProductoService precioListaProductoService;

    @PostMapping
    public ResponseEntity<PrecioListaProductoDTO> crear(@RequestBody PrecioListaProductoDTO dto){
        PrecioListaProducto creada = precioListaProductoService.crearPrecioListaProducto(dto);
        PrecioListaProductoDTO respuesta = precioListaProductoService.mapearADTO(creada);
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }

    @GetMapping("/lista/{listaId}")
    public List<PrecioListaProductoDTO> listarPorListaId(@PathVariable Long listaId) {
        return precioListaProductoService.obtenerPorListaPrecioId(listaId);
    }

    @GetMapping("/lista/{listaId}/producto/{productoId}")
    public PrecioListaProductoDTO listarPorProductoIdListaId(@PathVariable Long listaId, @PathVariable Long productoId) {
        PrecioListaProducto consulta = precioListaProductoService.obtenerPorProductoIdListaPrecioId(productoId,listaId);
        return precioListaProductoService.mapearADTO(consulta);
    }
}
