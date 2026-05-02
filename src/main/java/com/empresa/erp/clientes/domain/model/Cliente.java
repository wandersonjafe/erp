package com.empresa.erp.clientes.domain.model;

import com.empresa.erp.clientes.domain.valueobject.CPF;
import com.empresa.erp.clientes.domain.valueobject.Endereco;
import com.empresa.erp.shared.exception.ErpException;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    private UUID id;

    private String nome;
    private String email;
    private String cpf;

    @Embedded
    private EnderecoEmbeddable endereco;

    protected Cliente() {}

    public Cliente(String nome, String email, CPF cpf, Endereco endereco) {
        if (nome == null || nome.isBlank())
            throw new ErpException("Nome não pode ser vazio");
        if (email == null || !email.contains("@"))
            throw new ErpException("Email inválido");
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.email = email;
        this.cpf = cpf.getValor();
        this.endereco = new EnderecoEmbeddable(endereco);
    }

    public void atualizarNome(String novoNome) {
        if (novoNome == null || novoNome.isBlank())
            throw new ErpException("Nome não pode ser vazio");
        this.nome = novoNome;
    }

    public void atualizarEmail(String novoEmail) {
        if (novoEmail == null || !novoEmail.contains("@"))
            throw new ErpException("Email inválido");
        this.email = novoEmail;
    }
    public void atualizarEndereco(Endereco novoEndereco) {
        this.endereco = new EnderecoEmbeddable(novoEndereco);

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

    public String getCpf() {
        return cpf;
    }

    public EnderecoEmbeddable getEndereco() {
        return endereco;
    }
}
