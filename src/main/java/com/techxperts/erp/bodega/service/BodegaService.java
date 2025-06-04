package com.techxperts.erp.bodega.service;

import com.techxperts.erp.bodega.model.Bodega;
import com.techxperts.erp.bodega.repository.BodegaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BodegaService {

    private final BodegaRepository bodegaRepository;

    public Bodega guardar(Bodega bodega) {
        return bodegaRepository.save(bodega);
    }

    public List<Bodega> listar() {
        return bodegaRepository.findAll();
    }
}