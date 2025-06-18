package com.techxperts.erp.producto.service;

import com.techxperts.erp.empresa.repository.EmpresaRepository;
import com.techxperts.erp.producto.dto.ProductoDTO;
import com.techxperts.erp.producto.model.*;
import com.techxperts.erp.producto.repository.*;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public class BadRequestException extends RuntimeException {
        public BadRequestException(String message) {
            super(message);
        }
    }

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
        validarProducto(dto);
        Producto producto = mapearADominio(dto);
        return productoRepository.save(producto);
    }

    private void validarProducto(ProductoDTO dto) {
        // Validación 1: Precio oferta menor al normal
        if (dto.getPrecioOferta() != null && dto.getPrecioOferta().compareTo(dto.getPrecio()) >= 0) {
            throw new BadRequestException("El precio de oferta debe ser menor al precio normal.");
        }

        // Validación 2: Fechas de oferta válidas
        if (dto.getFechaInicioOferta() != null && dto.getFechaFinOferta() != null &&
                dto.getFechaFinOferta().isBefore(dto.getFechaInicioOferta())) {
            throw new BadRequestException("La fecha de fin de la oferta no puede ser anterior a la fecha de inicio.");
        }

        // Validación 3: Descuento máximo no mayor al 100%
        if (dto.getDescuentoMaximo() != null && dto.getDescuentoMaximo().compareTo(BigDecimal.valueOf(100)) > 0) {
            throw new BadRequestException("El descuento máximo no puede ser mayor al 100%.");
        }

        // Validación 4: Código único por empresa
        boolean existe = productoRepository.existsByCodigoAndEmpresaId(dto.getCodigo(), dto.getEmpresaId());
        if (existe) {
            throw new BadRequestException("Ya existe un producto con este código para esta empresa.");
        }

        // Validación 5: Entidades relacionadas existen y pertenecen a la empresa
        Long empresaId = dto.getEmpresaId();

        clase1Repository.findById(dto.getClase1Id())
                .filter(c -> c.getEmpresa().getId().equals(empresaId))
                .orElseThrow(() -> new BadRequestException("La Clase1 no existe o no pertenece a la empresa"));

        clase2Repository.findById(dto.getClase2Id())
                .filter(c -> c.getEmpresa().getId().equals(empresaId))
                .orElseThrow(() -> new BadRequestException("La Clase2 no existe o no pertenece a la empresa"));

        clase3Repository.findById(dto.getClase3Id())
                .filter(c -> c.getEmpresa().getId().equals(empresaId))
                .orElseThrow(() -> new BadRequestException("La Clase3 no existe o no pertenece a la empresa"));

        marcaRepository.findById(dto.getMarcaId())
                .filter(m -> m.getEmpresa().getId().equals(empresaId))
                .orElseThrow(() -> new BadRequestException("La Marca no existe o no pertenece a la empresa"));

        medidaRepository.findById(dto.getMedidaId())
                .filter(m -> m.getEmpresa().getId().equals(empresaId))
                .orElseThrow(() -> new BadRequestException("La Medida no existe o no pertenece a la empresa"));

        if (dto.getProcedenciaId() != null) {
            procedenciaRepository.findById(dto.getProcedenciaId())
                    .filter(p -> p.getEmpresa().getId().equals(empresaId))
                    .orElseThrow(() -> new BadRequestException("La Procedencia no existe o no pertenece a la empresa"));
        }
    }

    public ProductoDTO actualizarProducto(Long id, ProductoDTO dto){
        Producto existente = productoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado con ID: " + id));
        validarActualizacionProducto(id, dto, existente);

        Producto actualizado = mapearADominio(dto);
        actualizado.setId(id); // Aseguramos que conserve el mismo ID
        return mapearADTO(productoRepository.save(actualizado));
    }

    private void validarActualizacionProducto(Long id, ProductoDTO dto, Producto existente) {
        Long empresaId = dto.getEmpresaId();

        // Precio oferta < precio normal
        if (dto.getPrecioOferta() != null && dto.getPrecioOferta().compareTo(dto.getPrecio()) >= 0) {
            throw new BadRequestException("El precio de oferta debe ser menor al precio normal.");
        }

        // Fechas de oferta válidas
        if (dto.getFechaInicioOferta() != null && dto.getFechaFinOferta() != null &&
                dto.getFechaFinOferta().isBefore(dto.getFechaInicioOferta())) {
            throw new BadRequestException("La fecha de fin de la oferta no puede ser anterior a la fecha de inicio.");
        }

        // Descuento máximo válido
        if (dto.getDescuentoMaximo() != null && dto.getDescuentoMaximo().compareTo(BigDecimal.valueOf(100)) > 0) {
            throw new BadRequestException("El descuento máximo no puede ser mayor al 100%.");
        }

        // Si el código fue modificado, validar que no esté duplicado
        if (!dto.getCodigo().equals(existente.getCodigo())) {
            boolean codigoDuplicado = productoRepository.existsByCodigoAndEmpresaId(dto.getCodigo(), empresaId);
            if (codigoDuplicado) {
                throw new BadRequestException("Ya existe otro producto con este código en esta empresa.");
            }
        }

        // Validar entidades relacionadas
        clase1Repository.findById(dto.getClase1Id())
                .filter(c -> c.getEmpresa().getId().equals(empresaId))
                .orElseThrow(() -> new BadRequestException("La Clase1 no existe o no pertenece a la empresa"));

        clase2Repository.findById(dto.getClase2Id())
                .filter(c -> c.getEmpresa().getId().equals(empresaId))
                .orElseThrow(() -> new BadRequestException("La Clase2 no existe o no pertenece a la empresa"));

        clase3Repository.findById(dto.getClase3Id())
                .filter(c -> c.getEmpresa().getId().equals(empresaId))
                .orElseThrow(() -> new BadRequestException("La Clase3 no existe o no pertenece a la empresa"));

        marcaRepository.findById(dto.getMarcaId())
                .filter(m -> m.getEmpresa().getId().equals(empresaId))
                .orElseThrow(() -> new BadRequestException("La Marca no existe o no pertenece a la empresa"));

        medidaRepository.findById(dto.getMedidaId())
                .filter(m -> m.getEmpresa().getId().equals(empresaId))
                .orElseThrow(() -> new BadRequestException("La Medida no existe o no pertenece a la empresa"));

        if (dto.getProcedenciaId() != null) {
            procedenciaRepository.findById(dto.getProcedenciaId())
                    .filter(p -> p.getEmpresa().getId().equals(empresaId))
                    .orElseThrow(() -> new BadRequestException("La Procedencia no existe o no pertenece a la empresa"));
        }
    }

    public Producto mapearADominio(ProductoDTO dto) {
        Producto producto = new Producto();

        producto.setActivo(dto.isActivo());
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

        producto.setClase1(clase1Repository.findById(dto.getClase1Id()).orElseThrow());
        producto.setClase2(clase2Repository.findById(dto.getClase2Id()).orElseThrow());
        producto.setClase3(clase3Repository.findById(dto.getClase3Id()).orElseThrow());
        producto.setEmpresa(empresaRepository.findById(dto.getEmpresaId()).orElseThrow());
        producto.setMarca(marcaRepository.findById(dto.getMarcaId()).orElseThrow());
        producto.setMedida(medidaRepository.findById(dto.getMedidaId()).orElseThrow());
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