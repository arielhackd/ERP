package com.techxperts.erp.usuario.service;

import com.techxperts.erp.empresa.model.Empresa;
import com.techxperts.erp.empresa.repository.EmpresaRepository;
import com.techxperts.erp.usuario.dto.UsuarioCreateDTO;
import com.techxperts.erp.usuario.dto.UsuarioDTO;
import com.techxperts.erp.usuario.model.Usuario;
import com.techxperts.erp.usuario.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final EmpresaRepository empresaRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioDTO crear(UsuarioCreateDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setUsername(dto.getUsername());
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        usuario.setNombre(dto.getNombre());
        usuario.setRol(dto.getRol());
        usuario.setActivo(dto.getActivo());

        Empresa empresa = empresaRepository.findById(dto.getEmpresaId())
                .orElseThrow(() -> new EntityNotFoundException("Empresa no encontrada"));
        usuario.setEmpresa(empresa);

        usuarioRepository.save(usuario);
        return mapearADTO(usuario);
    }

    public List<UsuarioDTO> listar() {
        return ((List<Usuario>) usuarioRepository.findAll()).stream()
                .map(this::mapearADTO)
                .collect(Collectors.toList());
    }

    public UsuarioDTO buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(this::mapearADTO)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
    }

    private UsuarioDTO mapearADTO(Usuario dominio) {
        return new UsuarioDTO(
                dominio.getId(),
                dominio.getUsername(),
                dominio.getNombre(),
                dominio.getRol(),
                dominio.getActivo(),
                dominio.getEmpresa().getId()
        );
    }

    //Metodo opcional si luego quieres editar usuarios
}