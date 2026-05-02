package com.empresa.erp.clientes.application.dto;

public record ClienteRequest(
        String nome,
        String email,
        String cpf,
        String logradouro,
        String numero,
        String cep,
        String cidade,
        String estado
) {}
