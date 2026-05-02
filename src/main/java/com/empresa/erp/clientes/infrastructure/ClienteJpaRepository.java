package com.empresa.erp.clientes.infrastructure;

import com.empresa.erp.clientes.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

interface ClienteJpaRepository extends JpaRepository<Cliente, UUID> {
    Optional<Cliente> findByEmail(String email);
}