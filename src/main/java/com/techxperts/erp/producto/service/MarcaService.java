package com.techxperts.erp.producto.service;

import com.techxperts.erp.producto.model.Marca;
import com.techxperts.erp.producto.repository.MarcaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarcaService {

    private final MarcaRepository marcaRepository;

    public Marca crear(Marca marca) {
        return marcaRepository.save(marca);
    }

    public List<Marca> listar() {
        return marcaRepository.findAll();
    }

    public Marca obtenerPorId(Long id) {
        return marcaRepository.findById(id).orElse(null);
    }
}