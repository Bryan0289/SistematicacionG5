package com.example.demo.entity;

import com.example.demo.dto.TareaDto;
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
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTareas;

    private String descripcion;
    private Date fechaLimite;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_caso")
    private Caso caso;

    @ManyToOne
    @JoinColumn(name = "id_abogado")
    private Usuarios abogado;

    @OneToMany(mappedBy = "tarea", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Evidencia> evidencias;

    public Tarea(TareaDto tarea, Caso caso, Usuarios abogado) {
        this.descripcion = tarea.getDescripcion();
        this.fechaLimite = tarea.getFechaLimite();
        this.estado = tarea.getEstado();
        this.caso = caso;
        this.abogado = abogado;
    }
}
