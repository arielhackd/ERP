package com.techxperts.erp.producto.service;

import com.techxperts.erp.empresa.model.Empresa;
import com.techxperts.erp.empresa.repository.EmpresaRepository;
import com.techxperts.erp.producto.model.Medida;
import com.techxperts.erp.producto.repository.MedidaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedidaService {

    private final MedidaRepository medidaRepository;
    private final EmpresaRepository empresaRepository;

    public Medida crear(Medida medida, Long empresaId) {
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Empresa no encontrada"));
        medida.setEmpresa(empresa);
        return medidaRepository.save(medida);
    }

    public List<Medida> listar() {
        return medidaRepository.findAll();
    }

    public Medida obtenerPorId(Long id) {
        return medidaRepository.findById(id).orElse(null);
    }

    public List<Medida> obtenerPorEmpresa(Long empresaId){
        return medidaRepository.findByEmpresaId(empresaId);
    }
}