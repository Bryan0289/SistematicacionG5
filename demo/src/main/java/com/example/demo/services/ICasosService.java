package com.example.demo.services;

import com.example.demo.dto.CasoDto;

import java.util.List;

public interface ICasosService {

    void crearCaso(CasoDto casoDto);

    void actualizarCaso(CasoDto casoDto);

    List<CasoDto> listarCasos(Integer idAbogado);

    List<CasoDto> listarCasosCliente(Integer idCliente);

    void eliminarCaso(Integer idCaso);
}
