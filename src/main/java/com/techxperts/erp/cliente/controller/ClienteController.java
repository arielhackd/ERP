package com.techxperts.erp.cliente.controller;

import com.techxperts.erp.cliente.dto.ClienteDTO;
import com.techxperts.erp.cliente.model.Cliente;
import com.techxperts.erp.cliente.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@Valid @RequestBody ClienteDTO dto) {
        Cliente creado = clienteService.crearCliente(dto);
        return ResponseEntity.ok(creado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obtenerClientePorId(@PathVariable Long id) {
        ClienteDTO dto = clienteService.obtenerClientePorId(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> obtenerTodos() {
        List<ClienteDTO> lista = clienteService.obtenerTodos();
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @Valid @RequestBody ClienteDTO dto) {
        Cliente actualizado = clienteService.actualizarCliente(id, dto);
        return ResponseEntity.ok(actualizado);
    }

}
