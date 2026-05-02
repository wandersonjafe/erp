package com.empresa.erp.clientes.domain.model;

import com.empresa.erp.clientes.domain.valueobject.Endereco;
import jakarta.persistence.Embeddable;

@Embeddable
public class EnderecoEmbeddable {

    private String logradouro;
    private String numero;
    private String cep;
    private String cidade;
    private String estado;

    protected EnderecoEmbeddable() {}

    public EnderecoEmbeddable(Endereco endereco) {
        this.logradouro = endereco.getLogradouro();
        this.numero = endereco.getNumero();
        this.cep = endereco.getCep();
        this.cidade = endereco.getCidade();
        this.estado = endereco.getEstado();
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public String getCep() {
        return cep;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }
}
