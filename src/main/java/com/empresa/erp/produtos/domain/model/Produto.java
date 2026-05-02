package com.empresa.erp.produtos.domain.model;

import com.empresa.erp.shared.exception.ErpException;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    private UUID id;

    private String nome;
    private String descricao;
    private BigDecimal preco;
    private int estoque;

    @Enumerated(EnumType.STRING)
    private CategoriaProduto categoria;

    protected Produto() {}

    public Produto(String nome, String descricao, BigDecimal preco, int estoque, CategoriaProduto categoria) {
        if (nome == null || nome.isBlank())
            throw new ErpException("Nome não pode ser vazio");
        if (preco == null || preco.compareTo(BigDecimal.ZERO) <= 0)
            throw new ErpException("Preço deve ser maior que zero");
        if (estoque < 0)
            throw new ErpException("Estoque não pode ser negativo");
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.estoque = estoque;
        this.categoria = categoria;
    }
    public void atualizarPreco(BigDecimal novoPreco) {
        if (novoPreco == null || novoPreco.compareTo(BigDecimal.ZERO) <= 0)
            throw new ErpException("Preço deve ser maior que zero");
        this.preco = novoPreco;
    }
    public void adicionarEstoque(int quantidade) {
        if (quantidade <= 0)
            throw new ErpException("Quantidade deve ser maior que zero");
        this.estoque += quantidade;
    }
    public void removerEstoque(int quantidade) {
        if (quantidade <= 0)
            throw new ErpException("Quantidade deve ser maior que zero");
        if (this.estoque < quantidade)
            throw new ErpException("Estoque insuficiente");
        this.estoque -= quantidade;
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public CategoriaProduto getCategoria() {
        return categoria;
    }
}
