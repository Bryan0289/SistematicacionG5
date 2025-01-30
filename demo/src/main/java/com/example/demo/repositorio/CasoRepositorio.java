package com.example.demo.repositorio;

import com.example.demo.entity.Caso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CasoRepositorio extends JpaRepository<Caso, Integer> {

    @Query("SELECT c FROM Caso c WHERE c.cliente.idUsuarios = :id OR c.abogado.idUsuarios = :id")
    List<Caso> findByClienteIdOrAbogadoId(Integer id);


    @Query("SELECT c FROM Caso c WHERE c.cliente.id = :idCliente")
    List<Caso> findByClienteId(@Param("idCliente") Integer idCliente);
}
