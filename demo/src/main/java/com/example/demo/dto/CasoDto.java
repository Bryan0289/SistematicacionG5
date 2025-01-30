package com.example.demo.dto;

import com.example.demo.entity.Caso;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor // Genera un constructor sin argumentos
@AllArgsConstructor
public class CasoDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer idCaso;
    private String codigo;
    private Date fechaInicio;
    private String descripcion;
    private String abogado;
    private String cliente;
    private String tipo;
    private Integer idTipo;
    private Integer idCliente;
    private Integer idAbogado;

    public CasoDto(Caso caso) {
        this.idCaso = caso.getIdCasos();
        this.codigo = caso.getCodigo();
        this.fechaInicio = caso.getFechaInicio();
        this.descripcion = caso.getDescripcion();
        this.abogado = caso.getAbogado().getNombre();
        this.idTipo = caso.getTipoCaso().getIdTipos();
        this.idCliente = caso.getCliente().getIdUsuarios();
        this.cliente= caso.getCliente().getNombre();
        this.tipo=caso.getTipoCaso().getNombre();
        this.idAbogado = caso.getAbogado().getIdUsuarios();
    }
}
