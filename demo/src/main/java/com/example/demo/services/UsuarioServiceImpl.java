package com.example.demo.services;

import com.example.demo.dto.UsuarioDto;
import com.example.demo.entity.*;
import com.example.demo.repositorio.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class UsuarioServiceImpl implements IUsuarioService{
    @Autowired
    private UsuarioRepositorio usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private UsuariosRolRepositorio usuarioRolRepository;
    @Autowired
    private CasoRepositorio casoRepositorio;
    @Autowired
    private TareaRepositorio tareaRepositorio;
    @Autowired
    private EvidenciaRepositorio evidenciaRepositorio;

    @Override
    public UsuarioDto crearUsuario(UsuarioDto usuario) {
       usuarioRepository.save(new Usuarios(usuario));
        return usuario;
    }
    @Override
    public UsuarioDto actualizarUsuario(Integer id, UsuarioDto usuarioActualizado) {
        Optional<Usuarios> usuarioExistente = usuarioRepository.findById(id);
        if (usuarioExistente.isPresent()) {
            Usuarios usuario = usuarioExistente.get();
            usuario.setNombre(usuarioActualizado.getNombre());
            usuario.setApellido(usuarioActualizado.getApellido());
            usuario.setEmail(usuarioActualizado.getEmail());
            usuario.setPassword(usuarioActualizado.getPassword());
            usuario.setCi(usuarioActualizado.getCi());
            usuarioRepository.save(usuario);

            return usuarioActualizado;
        }
        throw new RuntimeException("Usuario no encontrado");
    }
    @Override
    public void eliminarUsuario(Integer id) {
        try{

            usuarioRepository.deleteById(id);

            List<UsuarioRol> userRol=usuarioRolRepository.findByUsuarioId(id);

            usuarioRolRepository.deleteAll(userRol);

            List<Caso> casos=casoRepositorio.findByClienteIdOrAbogadoId(id);

            if(casos!=null){

                List<Integer> idCasosList=new ArrayList<>();

                casos.stream().forEach(caso->{
                    idCasosList.add(caso.getIdCasos());
                });

                List<Tarea> tareas= tareaRepositorio.findByCasoIds(idCasosList);
                if(tareas!=null) {

                    List<Integer> idTareasList = new ArrayList<>();
                    tareas.stream().forEach(tarea -> {
                        idTareasList.add(tarea.getIdTareas());
                    });

                    List<Evidencia> evidenc = evidenciaRepositorio.findByTareaIdIn(idTareasList);

                    if (evidenc != null) {
                        evidenciaRepositorio.deleteAll(evidenc);
                    }
                    tareaRepositorio.deleteAll(tareas);

                }


                casoRepositorio.deleteAll(casos);
            }


        }catch (NoSuchElementException e) {
            // Manejo de la excepción cuando el ID no se encuentra
            System.out.println("No se encontró el usuario con ID: " + id);
        } catch (Exception e) {
            // Manejo de otras excepciones
            System.out.println("Ocurrió un error al intentar eliminar el usuario: " + e.getMessage());
        }
    }
    @Override
    public void asignarRolUsuario(Integer idUsuario, Integer idRol) {
        Optional<Usuarios> usuario = usuarioRepository.findById(idUsuario);
        Optional<Rol> rol = rolRepository.findById(idRol);

        if (usuario.isPresent() && rol.isPresent()) {
            UsuarioRol usuarioRol = new UsuarioRol();
            usuarioRol.setUsuario(usuario.get());
            usuarioRol.setRol(rol.get());
            usuarioRolRepository.save(usuarioRol);
        } else {
            throw new RuntimeException("Usuario o Rol no encontrado");
        }
    }
    @Override
    public List<UsuarioDto> listarUsuarios() {
        List<UsuarioDto> usuariosDto= new ArrayList<>();
        List<Usuarios> usuariosList= usuarioRepository.findAll();
        usuariosList.stream().forEach(user->{
            usuariosDto.add(new UsuarioDto(user));
        });
        return usuariosDto;
    }
}
