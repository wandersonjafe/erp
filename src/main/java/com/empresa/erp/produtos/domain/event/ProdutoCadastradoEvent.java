package com.empresa.erp.produtos.domain.event;

import com.empresa.erp.shared.event.DomainEvent;

import java.time.LocalDateTime;
import java.util.UUID;

public class ProdutoCadastradoEvent implements DomainEvent {

    private final UUID produtoId;
    private final String nome;
    private final LocalDateTime ocorridoEm;

    public ProdutoCadastradoEvent(UUID produtoId, String nome) {
        this.produtoId = produtoId;
        this.nome = nome;
        this.ocorridoEm = LocalDateTime.now();
    }

    @Override
    public LocalDateTime occurredOn() {
        return ocorridoEm;
    }

    public UUID getProdutoId() {
        return produtoId;
    }

    public String getNome() {
        return nome;
    }
}
