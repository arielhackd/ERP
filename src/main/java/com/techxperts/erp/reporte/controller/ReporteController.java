package com.techxperts.erp.reporte.controller;

import com.techxperts.erp.reporte.dto.ReporteDTO;
import com.techxperts.erp.reporte.service.ReporteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
public class ReporteController {

    private final ReporteService service;

    @GetMapping
    public List<ReporteDTO> listarPorEmpresa(@RequestParam Long empresaId) {
        return service.listarPorEmpresa(empresaId);
    }

    @PostMapping
    public ReporteDTO crear(@RequestBody ReporteDTO dto) {
        return service.crear(dto);
    }

    @PutMapping("/{id}")
    public ReporteDTO actualizar(@PathVariable Long id, @RequestBody ReporteDTO dto) {
        return service.actualizar(id, dto);
    }
}