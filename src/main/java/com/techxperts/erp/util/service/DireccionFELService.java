package com.techxperts.erp.util.service;

import com.techxperts.erp.util.dto.DireccionFELDTO;
import com.techxperts.erp.util.model.DireccionFEL;
import com.techxperts.erp.util.repository.DireccionFELRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DireccionFELService {

    private final DireccionFELRepository direccionFELRepository;

    public DireccionFEL crearDireccionFEL(DireccionFELDTO dto){
        DireccionFEL creada = mapearADominio(dto);
        return direccionFELRepository.save(creada);
    }

    public DireccionFEL obtenerDireccionFELPorId(Long id){
        DireccionFEL consulta = direccionFELRepository.findById(id).orElseThrow(() -> new RuntimeException("Dirección FEL no encontrada con ID: " + id));
        return consulta;
    }

    public List<DireccionFELDTO> obtenerTodas(){
        List<DireccionFEL> consulta = direccionFELRepository.findAll();
        return consulta.stream().map(this::mapearADTO).collect(Collectors.toList());
    }

    public DireccionFEL mapearADominio (DireccionFELDTO dto){
        DireccionFEL dominio = new DireccionFEL();
        dominio.setId(dto.getId());
        dominio.setPais(dto.getPais());
        dominio.setDepartamento(dto.getDepartamento());
        dominio.setMunicipio(dto.getMunicipio());
        dominio.setCodigoPostal(dto.getCodigoPostal());
        return dominio;
    }

    public DireccionFELDTO mapearADTO(DireccionFEL dominio){
        DireccionFELDTO dto = new DireccionFELDTO();
        dto.setId(dominio.getId());
        dto.setPais(dominio.getPais());
        dto.setDepartamento(dominio.getDepartamento());
        dto.setMunicipio(dominio.getMunicipio());
        dto.setCodigoPostal(dominio.getCodigoPostal());
        return dto;
    }
}
