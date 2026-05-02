package com.empresa.erp.produtos.application;

import com.empresa.erp.produtos.application.dto.ProdutoRequest;
import com.empresa.erp.produtos.application.dto.ProdutoResponse;
import com.empresa.erp.produtos.domain.model.Produto;
import com.empresa.erp.produtos.domain.repository.ProdutoRepository;
import com.empresa.erp.shared.exception.ErpException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public ProdutoResponse cadastrar(ProdutoRequest request) {
        Produto produto = new Produto(
                request.nome(),
                request.descricao(),
                request.preco(),
                request.estoque(),
                request.categoria()
        );
        produtoRepository.salvar(produto);
        return toResponse(produto);
    }

    public ProdutoResponse buscarPorId(UUID id) {
        Produto produto = produtoRepository.buscarPorId(id)
                .orElseThrow(() -> new ErpException("Produto não encontrado"));
        return toResponse(produto);
    }

    public List<ProdutoResponse> listarTodos() {
        return produtoRepository.listarTodos()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ProdutoResponse atualizar(UUID id, ProdutoRequest request) {
        Produto produto = produtoRepository.buscarPorId(id)
                .orElseThrow(() -> new ErpException("Produto não encontrado"));
        produto.atualizarPreco(request.preco());
        produtoRepository.salvar(produto);
        return toResponse(produto);

    }

    public void deletar(UUID id) {
        produtoRepository.buscarPorId(id)
                .orElseThrow(() -> new ErpException("Produto não encontrado"));
        produtoRepository.deletar(id);
    }

    private ProdutoResponse toResponse(Produto produto) {
        return new ProdutoResponse(
              produto.getId(),
              produto.getNome(),
              produto.getDescricao(),
              produto.getPreco(),
              produto.getEstoque(),
              produto.getCategoria()
        );
    }
}
