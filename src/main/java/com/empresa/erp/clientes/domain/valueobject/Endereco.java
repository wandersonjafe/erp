package com.empresa.erp.clientes.domain.valueobject;

public class Endereco {
    private final String logradouro;
    private final String numero;
    private final String cep;
    private final String cidade;
    private final String estado;

    public Endereco(String logradouro, String numero, String cep, String cidade, String estado) {
        if (logradouro == null || logradouro.isBlank())
            throw new IllegalArgumentException("Logradouro não pode ser vazio");
        if (cep == null || cep.replaceAll("\\D","").length() != 8)
            throw new IllegalArgumentException("CEP inválido");
        this.logradouro = logradouro;
        this.numero = numero;
        this.cep = cep.replaceAll("\\D", "");
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getLogradouro() { return logradouro; }
    public String getNumero() { return numero; }
    public String getCep() { return cep; }
    public String getCidade() { return cidade; }
    public String getEstado() { return estado; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Endereco)) return false;
        Endereco e = (Endereco) o;
        return logradouro.equals(e.logradouro) &&
                numero.equals(e.numero) &&
                cep.equals(e.cep);

    }

    @Override
    public int hashCode() {
        return logradouro.hashCode() + numero.hashCode() + cep.hashCode();
    }
}
