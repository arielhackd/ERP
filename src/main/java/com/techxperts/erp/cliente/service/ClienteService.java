package com.techxperts.erp.cliente.service;

import com.techxperts.erp.cliente.dto.ClienteDTO;
import com.techxperts.erp.cliente.model.Cliente;
import com.techxperts.erp.cliente.repository.ClienteRepository;
import com.techxperts.erp.empresa.repository.EmpresaRepository;
import com.techxperts.erp.producto.repository.ListaPrecioRepository;
import com.techxperts.erp.util.model.DireccionFEL;
import com.techxperts.erp.util.service.DireccionFELService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final EmpresaRepository empresaRepository;
    private final ListaPrecioRepository listaPrecioRepository;
    private final DireccionFELService direccionFELService;

    public Cliente crearCliente(ClienteDTO dto) {
        DireccionFEL direccion = direccionFELService.crearDireccionFEL(dto.getDireccionFEL());
        Cliente cliente = mapearADominio(dto);
        cliente.setDireccionFEL(direccion);
        return clienteRepository.save(cliente);
    }

    public ClienteDTO obtenerClientePorId(Long id) {
        return clienteRepository.findById(id).map(this::mapearADTO).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));
    }

    public List<ClienteDTO> obtenerTodos() {
        return clienteRepository.findAll().stream()
                .map(this::mapearADTO)
                .collect(Collectors.toList());
    }

    public Cliente actualizarCliente(Long id, ClienteDTO dto) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));

        // No actualizamos el NIT
        cliente.setEmpresa(empresaRepository.findById(dto.getEmpresaId()).orElseThrow());
        cliente.setNombre(dto.getNombre());
        cliente.setNombreFactura(dto.getNombreFactura());
        cliente.setDireccion(dto.getDireccion());
        cliente.setDireccionEntrega(dto.getDireccionEntrega());
        cliente.setDireccionFactura(dto.getDireccionFactura());
        cliente.setTelefono(dto.getTelefono());
        cliente.setEmail(dto.getEmail());
        cliente.setObservaciones(dto.getObservaciones());
        cliente.setListaPrecio(listaPrecioRepository.findById(dto.getListaPrecioId()).orElseThrow());
        cliente.setBloquearCredito(dto.isBloquearCredito());
        cliente.setLimiteCredito(dto.getLimiteCredito());
        cliente.setDiasCredito(dto.getDiasCredito());
        cliente.setDescuento(dto.getDescuento());
        cliente.setFechaIngreso(dto.getFechaIngreso());
        cliente.setActivo(dto.isActivo());

        if (dto.getDireccionFEL() != null) {
            DireccionFEL direccionFEL = direccionFELService.mapearADominio(dto.getDireccionFEL());
            cliente.setDireccionFEL(direccionFEL);
        }

        return clienteRepository.save(cliente);
    }

    private Cliente mapearADominio(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setId(dto.getId());
        cliente.setEmpresa(empresaRepository.findById(dto.getEmpresaId()).orElseThrow());
        cliente.setNit(dto.getNit());
        cliente.setNombre(dto.getNombre());
        cliente.setNombreFactura(dto.getNombreFactura());
        cliente.setDireccion(dto.getDireccion());
        cliente.setDireccionEntrega(dto.getDireccionEntrega());
        cliente.setDireccionFactura(dto.getDireccionFactura());
        cliente.setTelefono(dto.getTelefono());
        cliente.setEmail(dto.getEmail());
        cliente.setObservaciones(dto.getObservaciones());
        cliente.setListaPrecio(listaPrecioRepository.findById(dto.getListaPrecioId()).orElseThrow());
        cliente.setBloquearCredito(dto.isBloquearCredito());
        cliente.setLimiteCredito(dto.getLimiteCredito());
        cliente.setDiasCredito(dto.getDiasCredito());
        cliente.setDescuento(dto.getDescuento());
        cliente.setFechaIngreso(dto.getFechaIngreso());
        cliente.setActivo(dto.isActivo());

        if (dto.getDireccionFEL() != null) {
            DireccionFEL direccionFEL = direccionFELService.mapearADominio(dto.getDireccionFEL());
            cliente.setDireccionFEL(direccionFEL);
        }

        return cliente;
    }

    private ClienteDTO mapearADTO(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setEmpresaId(cliente.getEmpresa().getId());
        dto.setNit(cliente.getNit());
        dto.setNombre(cliente.getNombre());
        dto.setNombreFactura(cliente.getNombreFactura());
        dto.setDireccion(cliente.getDireccion());
        dto.setDireccionEntrega(cliente.getDireccionEntrega());
        dto.setDireccionFactura(cliente.getDireccionFactura());
        dto.setTelefono(cliente.getTelefono());
        dto.setEmail(cliente.getEmail());
        dto.setObservaciones(cliente.getObservaciones());
        dto.setListaPrecioId(cliente.getListaPrecio().getId());
        dto.setBloquearCredito(cliente.isBloquearCredito());
        dto.setLimiteCredito(cliente.getLimiteCredito());
        dto.setDiasCredito(cliente.getDiasCredito());
        dto.setDescuento(cliente.getDescuento());
        dto.setFechaIngreso(cliente.getFechaIngreso());
        dto.setActivo(cliente.isActivo());

        if (cliente.getDireccionFEL() != null) {
            dto.setDireccionFEL(direccionFELService.mapearADTO(cliente.getDireccionFEL()));
        }

        return dto;
    }
}
