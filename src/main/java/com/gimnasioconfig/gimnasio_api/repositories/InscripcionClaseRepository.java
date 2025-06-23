package com.gimnasioconfig.gimnasio_api.repositories;

import com.gimnasioconfig.gimnasio_api.models.InscripcionClase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscripcionClaseRepository extends JpaRepository<InscripcionClase,Long> {
}
