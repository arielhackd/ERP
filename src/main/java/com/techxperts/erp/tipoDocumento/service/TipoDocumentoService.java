package com.techxperts.erp.tipoDocumento.service;

import com.techxperts.erp.bodega.model.Bodega;
import com.techxperts.erp.bodega.repository.BodegaRepository;
import com.techxperts.erp.empresa.model.Empresa;
import com.techxperts.erp.empresa.repository.EmpresaRepository;
import com.techxperts.erp.reporte.model.Reporte;
import com.techxperts.erp.reporte.repository.ReporteRepository;
import com.techxperts.erp.tipoDocumento.dto.TipoDocumentoDTO;
import com.techxperts.erp.tipoDocumento.model.TipoDocumento;
import com.techxperts.erp.tipoDocumento.repository.TipoDocumentoRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TipoDocumentoService {

    private final TipoDocumentoRepository tipoDocumentoRepository;
    private final EmpresaRepository empresaRepository;
    private final BodegaRepository bodegaRepository;
    private final ReporteRepository reporteRepository;

    public List<TipoDocumentoDTO> listarPorEmpresa(Long empresaId){
        return tipoDocumentoRepository.findByEmpresaId(empresaId).stream()
                .map(this::mapearADTO)
                .collect(Collectors.toList());
    }

    public TipoDocumentoDTO crear (TipoDocumentoDTO dto){
        Empresa empresa = empresaRepository.findById(dto.getEmpresaId()).orElseThrow(null);
        Bodega bodegaEntrada = bodegaRepository.findById(dto.getBodegaEntradaId()).orElseThrow(null);
        Bodega bodegaSalida = bodegaRepository.findById(dto.getBodegaSalidaId()).orElseThrow(null);
        Reporte reporte = reporteRepository.findById(dto.getReporteId()).orElseThrow(null);
        TipoDocumento creado = mapearADominio(dto);
        creado.setEmpresa(empresa);
        creado.setBodegaEntrada(bodegaEntrada);
        creado.setBodegaSalida(bodegaSalida);
        creado.setReporte(reporte);
        return mapearADTO(tipoDocumentoRepository.save(creado));
    }

    public TipoDocumentoDTO actualizar (TipoDocumento dto){
        TipoDocumento existente = tipoDocumentoRepository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("TipoDocumento no encontrado"));
        existente.setEmpresa(dto.getEmpresa());
        existente.setCodigo(dto.getCodigo());
        existente.setTipoDocumento(dto.getTipoDocumento());
        existente.setDescripcion(dto.getDescripcion());
        existente.setSerie(dto.getSerie());
        existente.setGestion(dto.getGestion());
        existente.setCorrelativo(dto.getCorrelativo());
        existente.setBodegaEntrada(dto.getBodegaEntrada());
        existente.setBodegaSalida(dto.getBodegaSalida());
        existente.setItems(dto.getItems());
        existente.setReporte(dto.getReporte());
        existente.setTipoImpresion(dto.getTipoImpresion());
        existente.setPredet(dto.getPredet());
        existente.setDocumentoElectronico(dto.getDocumentoElectronico());
        existente.setActivo(dto.getActivo());
        existente.setNoAfecta(dto.getNoAfecta());
        existente.setNoValida(dto.getNoValida());
        existente.setObservaciones(dto.getObservaciones());
        return mapearADTO(tipoDocumentoRepository.save(existente));
    }

    private TipoDocumento mapearADominio (TipoDocumentoDTO dto){
        TipoDocumento dominio = new TipoDocumento();
        dominio.setId(dto.getId());
        dominio.setEmpresa(empresaRepository.findById(dto.getEmpresaId()).orElseThrow(null));
        dominio.setCodigo(dto.getCodigo());
        dominio.setTipoDocumento(dto.getTipoDocumento());
        dominio.setDescripcion(dto.getDescripcion());
        dominio.setSerie(dto.getSerie());
        dominio.setGestion(dto.getGestion());
        dominio.setCorrelativo(dto.getCorrelativo());
        dominio.setBodegaEntrada(bodegaRepository.findById(dto.getBodegaEntradaId()).orElseThrow(null));
        dominio.setBodegaSalida(bodegaRepository.findById(dto.getBodegaSalidaId()).orElseThrow(null));
        dominio.setItems(dto.getItems());
        dominio.setReporte(reporteRepository.findById(dto.getReporteId()).orElseThrow(null));
        dominio.setTipoImpresion(dto.getTipoImpresion());
        dominio.setPredet(dto.getPredet());
        dominio.setDocumentoElectronico(dto.getDocumentoElectronico());
        dominio.setActivo(dto.getActivo());
        dominio.setNoAfecta(dto.getNoAfecta());
        dominio.setNoValida(dto.getNoValida());
        dominio.setObservaciones(dto.getObservaciones());
        return dominio;
    }

    private TipoDocumentoDTO mapearADTO(TipoDocumento dominio){
        TipoDocumentoDTO dto = new TipoDocumentoDTO();
        dto.setId(dominio.getId());
        dto.setEmpresaId(dominio.getEmpresa().getId());
        dto.setCodigo(dominio.getCodigo());
        dto.setTipoDocumento(dominio.getTipoDocumento());
        dto.setDescripcion(dominio.getDescripcion());
        dto.setSerie(dominio.getSerie());
        dto.setGestion(dominio.getGestion());
        dto.setCorrelativo(dominio.getCorrelativo());
        dto.setBodegaEntradaId(dominio.getBodegaEntrada().getId());
        dto.setBodegaSalidaId(dominio.getBodegaSalida().getId());
        dto.setItems(dominio.getItems());
        dto.setReporteId(dominio.getReporte().getId());
        dto.setTipoImpresion(dominio.getTipoImpresion());
        dto.setPredet(dominio.getPredet());
        dto.setDocumentoElectronico(dominio.getDocumentoElectronico());
        dto.setActivo(dominio.getActivo());
        dto.setNoAfecta(dominio.getNoAfecta());
        dto.setNoValida(dominio.getNoValida());
        dto.setObservaciones(dominio.getObservaciones());
        return dto;
    }
}
