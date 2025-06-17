package com.techxperts.erp.producto.service;

import com.techxperts.erp.empresa.repository.EmpresaRepository;
import com.techxperts.erp.producto.dto.ProductoDTO;
import com.techxperts.erp.producto.model.Producto;
import com.techxperts.erp.producto.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final EmpresaRepository empresaRepository;
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

    public Producto crearProducto(ProductoDTO dto) {
        Producto producto = mapearADominio(dto);
        return productoRepository.save(producto);
    }

    public Producto mapearADominio(ProductoDTO dto) {
        Producto producto = new Producto();

        producto.setCodigo(dto.getCodigo());
        producto.setNombre(dto.getNombre());
        producto.setDescripcion2(dto.getDescripcion2());
        producto.setDescripcion3(dto.getDescripcion3());
        producto.setObservaciones(dto.getObservaciones());
        producto.setTipo(dto.getTipo());
        producto.setCosto(dto.getCosto());
        producto.setPrecio(dto.getPrecio());
        producto.setPrecioOferta(dto.getPrecioOferta());
        producto.setDescuentoMaximo(dto.getDescuentoMaximo());
        producto.setFechaInicioOferta(
                dto.getFechaInicioOferta() != null ? dto.getFechaInicioOferta() : LocalDate.of(2000, 1, 1)
        );
        producto.setFechaFinOferta(
                dto.getFechaFinOferta() != null ? dto.getFechaFinOferta() : LocalDate.of(2000, 1, 1)
        );
        producto.setUsaEscalas(dto.isUsaEscalas());
        producto.setUsaSeries(dto.isUsaSeries());

        // Relaciones
        producto.setEmpresa(empresaRepository.findById(dto.getEmpresaId()).orElseThrow());
        producto.setMarca(marcaRepository.findById(dto.getMarcaId()).orElseThrow());
        producto.setClase1(clase1Repository.findById(dto.getClase1Id()).orElseThrow());
        producto.setClase2(clase2Repository.findById(dto.getClase2Id()).orElse(null));
        producto.setClase3(clase3Repository.findById(dto.getClase3Id()).orElse(null));
        producto.setProcedencia(procedenciaRepository.findById(dto.getProcedenciaId()).orElse(null));

        return producto;
    }

    public ProductoDTO mapearADTO(Producto producto) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setActivo(producto.isActivo());
        dto.setCodigo(producto.getCodigo());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion2(producto.getDescripcion2());
        dto.setDescripcion3(producto.getDescripcion3());
        dto.setObservaciones(producto.getObservaciones());
        dto.setTipo(producto.getTipo());

        dto.setCosto(producto.getCosto());
        dto.setPrecio(producto.getPrecio());
        dto.setDescuentoMaximo(producto.getDescuentoMaximo());
        dto.setPrecioOferta(producto.getPrecioOferta());

        dto.setFechaInicioOferta(producto.getFechaInicioOferta());
        dto.setFechaFinOferta(producto.getFechaFinOferta());

        dto.setUsaEscalas(producto.isUsaEscalas());

        if (producto.getClase1() != null)
            dto.setClase1Id(producto.getClase1().getId());
            dto.setClase1Nombre(producto.getClase1().getNombre());
        if (producto.getClase2() != null)
            dto.setClase2Id(producto.getClase2().getId());
            dto.setClase2Nombre(producto.getClase2().getNombre());
        if (producto.getClase3() != null)
            dto.setClase3Id(producto.getClase3().getId());
            dto.setClase3Nombre(producto.getClase3().getNombre());
        if (producto.getMarca() != null)
            dto.setMarcaId(producto.getMarca().getId());
            dto.setMarcaNombre(producto.getMarca().getNombre());
        if (producto.getMedida() != null)
            dto.setMedidaId(producto.getMedida().getId());
            dto.setMedidaNombre(producto.getMedida().getNombre());
        if (producto.getProcedencia() != null)
            dto.setProcedenciaId(producto.getProcedencia().getId());
            dto.setProcedenciaNombre(producto.getProcedencia().getNombre());

        return dto;
    }
}