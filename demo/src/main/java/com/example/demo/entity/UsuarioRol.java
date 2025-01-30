package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioRol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUserRol;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private Usuarios usuario;

    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Rol rol;
}
