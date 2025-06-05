package com.techxperts.erp.producto.service;

import com.techxperts.erp.producto.dto.ProductoDTO;
import com.techxperts.erp.producto.model.Producto;
import com.techxperts.erp.producto.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final ModelMapper modelMapper;

    public Producto crear(Producto producto) {
        return productoRepository.save(producto);
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
        return convertirADTO(productoRepository.findById(id).orElse(null));
    }
}