package com.example.demo.entity;


import com.example.demo.dto.UsuarioDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuarios;

    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String ci;


    public Usuarios(UsuarioDto user) {
        this.nombre = user.getNombre();
        this.apellido = user.getApellido();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.ci = user.getCi();
    }
}
