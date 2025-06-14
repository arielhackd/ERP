package com.techxperts.erp.producto.service;

import com.techxperts.erp.empresa.model.Empresa;
import com.techxperts.erp.empresa.repository.EmpresaRepository;
import com.techxperts.erp.producto.model.Procedencia;
import com.techxperts.erp.producto.repository.ProcedenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcedenciaService {

    private final ProcedenciaRepository procedenciaRepository;
    private final EmpresaRepository empresaRepository;

    public Procedencia crear(Procedencia procedencia, Long empresaId) {
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Empresa no encontrada."));
        procedencia.setEmpresa(empresa);
        return procedenciaRepository.save(procedencia);
    }

    public List<Procedencia> listar() {
        return procedenciaRepository.findAll();
    }

    public Procedencia obtenerPorId(Long id) {
        return procedenciaRepository.findById(id).orElse(null);
    }

    public List<Procedencia> obtenerPorEmpresa(Long empresaId){
        return procedenciaRepository.findByEmpresaId(empresaId);
    }
}