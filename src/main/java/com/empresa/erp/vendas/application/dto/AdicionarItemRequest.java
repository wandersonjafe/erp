package com.empresa.erp.vendas.application.dto;

import java.util.UUID;

public record AdicionarItemRequest(
        UUID produtoId,
        int quantidade
    ) {}
