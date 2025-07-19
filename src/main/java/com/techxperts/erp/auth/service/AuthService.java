package com.techxperts.erp.auth.service;

import com.techxperts.erp.auth.dto.LoginRequest;
import com.techxperts.erp.auth.dto.LoginResponse;
import com.techxperts.erp.auth.util.JwtUtils;
import com.techxperts.erp.usuario.model.Usuario;
import com.techxperts.erp.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService{

    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Usuario usuario = usuarioRepository.findByUsernameAndActivoTrue(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario inactivo o no encontrado"));

        if (!encoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = jwtUtils.generarToken(usuario, request.getEmpresaId(), request.getPeriodoId());

        return new LoginResponse(token, usuario.getId(), usuario.getNombre(), request.getEmpresaId(), request.getPeriodoId());
    }
}