package com.techxperts.erp.producto.service;

import com.techxperts.erp.producto.dto.ProductoDTO;
import com.techxperts.erp.producto.model.Producto;
import com.techxperts.erp.producto.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final MarcaRepository marcaRepository;
    private final MedidaRepository medidaRepository;
    private final Clase1Repository clase1Repository;
    private final Clase2Repository clase2Repository;
    private final Clase3Repository clase3Repository;
    private final ProcedenciaRepository procedenciaRepository;

    public List<ProductoDTO> obtenerTodos() {
        List<Producto> productos = productoRepository.findAll();
        return productos.stream()
                .map(this::mapearADTO)
                .collect(Collectors.toList());
    }

    public ProductoDTO obtenerPorId(Long id) {
        return productoRepository.findById(id)
                .map(this::mapearADTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));
    }

    public ProductoDTO crearProducto(ProductoDTO dto) {
        Producto producto = new Producto();

        producto.setActivo(dto.isActivo());
        producto.setCodigo(dto.getCodigo());
        producto.setNombre(dto.getNombre());

        producto.setMarca(marcaRepository.findById(dto.getMarcaID())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Marca no encontrada")));

        producto.setMedida(medidaRepository.findById(dto.getMedidaID())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Medida no encontrada")));

        producto.setClase1(clase1Repository.findById(dto.getClase1ID())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Clase1 no encontrada")));

        producto.setClase2(clase2Repository.findById(dto.getClase2ID())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Clase2 no encontrada")));

        producto.setClase3(clase3Repository.findById(dto.getClase3ID())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Clase3 no encontrada")));

        producto.setProcedencia(procedenciaRepository.findById(dto.getProcedenciaID())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Procedencia no encontrada")));

        Producto guardado = productoRepository.save(producto);

        return mapearADTO(guardado); // reutilizamos el mapper existente
    }

    private ProductoDTO mapearADTO(Producto producto) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setActivo(producto.isActivo());
        dto.setCodigo(producto.getCodigo());
        dto.setNombre(producto.getNombre());

        if (producto.getClase1() != null)
            dto.setClase1ID(producto.getClase1().getId());
            dto.setClase1Nombre(producto.getClase1().getNombre());
        if (producto.getClase2() != null)
            dto.setClase2ID(producto.getClase2().getId());
            dto.setClase2Nombre(producto.getClase2().getNombre());
        if (producto.getClase3() != null)
            dto.setClase3ID(producto.getClase3().getId());
            dto.setClase3Nombre(producto.getClase3().getNombre());
        if (producto.getMarca() != null)
            dto.setMarcaID(producto.getMarca().getId());
            dto.setMarcaNombre(producto.getMarca().getNombre());
        if (producto.getMedida() != null)
            dto.setMedidaID(producto.getMedida().getId());
            dto.setMedidaNombre(producto.getMedida().getNombre());
        if (producto.getProcedencia() != null)
            dto.setProcedenciaID(producto.getProcedencia().getId());
            dto.setProcedenciaNombre(producto.getProcedencia().getNombre());

        return dto;
    }
}