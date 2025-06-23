package com.gimnasioconfig.gimnasio_api.controllers;

import com.gimnasioconfig.gimnasio_api.dto.RutinaDTO;
import com.gimnasioconfig.gimnasio_api.models.Rutina;
import com.gimnasioconfig.gimnasio_api.services.RutinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rutinas")
public class RutinaController {

    @Autowired
    private RutinaService rutinaService;

    @GetMapping
    public List<RutinaDTO> getAllRutinas() {
        return rutinaService.all()
                .stream()
                .map(RutinaDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RutinaDTO> getRutinaById(@PathVariable Long id) {
        Optional<Rutina> rutina = rutinaService.findById(id);
        return rutina.map(r -> ResponseEntity.ok(RutinaDTO.fromEntity(r)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Rutina createRutina(@RequestBody Rutina rutina) {
        return rutinaService.save(rutina);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RutinaDTO> updateRutina(@PathVariable Long id, @RequestBody Rutina rutinaData) {
        try {
            Rutina updatedRutina = rutinaService.updateRutina(id, rutinaData);
            RutinaDTO dto = RutinaDTO.fromEntity(updatedRutina);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRutina(@PathVariable Long id) {
        rutinaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}