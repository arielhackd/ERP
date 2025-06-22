package com.techxperts.erp.tipoDocumento.controller;

import com.techxperts.erp.tipoDocumento.dto.TipoDocumentoDTO;
import com.techxperts.erp.tipoDocumento.service.TipoDocumentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tipo-documento")
@RequiredArgsConstructor
public class TipoDocumentoController {

    private final TipoDocumentoService tipoDocumentoService;

    @GetMapping
    public ResponseEntity<List<TipoDocumentoDTO>> obtenerTodos(@RequestParam Long empresaId) {
        List<TipoDocumentoDTO> tipoDocumentos = tipoDocumentoService.listarPorEmpresa(empresaId);
        return ResponseEntity.ok(tipoDocumentos);
    }

}
