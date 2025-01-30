package com.example.demo.controller;

import com.example.demo.dto.TareaDto;
import com.example.demo.services.ITareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/tarea")
@CrossOrigin(origins = "http://localhost:4200")
public class TareaController {

    @Autowired
    private ITareaService tareaService;

    @PostMapping
    public ResponseEntity<String> crearTarea(@RequestBody TareaDto tareaDto) {
        try {
            tareaService.crearTarea(tareaDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Tarea creada con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la tarea");
        }
    }

    @PutMapping
    public ResponseEntity<String> actualizarTarea(@RequestBody TareaDto tareaDto) {
        try {
            tareaService.actualizarTarea(tareaDto);
            return ResponseEntity.ok("Tarea actualizada con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar la tarea");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTarea(@PathVariable Integer id) {
        try {
            tareaService.eliminarTarea(id);
            return ResponseEntity.ok("Tarea eliminada con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la tarea");
        }
    }

    @GetMapping("/abogado/{idAbogado}")
    public ResponseEntity<List<TareaDto>> listarTarea(@PathVariable Integer idAbogado) {

            List<TareaDto> tareas = tareaService.listarTareas(idAbogado);
            if (tareas.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(tareas);
            }
            return ResponseEntity.ok(tareas);

    }

}
