package com.empresa.erp.clientes.application.dto;

import java.util.UUID;

public record ClienteResponse(
        UUID id,
        String nome,
        String email,
        String cpf,
        String logradouro,
        String numero,
        String cep,
        String cidade,
        String estado
) {}
