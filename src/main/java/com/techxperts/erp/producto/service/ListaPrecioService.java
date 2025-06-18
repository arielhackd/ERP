package com.techxperts.erp.producto.service;


import com.techxperts.erp.producto.dto.ListaPrecioDTO;
import com.techxperts.erp.empresa.model.Empresa;
import com.techxperts.erp.producto.model.ListaPrecio;
import com.techxperts.erp.empresa.repository.EmpresaRepository;
import com.techxperts.erp.producto.repository.ListaPrecioRepository;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListaPrecioService {

    private final ListaPrecioRepository listaPrecioRepository;
    private final EmpresaRepository empresaRepository;

    public ListaPrecio crearListaPrecio(ListaPrecioDTO dto){
        Empresa empresa = empresaRepository.findById(dto.getEmpresaId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa no encontrada."));
        dto.setEmpresaId(empresa.getId());
        ListaPrecio creada = mapearADominio(dto);
        return listaPrecioRepository.save(creada);
    }

    public List<ListaPrecioDTO> obtenerPorEmpresa(Long empresaId){
        List<ListaPrecio> consulta = listaPrecioRepository.findByEmpresaId(empresaId);
        return consulta.stream().map(this::mapearADTO).collect(Collectors.toList());
    }

    public ListaPrecio mapearADominio(ListaPrecioDTO dto){
        ListaPrecio lista = new ListaPrecio();
        lista.setId(dto.getId());
        lista.setNombre(dto.getNombre());
        lista.setEmpresa(empresaRepository.findById(dto.getEmpresaId()).orElseThrow());
        lista.setActiva(dto.getActiva());
        return lista;
    }

    public ListaPrecioDTO mapearADTO(ListaPrecio listaPrecio) {
        ListaPrecioDTO dto = new ListaPrecioDTO();
        dto.setId(listaPrecio.getId());
        dto.setNombre(listaPrecio.getNombre());
        if(listaPrecio.getEmpresa()!=null){
            dto.setEmpresaId(listaPrecio.getEmpresa().getId());
        }
        dto.setActiva(listaPrecio.getActiva());
        return dto;
    }
}