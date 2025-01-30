package com.example.demo.controller;

import com.example.demo.dto.EvidenciaDto;
import com.example.demo.services.IEvidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/evidencia")
public class EvidenciaController {

    @Autowired
    private IEvidenciaService evidenciaService;

    @PostMapping
    public ResponseEntity<String> crearEvidencia(
            @RequestPart("evidencia") EvidenciaDto evidenciaDto,
            @RequestPart("archivo") MultipartFile archivo) {
        try {
            evidenciaService.crearEvidencia(evidenciaDto, archivo);
            return ResponseEntity.status(HttpStatus.CREATED).body("Evidencia creada con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la evidencia");
        }
    }

    @PutMapping
    public ResponseEntity<String> actualizarEvidencia(
            @RequestPart("evidencia") EvidenciaDto evidenciaDto,
            @RequestPart("archivo") MultipartFile archivo) {
        try {
            evidenciaService.actualizarEvidencia(evidenciaDto, archivo);
            return ResponseEntity.ok("Evidencia actualizada con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar la evidencia");
        }
    }

    @GetMapping("/{idTarea}")
    public ResponseEntity<List<EvidenciaDto>> listarEvidencias(@PathVariable Integer idTarea) {
        try {
            List<EvidenciaDto> evidencias = evidenciaService.listarEvidencias(idTarea);
            if (evidencias.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(evidencias);
            }
            return ResponseEntity.ok(evidencias);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.emptyList());
        }
    }

    @DeleteMapping("/{idEvidencia}")
    public ResponseEntity<String> eliminarEvidencia(@PathVariable Integer idEvidencia) {
        try {
            evidenciaService.eliminarEvidencia(idEvidencia);
            return ResponseEntity.ok("Evidencia eliminada con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la evidencia");
        }
    }
}
