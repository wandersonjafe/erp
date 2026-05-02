package com.empresa.erp.produtos.infrastructure;

import com.empresa.erp.produtos.domain.model.Produto;
import com.empresa.erp.produtos.domain.repository.ProdutoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ProdutoRepositoryJpa implements ProdutoRepository {

    private final ProdutoJpaRepository jpaRepository;

    public ProdutoRepositoryJpa(ProdutoJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void salvar(Produto produto) {
        jpaRepository.save(produto);
    }

    @Override
    public Optional<Produto> buscarPorId(UUID id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<Produto> listarTodos() {
        return jpaRepository.findAll();
    }

    @Override
    public void deletar(UUID id) {
        jpaRepository.deleteById(id);
    }
}


