package com.techxperts.erp.producto.service;

import com.techxperts.erp.empresa.model.Empresa;
import com.techxperts.erp.producto.model.Marca;
import com.techxperts.erp.producto.repository.MarcaRepository;
import com.techxperts.erp.empresa.repository.EmpresaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarcaService {

    private final MarcaRepository marcaRepository;
    private final EmpresaRepository empresaRepository;

    public Marca crear(Marca marca, Long empresaId) {
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Empresa no encontrada"));
        marca.setEmpresa(empresa);
        return marcaRepository.save(marca);
    }

    public List<Marca> listar() {
        return marcaRepository.findAll();
    }

    public Marca obtenerPorId(Long id) {
        return marcaRepository.findById(id).orElse(null);
    }

    public List<Marca> obtenerPorEmpresa(Long empresaId) {
        return marcaRepository.findByEmpresaId(empresaId);
    }
}