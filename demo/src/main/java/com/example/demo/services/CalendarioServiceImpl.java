package com.example.demo.services;

import com.example.demo.dto.CalendarioDto;
import com.example.demo.entity.Calendario;
import com.example.demo.entity.Caso;
import com.example.demo.entity.Tipo;
import com.example.demo.repositorio.CalendarioRepositorio;
import com.example.demo.repositorio.CasoRepositorio;
import com.example.demo.repositorio.TipoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalendarioServiceImpl implements ICalendarioService{

    @Autowired
    CalendarioRepositorio calendarioRepositorio;
    @Autowired
    TipoRepositorio tipoRepositorio;
    @Autowired
    CasoRepositorio casoRepositorio;

    @Override
    public void crearCalendario(CalendarioDto calendario) {
        Tipo tipo= tipoRepositorio.findById(calendario.getIdTipo()).orElse(null);
        Caso caso= casoRepositorio.findById(calendario.getIdCaso()).orElse(null);
        if(tipo!=null || caso!=null){

            calendarioRepositorio.saveAndFlush(new Calendario(calendario,caso ,tipo));
        }
    }

    @Override
    public List<CalendarioDto> listarCalendario(Integer idCaso) {
        List<CalendarioDto> calendarioDtoList= new ArrayList<>();
        List<Calendario> calendarioList= calendarioRepositorio.findEventosByCasoId(idCaso);

        calendarioList.stream().forEach(cal->{
            calendarioDtoList.add(new CalendarioDto(cal));
        });

        return calendarioDtoList;
    }
}
