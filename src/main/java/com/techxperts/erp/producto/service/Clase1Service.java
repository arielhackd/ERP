package com.techxperts.erp.producto.service;

import com.techxperts.erp.empresa.model.Empresa;
import com.techxperts.erp.empresa.repository.EmpresaRepository;
import com.techxperts.erp.producto.model.Clase1;
import com.techxperts.erp.producto.model.Marca;
import com.techxperts.erp.producto.repository.Clase1Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Clase1Service {

    private final Clase1Repository clase1Repository;
    private final EmpresaRepository empresaRepository;

    public Clase1 crear(Clase1 clase1, Long empresaId) {
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Empresa no encontrada"));
        clase1.setEmpresa(empresa);
        return clase1Repository.save(clase1);
    }

    public List<Clase1> listar() {
        return clase1Repository.findAll();
    }

    public Clase1 obtenerPorId(Long id) {
        return clase1Repository.findById(id).orElse(null);
    }

    public List<Clase1> obtenerPorEmpresa(Long empresaId) {
        return clase1Repository.findByEmpresaId(empresaId);
    }
}