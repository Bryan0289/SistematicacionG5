package com.example.demo.services;

import com.example.demo.dto.CalendarioDto;

import java.util.List;

public interface ICalendarioService {

    void crearCalendario(CalendarioDto calendario);

    List<CalendarioDto> listarCalendario(Integer idCaso);
}
