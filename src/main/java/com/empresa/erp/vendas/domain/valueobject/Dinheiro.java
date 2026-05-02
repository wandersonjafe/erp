package com.empresa.erp.vendas.domain.valueobject;

import java.math.BigDecimal;

public final class Dinheiro {

    private final BigDecimal valor;

    public Dinheiro(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Valor não pode ser nulo ou negativo");
        this.valor = valor;
    }

    public BigDecimal getValor() {
        return valor;

    }

    public Dinheiro somar(Dinheiro outro) {
        return new Dinheiro(this.valor.add(outro.valor));
    }
    @Override
    public boolean equals(Object o) {
        if (this == o ) return true;
        if (! (o instanceof Dinheiro)) return false;
        Dinheiro d = (Dinheiro) o;
        return valor.compareTo(d.valor) == 0;
    }
    @Override
    public int hashCode(){
        return valor.hashCode();
    }
}
