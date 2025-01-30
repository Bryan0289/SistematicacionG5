package com.example.demo.controller;

import com.example.demo.dto.CasoDto;
import com.example.demo.services.ICasosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/caso")
@CrossOrigin(origins = "http://localhost:4200")
public class CasoController {


    @Autowired
    private ICasosService casosService;

    @PostMapping
    public ResponseEntity<String> crearCasos(@RequestBody CasoDto casoDto) {
        try {
            casosService.crearCaso(casoDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Caso creado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear el caso: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<String> actualizarCasos(@RequestBody CasoDto casoDto) {
        try {
            casosService.actualizarCaso(casoDto);
            return ResponseEntity.ok("Caso actualizado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar el caso: " + e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCasos(@PathVariable Integer id) {
        try {
            casosService.eliminarCaso(id);
            return ResponseEntity.ok("Caso eliminado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar el caso: " + e.getMessage());
        }
    }

    @GetMapping("/{idAbogado}")
    public ResponseEntity<List<CasoDto>> listarCasos(@PathVariable Integer idAbogado) {
        List<CasoDto> casos = casosService.listarCasos(idAbogado);

        return ResponseEntity.ok(casos);
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<CasoDto>> listarCasosByCliente(@PathVariable Integer idCliente) {
        List<CasoDto> casos = casosService.listarCasosCliente(idCliente);

        return ResponseEntity.ok(casos);
    }



}
