package com.empresa.erp.clientes.domain.valueobject;

public final class CPF {

    private final String valor;

    public CPF(String valor) {
        if (valor == null || !isValido(valor))
            throw new IllegalArgumentException("CPF inválido: " + valor);
        this.valor = valor.replaceAll("\\D","");
    }
    private boolean isValido (String cpf) {
        String numeros = cpf.replaceAll("\\D","");
        return numeros.length() == 11;
    }

    public String getValor() {
        return valor;
    }
    public String getFormatado() {
        return valor.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CPF)) return false;
        CPF cpf = (CPF) o;
        return valor.equals(cpf.valor);
    }

    @Override
    public int hashCode() {
        return valor.hashCode();
    }

    @Override
    public String toString() {
        return getFormatado();
    }
}
