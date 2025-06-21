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

    public List<Bodega> obtenerTodosPorEmpresa(Long empresaId) {
        return bodegaRepository.findByEmpresaId(empresaId);
    }

    public Bodega obtenerPorEmpesa(Long id, Long empresaId){
        return bodegaRepository.findByIdAndEmpresaId(id, empresaId).orElseThrow();
    }

    public Bodega actualizarBodega(Long id, Bodega bodega, Long empresaId) {
        Bodega consulta = bodegaRepository.findByIdAndEmpresaId(id, empresaId).orElseThrow(() -> new RuntimeException("Bodega no encontrado con ID: " + id));
        consulta.setId(id);
        consulta.setNombre(bodega.getNombre());
        consulta.setDireccion(bodega.getDireccion());
        consulta.setActiva(bodega.isActiva());
        return bodegaRepository.save(consulta);
    }
}