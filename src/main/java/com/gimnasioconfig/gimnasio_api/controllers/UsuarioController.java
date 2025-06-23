package com.gimnasioconfig.gimnasio_api.controllers;

import com.gimnasioconfig.gimnasio_api.dto.UsuarioDTO;
import com.gimnasioconfig.gimnasio_api.models.Usuario;
import com.gimnasioconfig.gimnasio_api.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioDTO> getAllUsuarios() {
        return usuarioService.all()
                .stream()
                .map(UsuarioDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/activos")
    public ResponseEntity<List<UsuarioDTO>> listarActivos() {
        List<UsuarioDTO> dtos = usuarioService.allActivos().stream()
                .map(UsuarioDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.findById(id);
        return usuario.map(u -> ResponseEntity.ok(UsuarioDTO.fromEntity(u)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/activos/{id}")
    public ResponseEntity<UsuarioDTO> getActivoPorId(@PathVariable Long id) {
        try {
            Usuario usuario = usuarioService.findByIdAndActivo(id);
            UsuarioDTO dto = UsuarioDTO.fromEntity(usuario);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UsuarioDTO> getUsuarioByUsername(@PathVariable String username) {
        Optional<Usuario> usuario = usuarioService.findByUsername(username);
        return usuario.map(u -> ResponseEntity.ok(UsuarioDTO.fromEntity(u)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuarioData) {
        try {
            Usuario updatedUsuario = usuarioService.update(id, usuarioData);
            UsuarioDTO dto = UsuarioDTO.fromEntity(updatedUsuario);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            System.out.println(e);
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/desactivar/{id}")
    public ResponseEntity<Void> desactivar(@PathVariable Long id) {
        usuarioService.desactivar(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

