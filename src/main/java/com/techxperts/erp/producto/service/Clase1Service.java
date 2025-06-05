package com.techxperts.erp.producto.service;

import com.techxperts.erp.producto.model.Clase1;
import com.techxperts.erp.producto.repository.Clase1Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Clase1Service {

    private final Clase1Repository clase1Repository;

    public Clase1 crear(Clase1 clase1) {
        return clase1Repository.save(clase1);
    }

    public List<Clase1> listar() {
        return clase1Repository.findAll();
    }

    public Clase1 obtenerPorId(Long id) {
        return clase1Repository.findById(id).orElse(null);
    }
}