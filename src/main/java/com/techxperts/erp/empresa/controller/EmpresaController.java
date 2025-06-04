package com.techxperts.erp.empresa.controller;

import com.techxperts.erp.empresa.model.Empresa;
import com.techxperts.erp.empresa.service.EmpresaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empresas")
@CrossOrigin(origins = "*")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping
    public List<Empresa> listarEmpresas() {
        return empresaService.obtenerTodas();
    }

    @PostMapping
    public Empresa crearEmpresa(@RequestBody Empresa empresa) {
        return empresaService.crear(empresa);
    }
}