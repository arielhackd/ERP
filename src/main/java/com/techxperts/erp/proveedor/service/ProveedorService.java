package com.techxperts.erp.proveedor.service;


import com.techxperts.erp.empresa.repository.EmpresaRepository;
import com.techxperts.erp.proveedor.dto.ProveedorDTO;
import com.techxperts.erp.proveedor.model.Proveedor;
import com.techxperts.erp.proveedor.repository.ProveedorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProveedorService {

    private final ProveedorRepository proveedorRepository;
    private final EmpresaRepository empresaRepository;

    public List<ProveedorDTO> obtenerTodos(Long empresaId) {
        return proveedorRepository.findAllByEmpresaId(empresaId).stream()
                .map(this::mapearADTO)
                .collect(Collectors.toList());
    }

    public ProveedorDTO obtenerPorId(Long id) {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Proveedor no encontrado con ID: " + id));
        return mapearADTO(proveedor);
    }

    public ProveedorDTO crear(ProveedorDTO dto) {
        Proveedor proveedor = mapearADominio(dto);
        proveedor.setFechaIngreso(LocalDate.now()); // Asignar fecha por defecto
        return mapearADTO(proveedorRepository.save(proveedor));
    }

    public ProveedorDTO actualizar(Long id, ProveedorDTO dto) {
        Proveedor existente = proveedorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Proveedor no encontrado con ID: " + id));

        // Mapeamos cambios
        existente.setNit(dto.getNit());
        existente.setNombre(dto.getNombre());
        existente.setDireccion(dto.getDireccion());
        existente.setTelefono(dto.getTelefono());
        existente.setEmail(dto.getEmail());
        existente.setObservaciones(dto.getObservaciones());
        existente.setContacto(dto.getContacto());
        existente.setPuestoContacto(dto.getPuestoContacto());
        existente.setTelContacto(dto.getTelContacto());
        existente.setDiasCredito(dto.getDiasCredito());
        existente.setDescuento(dto.getDescuento());
        existente.setActivo(dto.isActivo());

        return mapearADTO(proveedorRepository.save(existente));
    }

    private Proveedor mapearADominio(ProveedorDTO dto) {
        Proveedor proveedor = new Proveedor();
        proveedor.setId(dto.getId());
        proveedor.setEmpresa(empresaRepository.findById(dto.getEmpresaId())
                .orElseThrow(() -> new EntityNotFoundException("Empresa no encontrada con ID: " + dto.getEmpresaId())));
        proveedor.setNit(dto.getNit());
        proveedor.setNombre(dto.getNombre());
        proveedor.setDireccion(dto.getDireccion());
        proveedor.setTelefono(dto.getTelefono());
        proveedor.setEmail(dto.getEmail());
        proveedor.setObservaciones(dto.getObservaciones());
        proveedor.setContacto(dto.getContacto());
        proveedor.setPuestoContacto(dto.getPuestoContacto());
        proveedor.setTelContacto(dto.getTelContacto());
        proveedor.setDiasCredito(dto.getDiasCredito());
        proveedor.setDescuento(dto.getDescuento());
        proveedor.setFechaIngreso(dto.getFechaIngreso() != null ? dto.getFechaIngreso() : LocalDate.now());
        proveedor.setActivo(dto.isActivo());
        return proveedor;
    }

    private ProveedorDTO mapearADTO(Proveedor proveedor) {
        ProveedorDTO dto = new ProveedorDTO();
        dto.setId(proveedor.getId());
        dto.setEmpresaId(proveedor.getEmpresa().getId());
        dto.setNit(proveedor.getNit());
        dto.setNombre(proveedor.getNombre());
        dto.setDireccion(proveedor.getDireccion());
        dto.setTelefono(proveedor.getTelefono());
        dto.setEmail(proveedor.getEmail());
        dto.setObservaciones(proveedor.getObservaciones());
        dto.setContacto(proveedor.getContacto());
        dto.setPuestoContacto(proveedor.getPuestoContacto());
        dto.setTelContacto(proveedor.getTelContacto());
        dto.setDiasCredito(proveedor.getDiasCredito());
        dto.setDescuento(proveedor.getDescuento());
        dto.setFechaIngreso(proveedor.getFechaIngreso());
        dto.setActivo(proveedor.isActivo());
        return dto;
    }
}