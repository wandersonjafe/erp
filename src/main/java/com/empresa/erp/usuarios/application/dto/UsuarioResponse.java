package com.empresa.erp.usuarios.application.dto;

import com.empresa.erp.usuarios.domain.model.PerfilUsuario;

import java.util.UUID;

public record UsuarioResponse(
        UUID id,
        String nome,
        String email,
        PerfilUsuario perfil
) {}
