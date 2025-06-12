package com.techxperts.erp.producto.controller;

import com.techxperts.erp.producto.model.Producto;
import com.techxperts.erp.producto.dto.ProductoDTO;
import com.techxperts.erp.producto.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @PostMapping
    public ResponseEntity<ProductoDTO> crearProducto(@RequestBody ProductoDTO productoDTO) {
        ProductoDTO creado = productoService.crearProducto(productoDTO);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizarProducto(@PathVariable Long id, @RequestBody ProductoDTO productoDTO) {
        ProductoDTO actualizado = productoService.actualizarProducto(id, productoDTO);
        return ResponseEntity.ok(actualizado);
    }

    @GetMapping
    public List<ProductoDTO> listarProductos() {
        return productoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ProductoDTO obtener(@PathVariable Long id) {
        return productoService.obtenerPorId(id);
    }
}