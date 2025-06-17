package com.techxperts.erp.producto.service;

import com.techxperts.erp.empresa.repository.EmpresaRepository;
import com.techxperts.erp.producto.dto.EscalaDTO;
import com.techxperts.erp.producto.model.Escala;
import com.techxperts.erp.producto.model.Producto;
import com.techxperts.erp.producto.repository.EscalaRepository;
import com.techxperts.erp.producto.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EscalaService {

    private final EscalaRepository escalaRepository;
    private final ProductoRepository productoRepository;
    private final EmpresaRepository empresaRepository;

    public List<EscalaDTO> obtenerTodas() {
        return escalaRepository.findAll().stream()
                .map(this::mapearADTO)
                .collect(Collectors.toList());
    }

    public EscalaDTO crear(EscalaDTO dto) {
        Escala escala = new Escala();
        escala.setCantidadMinima(dto.getCantidadMinima());
        escala.setCantidadMaxima(dto.getCantidadMaxima());
        escala.setPrecioUnitario(dto.getPrecioUnitario());

        escala.setProducto(productoRepository.findById(dto.getProductoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Producto no encontrado")));

        escala.setEmpresa(empresaRepository.findById(dto.getEmpresaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Empresa no encontrada")));

        escalaRepository.save(escala);
        return mapearADTO(escala);
    }

    private EscalaDTO mapearADTO(Escala escala) {
        EscalaDTO dto = new EscalaDTO();
        dto.setId(escala.getId());
        dto.setCantidadMinima(escala.getCantidadMinima());
        dto.setCantidadMaxima(escala.getCantidadMaxima());
        dto.setPrecioUnitario(escala.getPrecioUnitario());
        dto.setProductoId(escala.getProducto().getId());
        dto.setEmpresaId(escala.getEmpresa().getId());
        return dto;
    }
}