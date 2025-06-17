package com.techxperts.erp.producto.controller;

import com.techxperts.erp.producto.dto.EscalaDTO;
import com.techxperts.erp.producto.service.EscalaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/escalas")
@RequiredArgsConstructor
public class EscalaController {

    private final EscalaService escalaService;

    @GetMapping
    public List<EscalaDTO> obtenerTodas() {
        return escalaService.obtenerTodas();
    }

    @PostMapping
    public EscalaDTO crear(@RequestBody EscalaDTO dto) {
        return escalaService.crear(dto);
    }
}