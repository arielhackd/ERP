package com.techxperts.erp.producto.service;

import com.techxperts.erp.producto.model.Clase3;
import com.techxperts.erp.producto.repository.Clase3Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Clase3Service {

    private final Clase3Repository clase3Repository;

    public Clase3 crear(Clase3 clase3) {
        return clase3Repository.save(clase3);
    }

    public List<Clase3> listar() {
        return clase3Repository.findAll();
    }

    public Clase3 obtenerPorId(Long id) {
        return clase3Repository.findById(id).orElse(null);
    }
}