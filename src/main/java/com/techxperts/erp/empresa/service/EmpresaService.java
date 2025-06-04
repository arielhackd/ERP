package com.techxperts.erp.empresa.service;

import com.techxperts.erp.empresa.model.Empresa;
import com.techxperts.erp.empresa.repository.EmpresaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public List<Empresa> obtenerTodas() {
        return empresaRepository.findAll();
    }

    public Empresa crear(Empresa empresa) {
        return empresaRepository.save(empresa);
    }
}