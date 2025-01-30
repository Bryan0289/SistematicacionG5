package com.example.demo.dto;

import com.example.demo.entity.Caso;
import com.example.demo.entity.Tarea;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor // Genera un constructor sin argumentos
@AllArgsConstructor
public class TareaDto implements Serializable {
        private static final long serialVersionUID = 1L;

        private Integer idTarea;
    private String descripcion;
    private Date fechaLimite;
    private String estado;
    private String caso;
    private String abogado;
    private Integer idAbogado;
    private Integer idCaso;

    public TareaDto(Tarea tarea) {
        this.idTarea = tarea.getIdTareas();
        this.descripcion = tarea.getDescripcion();
        this.fechaLimite = tarea.getFechaLimite();
        this.estado = tarea.getEstado();
        this.caso = tarea.getCaso().getDescripcion();
        this.abogado =tarea.getAbogado().getNombre();
        this.idAbogado = tarea.getAbogado().getIdUsuarios();
        this.idCaso = tarea.getCaso().getIdCasos();
    }
}
