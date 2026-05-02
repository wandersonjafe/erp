package com.empresa.erp.clientes.infrastructure;

import com.empresa.erp.clientes.domain.model.Cliente;
import com.empresa.erp.clientes.domain.repository.ClienteRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ClienteRepositoryJpa implements ClienteRepository {

    private final ClienteJpaRepository jpaRepository;

    public ClienteRepositoryJpa(ClienteJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void salvar(Cliente cliente) {
        jpaRepository.save(cliente);
    }

    @Override
    public Optional<Cliente> buscarPorId(UUID id) {
        return jpaRepository.findById(id);
    }

    @Override
    public Optional<Cliente> buscarPorEmail(String email) {
        return jpaRepository.findByEmail(email);
    }

    @Override
    public List<Cliente> listarTodos() {
        return jpaRepository.findAll();
    }

    @Override
    public void deletar(UUID id) {
        jpaRepository.deleteById(id);
    }
}
