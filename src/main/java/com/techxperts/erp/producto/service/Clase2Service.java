package com.techxperts.erp.producto.service;

import com.techxperts.erp.producto.model.Clase2;
import com.techxperts.erp.producto.repository.Clase2Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Clase2Service {

    private final Clase2Repository clase2Repository;

    public Clase2 crear(Clase2 clase2) {
        return clase2Repository.save(clase2);
    }

    public List<Clase2> listar() {
        return clase2Repository.findAll();
    }

    public Clase2 obtenerPorId(Long id) {
        return clase2Repository.findById(id).orElse(null);
    }
}