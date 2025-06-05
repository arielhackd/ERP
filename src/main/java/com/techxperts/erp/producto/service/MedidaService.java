package com.techxperts.erp.producto.service;

import com.techxperts.erp.producto.model.Medida;
import com.techxperts.erp.producto.repository.MedidaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedidaService {

    private final MedidaRepository medidaRepository;

    public Medida crear(Medida medida) {
        return medidaRepository.save(medida);
    }

    public List<Medida> listar() {
        return medidaRepository.findAll();
    }

    public Medida obtenerPorId(Long id) {
        return medidaRepository.findById(id).orElse(null);
    }
}