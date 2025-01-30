package com.example.demo.repositorio;

import com.example.demo.entity.Calendario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarioRepositorio extends JpaRepository<Calendario, Integer> {

    @Query("SELECT c FROM Calendario c WHERE c.caso.idCasos = :idCaso")
    List<Calendario> findEventosByCasoId(@Param("idCaso") Integer idCaso);
}
