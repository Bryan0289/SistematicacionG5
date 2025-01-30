package com.example.demo.services;

import com.example.demo.dto.EvidenciaDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IEvidenciaService {

    void crearEvidencia(EvidenciaDto evidenciaDto, MultipartFile archivo);

    void actualizarEvidencia(EvidenciaDto evidenciaDto, MultipartFile archivo);

    List<EvidenciaDto> listarEvidencias(Integer idTarea);

    void eliminarEvidencia(Integer idEvidencia);
}
