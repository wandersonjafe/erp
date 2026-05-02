package com.empresa.erp.usuarios.infrastructure;

import com.empresa.erp.usuarios.domain.model.Usuario;
import com.empresa.erp.usuarios.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UsuarioRepositoryJpa implements UsuarioRepository {

    private final UsuarioJpaRepository jpaRepository;

    public UsuarioRepositoryJpa(UsuarioJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void salvar(Usuario usuario) {
        jpaRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> buscarPorId(UUID id) {
        return jpaRepository.findById(id);
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return jpaRepository.findByEmail(email);
    }

    @Override
    public List<Usuario> listarTodos() {
        return jpaRepository.findAll();
    }

    @Override
    public void deletar(UUID id) {
        jpaRepository.deleteById(id);
    }
}