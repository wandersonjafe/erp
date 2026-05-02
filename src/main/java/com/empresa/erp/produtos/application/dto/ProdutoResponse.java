package com.empresa.erp.produtos.application.dto;


import com.empresa.erp.produtos.domain.model.CategoriaProduto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProdutoResponse (
       UUID id,
       String nome,
       String descricao,
       BigDecimal preco,
       int estoque,
       CategoriaProduto categoria
) {}
