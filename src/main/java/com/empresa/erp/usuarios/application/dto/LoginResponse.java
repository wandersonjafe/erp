package com.empresa.erp.usuarios.application.dto;

public record LoginResponse(
        String token,
        String nome,
        String email
) {}
