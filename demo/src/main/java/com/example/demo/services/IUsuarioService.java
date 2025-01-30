package com.example.demo.services;

import com.example.demo.dto.UsuarioDto;

import java.util.List;

public interface IUsuarioService {

    UsuarioDto crearUsuario(UsuarioDto usuario);

    UsuarioDto actualizarUsuario(Integer id, UsuarioDto usuarioActualizado);

    void eliminarUsuario(Integer id);

    void asignarRolUsuario(Integer idUsuario, Integer idRol);

    List<UsuarioDto> listarUsuarios();
}
