package com.example.demo.repositorio;

import com.example.demo.entity.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoRepositorio extends JpaRepository<Tipo, Integer> {
}
