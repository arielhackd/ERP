package com.techxperts.erp.producto.service;

import com.techxperts.erp.producto.model.Procedencia;
import com.techxperts.erp.producto.repository.ProcedenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcedenciaService {

    private final ProcedenciaRepository procedenciaRepository;

    public Procedencia crear(Procedencia procedencia) {
        return procedenciaRepository.save(procedencia);
    }

    public List<Procedencia> listar() {
        return procedenciaRepository.findAll();
    }

    public Procedencia obtenerPorId(Long id) {
        return procedenciaRepository.findById(id).orElse(null);
    }
}