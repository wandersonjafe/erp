package com.empresa.erp.vendas.domain.model;

import com.empresa.erp.vendas.domain.valueobject.Dinheiro;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "itens_venda")
class ItemVenda {

    @Id
    private UUID id;

    private UUID produtoId;
    private String nomeProduto;
    private int quantidade;
    private BigDecimal precoUnitario;

    protected ItemVenda() {}

    ItemVenda(UUID produtoId, String nomeProduto, int quantidade, Dinheiro precoUnitario) {
        if (quantidade <= 0)
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        this.id = UUID.randomUUID();
        this.produtoId = produtoId;
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario.getValor();
    }

    Dinheiro getSubtotal() {
        return new Dinheiro(precoUnitario.multiply(BigDecimal.valueOf(quantidade)));
    }

    public UUID getId() { return id; }
    public UUID getProdutoId() { return produtoId; }
    public String getNomeProduto() { return nomeProduto; }
    public int getQuantidade() { return quantidade; }
    public Dinheiro getPrecoUnitario() { return new Dinheiro(precoUnitario); }
}