package com.example.demo.services;

import com.example.demo.dto.EvidenciaDto;
import com.example.demo.entity.Evidencia;
import com.example.demo.entity.Tarea;
import com.example.demo.repositorio.EvidenciaRepositorio;
import com.example.demo.repositorio.TareaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EvidenciaServicioImpl implements IEvidenciaService{

    @Autowired
    EvidenciaRepositorio evidenciaRepositorio;
    @Autowired
    TareaRepositorio tareaRepositorio;

    @Override
    public void crearEvidencia(EvidenciaDto evidenciaDto, MultipartFile archivo) {
        Tarea tarea= tareaRepositorio.findById(evidenciaDto.getIdTarea()).orElse(null);
        if(tarea!=null){
            String rutaDirectorio = "uploads/evidencias/";
            File directorio = new File(rutaDirectorio);
            if (!directorio.exists()) {
                directorio.mkdirs(); // Crear directorio si no existe
            }

            String rutaArchivo = rutaDirectorio + evidenciaDto.getNombre() + "_" + archivo.getOriginalFilename();

            try {
                archivo.transferTo(new File(rutaArchivo));
                evidenciaRepositorio.save(new Evidencia(evidenciaDto.getNombre(),rutaArchivo, tarea));
            } catch (IOException e) {
                throw new RuntimeException("Error al guardar el archivo: " + e.getMessage());
            }


        }
    }

    @Override
    public void actualizarEvidencia(EvidenciaDto evidenciaDto, MultipartFile archivo) {
        Tarea tarea= tareaRepositorio.findById(evidenciaDto.getIdTarea()).orElse(null);
        Evidencia evidencia= evidenciaRepositorio.findById(evidenciaDto.getIdEvidencia()).orElse(null);
        if(evidencia!=null && tarea!=null){
            String rutaDirectorio = "uploads/evidencias/";
            File directorio = new File(rutaDirectorio);
            if (!directorio.exists()) {
                directorio.mkdirs(); // Crear directorio si no existe
            }
            File archivoAnterior = new File(evidencia.getUrl());
            if (archivoAnterior.exists()) {
                archivoAnterior.delete();
            }

            evidencia.setNombre(evidenciaDto.getNombre());
            evidencia.setTarea(tarea);

            String rutaArchivo = rutaDirectorio + evidenciaDto.getNombre() + "_" + archivo.getOriginalFilename();

            try {
                archivo.transferTo(new File(rutaArchivo));
            } catch (IOException e) {
                throw new RuntimeException("Error al guardar el archivo: " + e.getMessage());
            }


        }
    }

    @Override
    public List<EvidenciaDto> listarEvidencias(Integer idTarea) {
        List<EvidenciaDto> evidenciaDto=new ArrayList<>();
        List<Evidencia> evidenciaList=evidenciaRepositorio.findByTareaId(idTarea);
        evidenciaList.stream().forEach(evi->{
            evidenciaDto.add(new EvidenciaDto(evi));
        });

        return evidenciaDto;

    }

    @Override
    public void eliminarEvidencia(Integer idEvidencia) {
        Evidencia evidencia= evidenciaRepositorio.findById(idEvidencia).orElse(null);
        if(evidencia!=null) {
            File archivoAnterior = new File(evidencia.getUrl());
            if (archivoAnterior.exists()) {
                archivoAnterior.delete();
            }
            evidenciaRepositorio.deleteById(idEvidencia);
        }

    }
}
