package com.empresa.erp.produtos.application.dto;

import com.empresa.erp.produtos.domain.model.CategoriaProduto;

import java.math.BigDecimal;

public record ProdutoRequest (
        String nome,
        String descricao,
        BigDecimal preco,
        int estoque,
        CategoriaProduto categoria
) {}