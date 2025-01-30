package com.example.demo.services;

import com.example.demo.dto.TareaDto;
import com.example.demo.entity.Caso;
import com.example.demo.entity.Evidencia;
import com.example.demo.entity.Tarea;
import com.example.demo.entity.Usuarios;
import com.example.demo.repositorio.CasoRepositorio;
import com.example.demo.repositorio.EvidenciaRepositorio;
import com.example.demo.repositorio.TareaRepositorio;
import com.example.demo.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TareaService implements ITareaService{

    @Autowired
    TareaRepositorio tareaRepositorio;
    @Autowired
    UsuarioRepositorio usuarioRepositorio;
    @Autowired
    CasoRepositorio casoRepositorio;
    @Autowired
    EvidenciaRepositorio evidenciaRepositorio;


    @Override
    public void crearTarea(TareaDto tareaDto) {

        Usuarios abogado= usuarioRepositorio.findById(tareaDto.getIdAbogado()).orElse(null);

        Caso caso= casoRepositorio.findById(tareaDto.getIdCaso()).orElse(null);
        if(abogado!=null && caso!= null){
            tareaRepositorio.saveAndFlush(new Tarea(tareaDto, caso, abogado));

        }
    }

    @Override
    public void actualizarTarea(TareaDto tareaDto) {
        Usuarios abogado= usuarioRepositorio.findById(tareaDto.getIdAbogado()).orElse(null);

        Caso caso= casoRepositorio.findById(tareaDto.getIdCaso()).orElse(null);

        Tarea tarea= tareaRepositorio.findById(tareaDto.getIdTarea()).orElse(null);
        if(abogado!=null && caso!= null && tarea!=null){
             tarea.setDescripcion(tareaDto.getDescripcion());
            tarea.setFechaLimite(tareaDto.getFechaLimite());
            tarea.setEstado(tareaDto.getEstado());
            tarea.setCaso(caso);
            tarea.setAbogado(abogado);

        }
    }

    @Override
    public List<TareaDto> listarTareas(Integer idAbogado) {
        List<TareaDto> listTareaDto= new ArrayList<>();
        List<Tarea> tareas= tareaRepositorio.findByAbogadoId(idAbogado);

        tareas.stream().forEach(tarea->{
            listTareaDto.add(new TareaDto(tarea));
        });

        return listTareaDto;
    }

    @Override
    public void eliminarTarea(Integer idTarea) {
        try {

            tareaRepositorio.deleteById(idTarea);

            List<Evidencia> evidenc=evidenciaRepositorio.findByTareaId(idTarea);

            if(evidenc!=null){
                evidenciaRepositorio.deleteAll(evidenc);
            }



        }catch(NoSuchElementException e) {
            // Manejo de la excepción cuando el ID no se encuentra
            System.out.println("No se encontró la tarea con ID: " + idTarea);
        } catch (Exception e) {
            // Manejo de otras excepciones
            System.out.println("Ocurrió un error al intentar eliminar la tarea: " + e.getMessage());
        }

    }
}
