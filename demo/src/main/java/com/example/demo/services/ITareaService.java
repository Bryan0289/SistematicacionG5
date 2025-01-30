package com.example.demo.services;

import com.example.demo.dto.TareaDto;

import java.util.List;

public interface ITareaService {

    void crearTarea(TareaDto TareaDto);

    void actualizarTarea(TareaDto TareaDto);

    List<TareaDto> listarTareas(Integer idAbogado);

    void eliminarTarea(Integer idTarea);
}
