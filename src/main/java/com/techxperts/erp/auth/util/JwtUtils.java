package com.techxperts.erp.auth.util;

import com.techxperts.erp.usuario.model.Usuario;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {

    private final String jwtSecret = "secretoSuperSecretoParaFirmarElToken1234567890";
    private final int jwtExpirationMs = 86400000; // 1 día

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String generateToken(Long usuarioId, Long empresaId) {
        return Jwts.builder()
                .setSubject(String.valueOf(usuarioId))
                .claim("empresaId", empresaId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generarToken(Usuario usuario, Long empresaId, Long periodoId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("empresaId", empresaId);
        claims.put("periodoId", periodoId);
        claims.put("usuarioId", usuario.getId());
        claims.put("nombre", usuario.getNombre());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(usuario.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 1 día
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public Long getUsuarioId(String token) {
        Claims claims = parseToken(token);
        return Long.parseLong(claims.getSubject());
    }

    public Long getEmpresaId(String token) {
        Claims claims = parseToken(token);
        return claims.get("empresaId", Long.class);
    }

    public boolean validarToken(String token) {
        try {
            parseToken(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
