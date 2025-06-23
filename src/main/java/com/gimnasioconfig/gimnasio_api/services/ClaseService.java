package com.gimnasioconfig.gimnasio_api.services;

import com.gimnasioconfig.gimnasio_api.models.Clase;
import com.gimnasioconfig.gimnasio_api.repositories.ClaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClaseService {
    @Autowired
    private ClaseRepository claseRepository;

    public List<Clase> all(){
        return claseRepository.findAll();
    }

    public List<Clase> allActivos(){
        return claseRepository.findByActivoTrue();
    }

    public Optional<Clase> findById(Long id){
        return claseRepository.findById(id);
    }

    public Clase findByIdAndActivo(Long id){
        return claseRepository.findByIdAndActivoTrue(id)
                .orElseThrow(() -> new RuntimeException("Clase no encontrado o inactivo"));
    }

    public Clase save(Clase clase){
        return (Clase) claseRepository.save(clase);
    }

    public void delete(Long id){
        claseRepository.deleteById(id);
    }

    public void desactivar(Long id){
        Clase clase = claseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        clase.setActivo(false);
        claseRepository.save(clase);

    }

    public Clase update(Long id, Clase claseData){
        Clase clase = claseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe la clase con id " + id));

        clase.setNombre(claseData.getNombre());
        clase.setDia(claseData.getDia());
        clase.setHoraInicio(claseData.getHoraInicio());
        clase.setHoraFin(claseData.getHoraFin());
        clase.setCupoMaximo(claseData.getCupoMaximo());
        clase.setActivo(claseData.getActivo());

        return claseRepository.save(clase);
    }
}
