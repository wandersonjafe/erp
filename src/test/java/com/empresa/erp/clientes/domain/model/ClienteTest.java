package com.empresa.erp.clientes.domain.model;

import com.empresa.erp.clientes.domain.valueobject.CPF;
import com.empresa.erp.clientes.domain.valueobject.Endereco;
import com.empresa.erp.shared.exception.ErpException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    private Endereco enderecoValido() {
        return new Endereco("Rua das Flores", "123", "20000000", "Rio de Janeiro", "RJ");
    }

    @Test
    void deveCriarClienteCorretamente() {
        Cliente cliente = new Cliente(
                "João Silva",
                "joao@email.com",
                new CPF("12345678900"),
                enderecoValido()
        );
        assertEquals("João Silva", cliente.getNome());
        assertEquals("joao@email.com", cliente.getEmail());
    }

    @Test
    void deveLancarErroAoCriarClienteComNomeVazio() {
        assertThrows(ErpException.class, () -> new Cliente(
                "",
                "joao@email.com",
                new CPF("12345678900"),
                enderecoValido()
        ));
    }

    @Test
    void deveLancarErroAoCriarClienteComEmailInvalido() {
        assertThrows(ErpException.class, () -> new Cliente(
                "João Silva",
                "emailinvalido",
                new CPF("12345678900"),
                enderecoValido()
        ));
    }

    @Test
    void deveAtualizarNomeCorretamente() {
        Cliente cliente = new Cliente(
                "João Silva",
                "joao@email.com",
                new CPF("12345678900"),
                enderecoValido()
        );
        cliente.atualizarNome("João Santos");
        assertEquals("João Santos", cliente.getNome());
    }

    @Test
    void deveLancarErroAoCriarCPFInvalido() {
        assertThrows(IllegalArgumentException.class, () -> new CPF("123"));
    }
}