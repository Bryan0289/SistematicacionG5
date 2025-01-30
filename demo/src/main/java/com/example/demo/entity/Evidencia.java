package com.example.demo.entity;


import com.example.demo.dto.EvidenciaDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Evidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_evidencia;

    private String nombre;

    private String url;

    @ManyToOne
    @JoinColumn(name = "id_tarea", nullable = false)
    private Tarea tarea;

    public Evidencia(String nombre, String url, Tarea tarea) {
        this.nombre = nombre;
        this.url = url;
        this.tarea = tarea;
    }
}
