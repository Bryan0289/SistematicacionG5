package com.example.demo.repositorio;

import com.example.demo.entity.UsuarioRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuariosRolRepositorio extends JpaRepository<UsuarioRol, Integer> {


    @Query("SELECT ur FROM UsuarioRol ur WHERE ur.usuario.idUsuarios = :idUsuario")
    List<UsuarioRol> findByUsuarioId(Integer idUsuario);
}
