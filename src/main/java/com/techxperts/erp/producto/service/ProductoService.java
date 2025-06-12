package com.techxperts.erp.producto.service;

import com.techxperts.erp.producto.dto.ProductoDTO;
import com.techxperts.erp.producto.model.Producto;
import com.techxperts.erp.producto.repository.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final ModelMapper modelMapper;
    private final MarcaRepository marcaRepository;
    private final MedidaRepository medidaRepository;
    private final Clase1Repository clase1Repository;
    private final Clase2Repository clase2Repository;
    private final Clase3Repository clase3Repository;
    private final ProcedenciaRepository procedenciaRepository;

    public ProductoService(
            ModelMapper modelMapper,
            ProductoRepository productoRepository,
            MarcaRepository marcaRepository,
            MedidaRepository medidaRepository,
            Clase1Repository clase1Repository,
            Clase2Repository clase2Repository,
            Clase3Repository clase3Repository,
            ProcedenciaRepository procedenciaRepository
    ) {
        this.productoRepository = productoRepository;
        this.marcaRepository = marcaRepository;
        this.medidaRepository = medidaRepository;
        this.clase1Repository = clase1Repository;
        this.clase2Repository = clase2Repository;
        this.clase3Repository = clase3Repository;
        this.procedenciaRepository = procedenciaRepository;
        this.modelMapper = modelMapper;
    }

    public ProductoDTO crearProducto(ProductoDTO dto) {
        Producto producto = mapearADominio(dto);
        Producto guardado = productoRepository.save(producto);
        return mapearADTO(guardado);
    }

    public ProductoDTO actualizarProducto(Long id, ProductoDTO dto) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));

        producto.setNombre(dto.getNombre());
        producto.setActivo(dto.isActivo());

        // Mapear relaciones
        producto.setMarca(marcaRepository.findById(dto.getMarcaId()).orElse(null));
        producto.setMedida(medidaRepository.findById(dto.getMedidaId()).orElse(null));
        producto.setClase1(clase1Repository.findById(dto.getClase1Id()).orElse(null));
        producto.setClase2(clase2Repository.findById(dto.getClase2Id()).orElse(null));
        producto.setClase3(clase3Repository.findById(dto.getClase3Id()).orElse(null));

        Producto actualizado = productoRepository.save(producto);
        return mapearADTO(actualizado);
    }

    public List<ProductoDTO> obtenerTodos() {
        List<Producto> productos = productoRepository.findAll();
        return productos.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    private ProductoDTO convertirADTO(Producto producto) {
        ProductoDTO dto = modelMapper.map(producto, ProductoDTO.class);
        dto.setClase1(producto.getClase1().getNombre());
        dto.setClase2(producto.getClase2().getNombre());
        dto.setClase3(producto.getClase3().getNombre());
        dto.setMarca(producto.getMarca().getNombre());
        dto.setMedida(producto.getMedida().getNombre());
        dto.setProcedencia(producto.getProcedencia().getNombre());
        return dto;
    }

    public ProductoDTO obtenerPorId(Long id) {
        return productoRepository.findById(id)
                .map(this::convertirADTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));
    }

    private ProductoDTO mapearADTO(Producto producto) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setActivo(producto.isActivo());

        if (producto.getMarca() != null) dto.setMarca(producto.getMarca().getNombre());
        if (producto.getMedida() != null) dto.setMedida(producto.getMedida().getNombre());
        if (producto.getClase1() != null) dto.setClase1(producto.getClase1().getNombre());
        if (producto.getClase2() != null) dto.setClase2(producto.getClase2().getNombre());
        if (producto.getClase3() != null) dto.setClase3(producto.getClase3().getNombre());
        if (producto.getProcedencia() != null) dto.setProcedencia(producto.getProcedencia().getNombre());

        return dto;
    }

    private Producto mapearADominio(ProductoDTO dto) {
        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setActivo(dto.isActivo());

        producto.setMarca(marcaRepository.findByNombre(dto.getMarca()).orElse(null));
        producto.setMedida(medidaRepository.findByNombre(dto.getMedida()).orElse(null));
        producto.setClase1(clase1Repository.findByNombre(dto.getClase1()).orElse(null));
        producto.setClase2(clase2Repository.findByNombre(dto.getClase2()).orElse(null));
        producto.setClase3(clase3Repository.findByNombre(dto.getClase3()).orElse(null));

        return producto;
    }
}