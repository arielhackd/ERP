package com.techxperts.erp.tipoDocumento.controller;

import com.techxperts.erp.tipoDocumento.dto.TipoDocumentoDTO;
import com.techxperts.erp.tipoDocumento.model.TipoDocumento;
import com.techxperts.erp.tipoDocumento.service.TipoDocumentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/{empresaId}")
    public ResponseEntity<TipoDocumentoDTO> crearTipoDocumento(@PathVariable Long empresaId, @RequestBody TipoDocumentoDTO dto) {
        dto.setEmpresaId(empresaId);
        TipoDocumentoDTO respuesta = tipoDocumentoService.crear(dto);
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<TipoDocumentoDTO> actualizarTipoDocumento(@RequestBody TipoDocumentoDTO dto) {
        TipoDocumentoDTO respuesta = tipoDocumentoService.obtenerPorIdEmpresaId(dto.getId(), dto.getEmpresaId());
        dto.setId(respuesta.getId());
        dto.setEmpresaId(respuesta.getEmpresaId());
        return new ResponseEntity<>(tipoDocumentoService.actualizar(dto), HttpStatus.CREATED);
    }

}
