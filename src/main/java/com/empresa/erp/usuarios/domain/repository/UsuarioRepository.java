package com.empresa.erp.usuarios.domain.repository;

import com.empresa.erp.usuarios.domain.model.Usuario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository {
    void salvar(Usuario usuario);
    Optional<Usuario> buscarPorId(UUID id);
    Optional<Usuario> buscarPorEmail(String email);
    List<Usuario> listarTodos();
    void deletar (UUID id);

}
