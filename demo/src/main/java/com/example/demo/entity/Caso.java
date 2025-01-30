package com.example.demo.entity;

import com.example.demo.dto.CasoDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Caso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCasos;

    private String codigo;
    private Date fechaInicio;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Usuarios cliente;

    @ManyToOne
    @JoinColumn(name = "id_abogado")
    private Usuarios abogado;

    @ManyToOne
    @JoinColumn(name = "id_tipoCaso")
    private Tipo tipoCaso;

    @OneToMany(mappedBy = "caso")
    private List<Tarea> tareas;

    @OneToMany(mappedBy = "caso")
    private List<Calendario> eventosCalendario;

    public Caso(CasoDto caso, Usuarios cliente, Usuarios abogado, Tipo tipoCaso ) {
        this.codigo = caso.getCodigo();
        this.fechaInicio = caso.getFechaInicio();
        this.descripcion = caso.getDescripcion();
        this.cliente = cliente;
        this.abogado = abogado;
        this.tipoCaso = tipoCaso;
    }
}
