package com.empresa.erp.usuarios.application.dto;

import com.empresa.erp.usuarios.domain.model.PerfilUsuario;

public record UsuarioRequest(
        String nome,
        String email,
        String senha,
        PerfilUsuario perfil
) {}
