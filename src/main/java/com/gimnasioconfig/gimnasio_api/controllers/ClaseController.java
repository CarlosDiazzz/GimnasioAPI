package com.gimnasioconfig.gimnasio_api.controllers;

import com.gimnasioconfig.gimnasio_api.dto.ClaseDTO;
import com.gimnasioconfig.gimnasio_api.models.Clase;
import com.gimnasioconfig.gimnasio_api.services.ClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clases")
public class ClaseController {

    @Autowired
    private ClaseService claseService;

    @GetMapping
    public List<ClaseDTO> getAllClases() {
        return claseService.all()
                .stream()
                .map(ClaseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/activos")
    public ResponseEntity<List<ClaseDTO>> listarActivos() {
        List<ClaseDTO> dtos = claseService.allActivos().stream()
                .map(ClaseDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClaseDTO> getClaseById(@PathVariable Long id) {
        Optional<Clase> clase = claseService.findById(id);
        return clase.map(c -> ResponseEntity.ok(ClaseDTO.fromEntity(c)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/activos/{id}")
    public ResponseEntity<ClaseDTO> getActivoPorId(@PathVariable Long id) {
        try {
            Clase clase = claseService.findByIdAndActivo(id);
            ClaseDTO dto = ClaseDTO.fromEntity(clase);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public Clase createClase(@RequestBody Clase clase) {
        return claseService.save(clase);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClaseDTO> updateClase(@PathVariable Long id, @RequestBody Clase claseData) {
        try {
            Clase updatedClase = claseService.update(id, claseData);
            ClaseDTO dto = ClaseDTO.fromEntity(updatedClase);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/desactivar/{id}")
    public ResponseEntity<Void> desactivar(@PathVariable Long id) {
        claseService.desactivar(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClase(@PathVariable Long id) {
        claseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
