package com.techxperts.erp.reporte.service;

import com.techxperts.erp.empresa.model.Empresa;
import com.techxperts.erp.empresa.repository.EmpresaRepository;
import com.techxperts.erp.reporte.dto.ReporteDTO;
import com.techxperts.erp.reporte.model.Reporte;
import com.techxperts.erp.reporte.repository.ReporteRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReporteService {

    private final ReporteRepository reporteRepository;
    private final EmpresaRepository empresaRepository;

    public List<ReporteDTO> listarPorEmpresa(Long empresaId) {
        return reporteRepository.findByEmpresaId(empresaId).stream()
                .map(this::mapearADTO)
                .collect(Collectors.toList());
    }

    public ReporteDTO crear(ReporteDTO dto){
        Empresa empresa = empresaRepository.findById(dto.getEmpresaId()).orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
        Reporte creado = mapearADominio(dto);
        creado.setEmpresa(empresa);
        return mapearADTO(reporteRepository.save(creado));
    }

    public ReporteDTO actualizar(Long id, ReporteDTO dto){
        Reporte existente = reporteRepository.findById(id).orElseThrow(() -> new RuntimeException("Reporte no encontrado"));
        existente.setDescripcion(dto.getDescripcion());
        existente.setNombreArchivo(dto.getNombreArchivo());
        existente.setActivo(dto.getActivo());
        existente.setObservaciones(dto.getObservaciones());
        return mapearADTO(reporteRepository.save(existente));
    }

    private ReporteDTO mapearADTO (Reporte dominio){
        ReporteDTO dto = new ReporteDTO();
        dto.setId(dominio.getId());
        dto.setEmpresaId(dominio.getId());
        dto.setDescripcion(dominio.getDescripcion());
        dto.setNombreArchivo(dominio.getNombreArchivo());
        dto.setActivo(dominio.getActivo());
        dto.setObservaciones(dominio.getObservaciones());
        return dto;
    }

    private Reporte mapearADominio(ReporteDTO dto){
        Reporte dominio = new Reporte();
        dominio.setId(dto.getId());
        dominio.setEmpresa(empresaRepository.findById(dto.getEmpresaId()).orElseThrow(null));
        dominio.setDescripcion(dto.getDescripcion());
        dominio.setNombreArchivo(dto.getNombreArchivo());
        dominio.setObservaciones(dto.getObservaciones());
        dominio.setActivo(dto.getActivo());
        return dominio;
    }
}
