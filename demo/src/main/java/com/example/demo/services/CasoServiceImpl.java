package com.example.demo.services;

import com.example.demo.dto.CasoDto;
import com.example.demo.entity.*;
import com.example.demo.repositorio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CasoServiceImpl implements ICasosService{
    @Autowired
    private CasoRepositorio casoRepositorio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private TipoRepositorio tipoRepositorio;
    @Autowired
    private TareaRepositorio tareaRepositorio;
    @Autowired
    private EvidenciaRepositorio evidenciaRepositorio;

    @Override
    public void crearCaso(CasoDto casoDto) {

        Usuarios cliente= usuarioRepositorio.findById(casoDto.getIdCliente()).orElse(null);

        Usuarios abogado= usuarioRepositorio.findById(casoDto.getIdAbogado()).orElse(null);

        Tipo tipo= tipoRepositorio.findById(casoDto.getIdTipo()).orElse(null);

        if(cliente!= null && abogado!= null && tipo!= null) {


            casoRepositorio.save(new Caso(casoDto, cliente, abogado, tipo));
        }
    }

    @Override
    public void actualizarCaso(CasoDto casoDto) {
        Usuarios cliente= usuarioRepositorio.findById(casoDto.getIdCliente()).orElse(null);

        Usuarios abogado= usuarioRepositorio.findById(casoDto.getIdAbogado()).orElse(null);

        Tipo tipo= tipoRepositorio.findById(casoDto.getIdTipo()).orElse(null);

        Caso caso= casoRepositorio.findById(casoDto.getIdCaso()).orElse(null);

        if(caso!=null){
            caso.setCodigo(caso.getCodigo());
            caso.setFechaInicio(caso.getFechaInicio());
            caso.setDescripcion(caso.getDescripcion());
            caso.setCliente(cliente);
            caso.setAbogado(abogado);
            caso.setTipoCaso(tipo);
        }
    }

    @Override
    public List<CasoDto> listarCasos(Integer Abogado) {
        List<CasoDto> casosDto= new ArrayList<>();
        List<Caso> casos= casoRepositorio.findAll().reversed();
        casos.stream().forEach(caso->{
            casosDto.add(new CasoDto(caso));
        });

        return casosDto;
    }
    @Override
    public List<CasoDto> listarCasosCliente(Integer idCliente) {
        List<CasoDto> casosDto= new ArrayList<>();
        List<Caso> casos= casoRepositorio.findByClienteIdOrAbogadoId(idCliente).reversed();
        casos.stream().forEach(caso->{
            casosDto.add(new CasoDto(caso));
        });

        return casosDto;
    }

    @Override
    public void eliminarCaso(Integer idCaso) {
        try {
            casoRepositorio.deleteById(idCaso);

            List<Tarea> tareas= tareaRepositorio.findByCasoId(idCaso);
            if(tareas!=null){

            List<Integer> idTareasList= new ArrayList<>();
            tareas.stream().forEach(tarea->{
                idTareasList.add(tarea.getIdTareas());
            });

            List<Evidencia> evidenc=evidenciaRepositorio.findByTareaIdIn(idTareasList);

            if(evidenc!=null){
                evidenciaRepositorio.deleteAll(evidenc);
            }
            tareaRepositorio.deleteAll(tareas);
            }

        }catch (NoSuchElementException e) {
            // Manejo de la excepción cuando el ID no se encuentra
            System.out.println("No se encontró el caso con ID: " + idCaso);
        } catch (Exception e) {
            // Manejo de otras excepciones
            System.out.println("Ocurrió un error al intentar eliminar el caso: " + e.getMessage());
        }
    }
}
