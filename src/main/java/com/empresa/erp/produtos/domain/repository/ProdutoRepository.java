package com.empresa.erp.produtos.domain.repository;

import com.empresa.erp.produtos.domain.model.Produto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProdutoRepository {
    void salvar(Produto produto);
    Optional<Produto> buscarPorId(UUID id);
    List<Produto> listarTodos();
    void deletar(UUID id);
}
