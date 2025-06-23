package com.gimnasioconfig.gimnasio_api.repositories;

import com.gimnasioconfig.gimnasio_api.models.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EntrenadorRepository extends JpaRepository<Entrenador,Long> {
    List<Entrenador> findByActivoTrue();
    Optional<Entrenador> findByIdAndActivoTrue(Long id);
}
