package com.empresa.erp.usuarios.domain.model;

import com.empresa.erp.shared.exception.ErpException;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    private UUID id;

    private String nome;

    @Column(unique = true)
    private String email;

    private String senha;

    @Enumerated(EnumType.STRING)
    private PerfilUsuario perfil;

    protected Usuario() {}

    public Usuario(String nome, String email, String senhaCriptografada, PerfilUsuario perfil) {
        if (nome == null || nome.isBlank())
            throw new ErpException("Nome não pode ser vazio");
        if (email == null || !email.contains("@"))
            throw new ErpException("Email inválido");
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.email = email;
        this.senha = senhaCriptografada;
        this.perfil = perfil;
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public PerfilUsuario getPerfil() {
        return perfil;
    }
}
