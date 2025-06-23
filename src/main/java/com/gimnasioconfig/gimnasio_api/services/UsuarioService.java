package com.gimnasioconfig.gimnasio_api.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.fasterxml.jackson.databind.ser.std.UUIDSerializer;
import com.gimnasioconfig.gimnasio_api.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gimnasioconfig.gimnasio_api.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    public List<Usuario> all(){
        return usuarioRepo.findAll();
    }

    public List<Usuario> allActivos(){
        return usuarioRepo.findByActivoTrue();
    }

    public Optional<Usuario> findById(Long id){
        return usuarioRepo.findById(id);
    }

    public Usuario findByIdAndActivo(Long id){
        return usuarioRepo.findByIdAndActivoTrue(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado o inactivo"));
    }

    public Optional<Usuario> findByUsername(String username){
        return usuarioRepo.findByUsername(username);
    }

    public Usuario save(Usuario clase){
        return (Usuario) usuarioRepo.save(clase);
    }

    public void delete(Long id){
        usuarioRepo.deleteById(id);
    }

    public void desactivar(Long id){
        Usuario usuario = usuarioRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setActivo(false);
        usuarioRepo.save(usuario);
    }

    public Usuario update(Long id, Usuario usuarioData) {
        Usuario existing = usuarioRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Optional<Usuario> userWithSameUsername = usuarioRepo.findByUsername(usuarioData.getUsername());

        if (userWithSameUsername.isPresent() && !userWithSameUsername.get().getId().equals(id)) {
            throw new RuntimeException("Username en uso");
        }

        existing.setUsername(usuarioData.getUsername());
        existing.setPassword(usuarioData.getPassword());
        existing.setRol(usuarioData.getRol());
        existing.setActivo(usuarioData.getActivo());

        return usuarioRepo.save(existing);
    }

}

