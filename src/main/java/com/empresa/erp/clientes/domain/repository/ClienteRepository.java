package com.empresa.erp.clientes.domain.repository;

import com.empresa.erp.clientes.domain.model.Cliente;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository {
    void salvar (Cliente cliente);
    Optional<Cliente> buscarPorId(UUID id);
    Optional<Cliente> buscarPorEmail(String email);
    List<Cliente> listarTodos();
    void deletar(UUID id);
}
