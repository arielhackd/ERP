package com.techxperts.erp.auth.service;

import com.techxperts.erp.auth.dto.LoginRequest;
import com.techxperts.erp.auth.dto.LoginResponse;
import com.techxperts.erp.auth.util.JwtUtils;
import com.techxperts.erp.usuario.model.Usuario;
import com.techxperts.erp.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepo;
    private final BCryptPasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    public LoginResponse login(LoginRequest request) {
        Usuario user = usuarioRepo.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!user.getActivo()) throw new RuntimeException("Usuario inactivo");

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = jwtUtils.generarToken(user.getId(), user.getEmpresa().getId());

        return new LoginResponse(token, user.getId(), user.getNombre(), user.getEmpresa().getId());
    }
}