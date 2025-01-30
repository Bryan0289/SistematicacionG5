package com.example.demo.dto;

import com.example.demo.entity.Evidencia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor // Genera un constructor sin argumentos
@AllArgsConstructor
public class EvidenciaDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer idEvidencia;
    private String nombre;
    private String tarea;
    private Integer idTarea;

    public EvidenciaDto(Evidencia evi) {
        this.idEvidencia = evi.getId_evidencia();
        this.nombre =evi.getNombre();
        this.tarea = evi.getTarea().getDescripcion();
        this.idTarea = evi.getTarea().getIdTareas();
    }
}
