package com.empresa.erp.usuarios.infrastructure;

import com.empresa.erp.usuarios.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

interface UsuarioJpaRepository extends JpaRepository<Usuario, UUID> {
    Optional<Usuario> findByEmail(String email);
}