package com.empresa.erp.vendas.domain.model;

import com.empresa.erp.shared.exception.ErpException;
import com.empresa.erp.vendas.domain.valueobject.Dinheiro;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class VendaTest {

    @Test
    void deveAbrirVendaComStatusAberta() {
        Venda venda = new Venda(UUID.randomUUID());
        assertEquals(StatusVenda.ABERTA, venda.getStatus());
    }

    @Test
    void deveAdicionarItemNaVenda() {
        Venda venda = new Venda(UUID.randomUUID());
        venda.adicionarItem(
                UUID.randomUUID(),
                "Notebook",
                2,
                new Dinheiro(new BigDecimal("3500.00"))
        );
        assertEquals(1, venda.getItens().size());
    }

    @Test
    void deveCalcularTotalCorretamente() {
        Venda venda = new Venda(UUID.randomUUID());
        venda.adicionarItem(
                UUID.randomUUID(),
                "Notebook",
                2,
                new Dinheiro(new BigDecimal("3500.00"))
        );
        assertEquals(new BigDecimal("7000.00"), venda.getTotal());
    }

    @Test
    void deveLancarErroAoAdicionarItemEmVendaFechada() {
        Venda venda = new Venda(UUID.randomUUID());
        venda.adicionarItem(
                UUID.randomUUID(),
                "Notebook",
                1,
                new Dinheiro(new BigDecimal("3500.00"))
        );
        venda.fechar();

        assertThrows(ErpException.class, () ->
                venda.adicionarItem(
                        UUID.randomUUID(),
                        "Mouse",
                        1,
                        new Dinheiro(new BigDecimal("150.00"))
                )
        );
    }

    @Test
    void deveLancarErroAoFecharVendaSemItens() {
        Venda venda = new Venda(UUID.randomUUID());
        assertThrows(ErpException.class, venda::fechar);
    }
}