package com.empresa.erp.produtos.domain.model;

import com.empresa.erp.shared.exception.ErpException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {

    @Test
    void deveCriarProdutoCorretamente() {
        Produto produto = new Produto(
                "Notebook",
                "Notebook Dell",
                new BigDecimal("3500.00"),
                10,
                CategoriaProduto.ELETRONICO
        );
        assertEquals("Notebook", produto.getNome());
        assertEquals(10, produto.getEstoque());
    }

    @Test
    void deveRemoverEstoqueCorretamente() {
        Produto produto = new Produto(
                "Notebook",
                "Notebook Dell",
                new BigDecimal("3500.00"),
                10,
                CategoriaProduto.ELETRONICO
        );
        produto.removerEstoque(3);
        assertEquals(7, produto.getEstoque());
    }

    @Test
    void deveAdicionarEstoqueCorretamente() {
        Produto produto = new Produto(
                "Notebook",
                "Notebook Dell",
                new BigDecimal("3500.00"),
                10,
                CategoriaProduto.ELETRONICO
        );
        produto.adicionarEstoque(5);
        assertEquals(15, produto.getEstoque());
    }

    @Test
    void deveLancarErroAoRemoverEstoqueInsuficiente() {
        Produto produto = new Produto(
                "Notebook",
                "Notebook Dell",
                new BigDecimal("3500.00"),
                5,
                CategoriaProduto.ELETRONICO
        );
        assertThrows(ErpException.class, () -> produto.removerEstoque(10));
    }

    @Test
    void deveLancarErroAoCriarProdutoComPrecoZero() {
        assertThrows(ErpException.class, () -> new Produto(
                "Notebook",
                "Notebook Dell",
                BigDecimal.ZERO,
                10,
                CategoriaProduto.ELETRONICO
        ));
    }
}