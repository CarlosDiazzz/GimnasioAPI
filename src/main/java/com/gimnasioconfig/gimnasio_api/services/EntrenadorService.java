package com.gimnasioconfig.gimnasio_api.services;

import com.gimnasioconfig.gimnasio_api.models.Entrenador;
import com.gimnasioconfig.gimnasio_api.repositories.EntrenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntrenadorService {

    @Autowired
    private EntrenadorRepository entrenadorRepository;

    public List<Entrenador> all(){
        return entrenadorRepository.findAll();
    }

    public List<Entrenador> allActivos(){
        return entrenadorRepository.findByActivoTrue();
    }

    public Optional<Entrenador> findById(Long id){
        return entrenadorRepository.findById(id);
    }

    public Entrenador findByIdAndActivo(Long id){
        return entrenadorRepository.findByIdAndActivoTrue(id)
                .orElseThrow(() -> new RuntimeException("Entrenador no encontrado o inactivo"));
    }

    public Entrenador save(Entrenador entrenador){
        return (Entrenador) entrenadorRepository.save(entrenador);
    }

    public void delete(Long id){
        entrenadorRepository.deleteById(id);
    }

    public void desactivar(Long id){
        Entrenador entrenador = entrenadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrenador no encontrado"));
        entrenador.setActivo(false);
        entrenadorRepository.save(entrenador);
    }

    public Entrenador update(Long id, Entrenador entrenadorData){
        Entrenador entrenador = entrenadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el entrenador con id " + id));

        entrenador.setNombre(entrenadorData.getNombre());
        entrenador.setEspecialidad(entrenadorData.getEspecialidad());
        entrenador.setActivo(entrenadorData.getActivo());

        return entrenadorRepository.save(entrenador);
    }
}
