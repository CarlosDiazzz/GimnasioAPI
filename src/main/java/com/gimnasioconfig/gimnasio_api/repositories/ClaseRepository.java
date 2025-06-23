package com.gimnasioconfig.gimnasio_api.repositories;

import com.gimnasioconfig.gimnasio_api.models.Clase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClaseRepository extends JpaRepository<Clase,Long> {
    List<Clase> findByActivoTrue();
    Optional<Clase> findByIdAndActivoTrue(Long id);
}
