package com.techxperts.erp.proveedor.controller;

import com.techxperts.erp.proveedor.dto.ProveedorDTO;
import com.techxperts.erp.proveedor.service.ProveedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
@RequiredArgsConstructor
public class ProveedorController {

    private final ProveedorService proveedorService;

    @GetMapping
    public ResponseEntity<List<ProveedorDTO>> obtenerTodos(@RequestParam Long empresaId) {
        List<ProveedorDTO> proveedores = proveedorService.obtenerTodos(empresaId);
        return ResponseEntity.ok(proveedores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(proveedorService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<ProveedorDTO> crear(@Validated @RequestBody ProveedorDTO dto) {
        return ResponseEntity.ok(proveedorService.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProveedorDTO> actualizar(@PathVariable Long id, @Validated @RequestBody ProveedorDTO dto) {
        return ResponseEntity.ok(proveedorService.actualizar(id, dto));
    }
}