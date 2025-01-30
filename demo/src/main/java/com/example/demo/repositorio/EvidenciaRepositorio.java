package com.example.demo.repositorio;

import com.example.demo.entity.Evidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EvidenciaRepositorio extends JpaRepository<Evidencia, Integer> {

    @Query("SELECT e FROM Evidencia e WHERE e.tarea.idTareas = :idTarea")
    List<Evidencia> findByTareaId(Integer idTarea);

    @Query("SELECT e FROM Evidencia e WHERE e.tarea.idTareas IN :tareaIds")
    List<Evidencia> findByTareaIdIn(List<Integer> tareaIds);

}
