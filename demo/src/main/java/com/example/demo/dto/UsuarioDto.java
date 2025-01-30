package com.example.demo.dto;

import com.example.demo.entity.Usuarios;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor // Genera un constructor sin argumentos
@AllArgsConstructor
public class UsuarioDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer idUsuarios;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String ci;

    public UsuarioDto(Usuarios user) {
        this.idUsuarios = user.getIdUsuarios();
        this.nombre = user.getNombre();
        this.apellido = user.getApellido();
        this.email = user.getEmail();
        this.ci = user.getCi();
    }
}
