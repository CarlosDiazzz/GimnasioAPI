package com.gimnasioconfig.gimnasio_api.services;

import com.gimnasioconfig.gimnasio_api.models.Rutina;
import com.gimnasioconfig.gimnasio_api.repositories.RutinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RutinaService {

    @Autowired
    private RutinaRepository rutinaRepository;

    public List<Rutina> all(){
        return rutinaRepository.findAll();
    }

    public Optional<Rutina> findById(Long id){
        return rutinaRepository.findById(id);
    }

    public Rutina save(Rutina clase){
        return (Rutina) rutinaRepository.save(clase);
    }

    public void delete(Long id){
        rutinaRepository.deleteById(id);
    }
    public Rutina updateRutina(Long id, Rutina rutinaData) {
        Rutina rutina = rutinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rutina no encontrada con id " + id));

        rutina.setDescripcion(rutinaData.getDescripcion());
        rutina.setFecha(rutinaData.getFecha());
        rutina.setCliente(rutinaData.getCliente());

        return rutinaRepository.save(rutina);
    }
}
