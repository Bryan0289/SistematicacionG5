package com.example.demo.controller;

import com.example.demo.dto.CalendarioDto;
import com.example.demo.services.ICalendarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/calendario")
public class CalendarioController {

    @Autowired
    ICalendarioService calendarioService;

    @PostMapping("/crear")
    public ResponseEntity<String> crearCalendario(@RequestBody CalendarioDto calendarioDto) {
        try {
            calendarioService.crearCalendario(calendarioDto);
            return ResponseEntity.ok("Calendario creado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear el calendario: " + e.getMessage());
        }
    }

    @GetMapping("/listar/{idCaso}")
    public ResponseEntity<List<CalendarioDto>> listarCalendario(@PathVariable Integer idCaso) {
        try {
            List<CalendarioDto> calendarios = calendarioService.listarCalendario(idCaso);
            if (calendarios.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(calendarios);
            }
            return ResponseEntity.ok(calendarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.emptyList());
        }
    }

}
