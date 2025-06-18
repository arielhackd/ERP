package com.techxperts.erp.producto.service;


import com.techxperts.erp.producto.dto.ListaPrecioDTO;
import com.techxperts.erp.empresa.model.Empresa;
import com.techxperts.erp.producto.model.ListaPrecio;
import com.techxperts.erp.empresa.repository.EmpresaRepository;
import com.techxperts.erp.producto.repository.ListaPrecioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@Service
@RequiredArgsConstructor
public class ListaPrecioService {

    private final ListaPrecioRepository listaPrecioRepository;
    private final EmpresaRepository empresaRepository;

    public ListaPrecioDTO crear(ListaPrecioDTO dto) {
        if (listaPrecioRepository.existsByNombreAndEmpresaId(dto.getNombre(), dto.getEmpresaId())) {
            throw new ResponseStatusException(BAD_REQUEST, "Ya existe una lista de precio con ese nombre en la empresa.");
        }

        Empresa empresa = empresaRepository.findById(dto.getEmpresaId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Empresa no encontrada"));

        ListaPrecio entidad = ListaPrecio.builder()
                .nombre(dto.getNombre())
                .empresa(empresa)
                .activa(Boolean.TRUE.equals(dto.getActiva()))
                .build();

        return mapearADTO(listaPrecioRepository.save(entidad));
    }

    public List<ListaPrecioDTO> listarPorEmpresa(Long empresaId) {
        return listaPrecioRepository.findByEmpresaId(empresaId)
                .stream()
                .map(this::mapearADTO)
                .collect(Collectors.toList());
    }

    private ListaPrecioDTO mapearADTO(ListaPrecio lp) {
        return ListaPrecioDTO.builder()
                .id(lp.getId())
                .nombre(lp.getNombre())
                .empresaId(lp.getEmpresa().getId())
                .activa(lp.getActiva())
                .build();
    }
}