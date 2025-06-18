package com.techxperts.erp.producto.service;

import com.techxperts.erp.producto.dto.PrecioListaProductoDTO;
import com.techxperts.erp.producto.model.ListaPrecio;
import com.techxperts.erp.producto.model.PrecioListaProducto;
import com.techxperts.erp.producto.model.Producto;
import com.techxperts.erp.producto.repository.ListaPrecioRepository;
import com.techxperts.erp.producto.repository.PrecioListaProductoRepository;
import com.techxperts.erp.producto.repository.ProductoRepository;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrecioListaProductoService {

    private final PrecioListaProductoRepository precioListaProductoRepository;
    private final ProductoRepository productoRepository;
    private final ListaPrecioRepository listaPrecioRepository;

    public PrecioListaProducto crearPrecioListaProducto(PrecioListaProductoDTO dto){
        Producto producto = productoRepository.findById(dto.getProductoId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado."));
        ListaPrecio lista = listaPrecioRepository.findById(dto.getListaPrecioId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ListaPrecio no encontrado."));
        dto.setProductoId(producto.getId());
        dto.setListaPrecioId(lista.getId());
        PrecioListaProducto creada = mapearADominio(dto);
        return precioListaProductoRepository.save(creada);
    }

    public List<PrecioListaProductoDTO> obtenerPorListaPrecioId (Long listaPrecioId){
        List<PrecioListaProducto> consulta = precioListaProductoRepository.findByListaPrecioId(listaPrecioId);
        return consulta.stream().map(this::mapearADTO).collect(Collectors.toList());
    }

    public PrecioListaProducto obtenerPorProductoIdListaPrecioId(Long productoId, Long listaPrecioId){
        return precioListaProductoRepository.findByProductoIdAndListaPrecioId(productoId, listaPrecioId).orElse(null);
    }

    public PrecioListaProducto mapearADominio (PrecioListaProductoDTO dto){
        PrecioListaProducto dominio = new PrecioListaProducto();
        dominio.setId(dto.getId());
        dominio.setListaPrecio(listaPrecioRepository.findById(dto.getListaPrecioId()).orElseThrow());
        dominio.setProducto(productoRepository.findById(dto.getProductoId()).orElseThrow());
        dominio.setPrecio(dto.getPrecio());
        return dominio;
    }

    public PrecioListaProductoDTO mapearADTO(PrecioListaProducto dominio){
        PrecioListaProductoDTO dto = new PrecioListaProductoDTO();
        dto.setId(dominio.getId());
        dto.setListaPrecioId(dominio.getListaPrecio().getId());
        dto.setProductoId(dominio.getProducto().getId());
        dto.setPrecio(dominio.getPrecio());
        return dto;
    }
}
