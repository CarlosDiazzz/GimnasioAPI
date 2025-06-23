package com.gimnasioconfig.gimnasio_api.controllers;

import com.gimnasioconfig.gimnasio_api.dto.EntrenadorDTO;
import com.gimnasioconfig.gimnasio_api.models.Entrenador;
import com.gimnasioconfig.gimnasio_api.services.EntrenadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/entrenadores")
public class EntrenadorController {

    @Autowired
    private EntrenadorService entrenadorService;

    @GetMapping
    public List<EntrenadorDTO> getAllEntrenadores() {
        return entrenadorService.all()
                .stream()
                .map(EntrenadorDTO::fromEntity)
                .collect(Collectors.toList());
    }
    @GetMapping("/activos")
    public ResponseEntity<List<EntrenadorDTO>> listarActivos() {
        List<EntrenadorDTO> dtos = entrenadorService.allActivos().stream()
                .map(EntrenadorDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntrenadorDTO> getEntrenadorById(@PathVariable Long id) {
        Optional<Entrenador> entrenador = entrenadorService.findById(id);
        return entrenador.map(e -> ResponseEntity.ok(EntrenadorDTO.fromEntity(e)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/activos/{id}")
    public ResponseEntity<EntrenadorDTO> getActivoPorId(@PathVariable Long id) {
        try {
            Entrenador entrenador = entrenadorService.findByIdAndActivo(id);
            EntrenadorDTO dto = EntrenadorDTO.fromEntity(entrenador);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public Entrenador createEntrenador(@RequestBody Entrenador entrenador) {
        return entrenadorService.save(entrenador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntrenadorDTO> updateEntrenador(@PathVariable Long id, @RequestBody Entrenador entrenadorData) {
        try {
            Entrenador updatedEntrenador = entrenadorService.update(id, entrenadorData);
            EntrenadorDTO dto = EntrenadorDTO.fromEntity(updatedEntrenador);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/desactivar/{id}")
    public ResponseEntity<Void> desactivar(@PathVariable Long id) {
        entrenadorService.desactivar(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntrenador(@PathVariable Long id) {
        entrenadorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
