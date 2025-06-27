package com.techxperts.erp.usuario.controller;

import com.techxperts.erp.usuario.dto.UsuarioCreateDTO;
import com.techxperts.erp.usuario.dto.UsuarioDTO;
import com.techxperts.erp.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public UsuarioDTO crear(@Valid @RequestBody UsuarioCreateDTO dto) {
        return usuarioService.crear(dto);
    }

    @GetMapping
    public List<UsuarioDTO> listar() {
        return usuarioService.listar();
    }

    @GetMapping("/{id}")
    public UsuarioDTO buscar(@PathVariable Long id) {
        return usuarioService.buscarPorId(id);
    }

}
