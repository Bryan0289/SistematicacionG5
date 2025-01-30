package com.example.demo.dto;

import com.example.demo.entity.Calendario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor // Genera un constructor sin argumentos
@AllArgsConstructor
public class CalendarioDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String titulo;
    private Date fecha;

    private Integer idCaso;
    private String caso;

    private Integer idTipo;
    private String tipo;

    public CalendarioDto(Calendario cal) {
        this.titulo = cal.getTitulo();
        this.fecha = cal.getFecha();
        this.idCaso = cal.getCaso().getIdCasos();
        this.caso = cal.getCaso().getDescripcion();
        this.idTipo = cal.getTipoEvento().getIdTipos();
        this.tipo = cal.getTipoEvento().getNombre();
    }
}
