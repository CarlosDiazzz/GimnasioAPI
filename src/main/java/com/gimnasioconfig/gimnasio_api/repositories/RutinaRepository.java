package com.gimnasioconfig.gimnasio_api.repositories;

import com.gimnasioconfig.gimnasio_api.models.Rutina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RutinaRepository extends JpaRepository<Rutina,Long> {
}
