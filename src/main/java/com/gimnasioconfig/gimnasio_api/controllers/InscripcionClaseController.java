package com.gimnasioconfig.gimnasio_api.controllers;

import com.gimnasioconfig.gimnasio_api.dto.InscripcionClaseDTO;
import com.gimnasioconfig.gimnasio_api.models.InscripcionClase;
import com.gimnasioconfig.gimnasio_api.services.InscripcionClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionClaseController {

    @Autowired
    private InscripcionClaseService inscripcionClaseService;

    @GetMapping
    public List<InscripcionClaseDTO> getAllInscripciones() {
        return inscripcionClaseService.all()
                .stream()
                .map(InscripcionClaseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InscripcionClaseDTO> getInscripcionById(@PathVariable Long id) {
        Optional<InscripcionClase> inscripcion = inscripcionClaseService.findById(id);
        return inscripcion.map(ic -> ResponseEntity.ok(InscripcionClaseDTO.fromEntity(ic)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public InscripcionClase createInscripcion(@RequestBody InscripcionClase inscripcion) {
        return inscripcionClaseService.save(inscripcion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InscripcionClaseDTO> updateInscripcion(@PathVariable Long id, @RequestBody InscripcionClase inscripcionData) {
        try {
            InscripcionClase updatedInscripcion = inscripcionClaseService.updateInscripcion(id, inscripcionData);
            InscripcionClaseDTO dto = InscripcionClaseDTO.fromEntity(updatedInscripcion);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInscripcion(@PathVariable Long id) {
        inscripcionClaseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}