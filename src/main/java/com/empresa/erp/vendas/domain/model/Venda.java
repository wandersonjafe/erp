package com.empresa.erp.vendas.domain.model;

import com.empresa.erp.shared.exception.ErpException;
import com.empresa.erp.vendas.domain.valueobject.Dinheiro;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "vendas")
public class Venda {

    @Id
    private UUID id;

    private UUID clienteId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "venda_id")
    private List<ItemVenda> itens = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private StatusVenda status;

    private BigDecimal total;

    private LocalDateTime dataCriacao;
    private LocalDateTime dataFechamento;
    private LocalDateTime dataCancelamento;

    protected Venda() {}

    public Venda(UUID clienteId) {
        this.id = UUID.randomUUID();
        this.clienteId = clienteId;
        this.status = StatusVenda.ABERTA;
        this.total = BigDecimal.ZERO;
        this.dataCriacao = LocalDateTime.now();
    }

    public void adicionarItem(UUID produtoId, String nomeProduto, int quantidade, Dinheiro preco) {
        if (status != StatusVenda.ABERTA)
            throw new ErpException("Não é possível adicionar itens a uma venda fechada");
        itens.add(new ItemVenda(produtoId, nomeProduto, quantidade, preco));
        recalcularTotal();
    }

    public void fechar() {
        if (itens.isEmpty())
            throw new ErpException("Não é possível fechar uma venda sem itens");
        this.status = StatusVenda.FECHADA;
        this.dataFechamento = LocalDateTime.now();
    }

    public void cancelar() {
        if(status != StatusVenda.ABERTA)
            throw new ErpException("Apenas vendas abertas podem ser canceladas");
        this.status = StatusVenda.CANCELADA;
        this.dataCancelamento = LocalDateTime.now();
    }

    private void recalcularTotal() {
        this.total = itens.stream()
                .map(item -> item.getSubtotal().getValor())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public UUID getId() { return id; }
    public UUID getClienteId() { return clienteId; }
    public StatusVenda getStatus() { return status; }
    public BigDecimal getTotal() { return total; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public LocalDateTime getDataFechamento() { return dataFechamento; }
    public LocalDateTime getDataCancelamento() { return dataCancelamento; }
    public List<ItemVenda> getItens() { return Collections.unmodifiableList(itens); }
}