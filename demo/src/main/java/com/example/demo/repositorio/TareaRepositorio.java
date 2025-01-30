package com.example.demo.repositorio;

import com.example.demo.entity.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface TareaRepositorio extends JpaRepository<Tarea, Integer> {

        @Query("SELECT t FROM Tarea t WHERE t.abogado.idUsuarios = :idAbogado")
        List<Tarea> findByAbogadoId(Integer idAbogado);

        @Query("SELECT t FROM Tarea t WHERE t.caso.idCasos = :idCaso")
        List<Tarea> findByCasoId(Integer idCaso);

        @Query("SELECT t FROM Tarea t WHERE t.caso.idCasos IN :casoIds")
        List<Tarea> findByCasoIds(List<Integer> casoIds);
}
