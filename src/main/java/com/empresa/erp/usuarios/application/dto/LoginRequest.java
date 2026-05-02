package com.empresa.erp.usuarios.application.dto;

public record LoginRequest(
        String email,
        String senha
) {}
