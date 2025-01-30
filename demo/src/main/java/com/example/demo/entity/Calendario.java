package com.example.demo.entity;

import com.example.demo.dto.CalendarioDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Calendario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEvento;

    private String titulo;
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "id_caso")
    private Caso caso;

    @ManyToOne
    @JoinColumn(name = "id_tipoEvento")
    private Tipo tipoEvento;

    public Calendario(CalendarioDto calendarioDto, Caso caso, Tipo tipoEvento) {
        this.titulo = calendarioDto.getTitulo();
        this.fecha = calendarioDto.getFecha();
        this.caso = caso;
        this.tipoEvento = tipoEvento;
    }
}
