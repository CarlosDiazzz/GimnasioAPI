package com.gimnasioconfig.gimnasio_api.repositories;

import com.gimnasioconfig.gimnasio_api.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
    List<Usuario> findByActivoTrue();
    Optional<Usuario> findByIdAndActivoTrue(Long id);
}
