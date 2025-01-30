package com.example.demo.repositorio;

import com.example.demo.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuarios, Integer> {
}
