package com.gimnasioconfig.gimnasio_api.services;

import com.gimnasioconfig.gimnasio_api.models.InscripcionClase;
import com.gimnasioconfig.gimnasio_api.repositories.InscripcionClaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InscripcionClaseService {

    @Autowired
    private InscripcionClaseRepository inscripcionClaseRepository;

    public List<InscripcionClase> all(){
        return inscripcionClaseRepository.findAll();
    }

    public Optional<InscripcionClase> findById(Long id){
        return inscripcionClaseRepository.findById(id);
    }

    public InscripcionClase save(InscripcionClase clase){
        return (InscripcionClase) inscripcionClaseRepository.save(clase);
    }

    public void delete(Long id){
        inscripcionClaseRepository.deleteById(id);
    }

    public InscripcionClase updateInscripcion(Long id, InscripcionClase inscripcionData) {
        InscripcionClase inscripcion = inscripcionClaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscripción no encontrada con id " + id));

        inscripcion.setFecha(inscripcionData.getFecha());
        inscripcion.setClase(inscripcionData.getClase());
        inscripcion.setCliente(inscripcionData.getCliente());

        return inscripcionClaseRepository.save(inscripcion);
    }
}
