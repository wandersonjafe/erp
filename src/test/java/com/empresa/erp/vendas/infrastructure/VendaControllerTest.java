package com.empresa.erp.vendas.infrastructure;

import com.empresa.erp.clientes.domain.model.Cliente;
import com.empresa.erp.clientes.domain.repository.ClienteRepository;
import com.empresa.erp.clientes.domain.valueobject.CPF;
import com.empresa.erp.clientes.domain.valueobject.Endereco;
import com.empresa.erp.config.JwtService;
import com.empresa.erp.produtos.domain.model.CategoriaProduto;
import com.empresa.erp.produtos.domain.model.Produto;
import com.empresa.erp.produtos.domain.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc
class VendaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private JwtService jwtService;

    private String token;
    private UUID clienteId;
    private UUID produtoId;

    @BeforeEach
    void setUp() {
        token = jwtService.gerarToken("teste@erp.com");

        Cliente cliente = new Cliente(
                "Cliente Teste",
                "teste@erp.com",
                new CPF("12345678900"),
                new Endereco("Rua Teste", "1", "20000000", "Rio de Janeiro", "RJ")
        );
        clienteRepository.salvar(cliente);
        clienteId = cliente.getId();

        Produto produto = new Produto(
                "Produto Teste",
                "Descrição",
                new BigDecimal("100.00"),
                10,
                CategoriaProduto.ELETRONICO
        );
        produtoRepository.salvar(produto);
        produtoId = produto.getId();
    }

    @Test
    void deveAbrirVendaComSucesso() throws Exception {
        mockMvc.perform(post("/vendas/abrir/" + clienteId)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    void deveRetornar403SemToken() throws Exception {
        mockMvc.perform(post("/vendas/abrir/" + clienteId))
                .andExpect(status().isForbidden());
    }

    @Test
    void deveAdicionarItemNaVenda() throws Exception {
        String vendaId = mockMvc.perform(post("/vendas/abrir/" + clienteId)
                        .header("Authorization", "Bearer " + token))
                .andReturn()
                .getResponse()
                .getContentAsString()
                .replace("\"", "");

        String body = """
                {
                    "produtoId": "%s",
                    "quantidade": 2
                }
                """.formatted(produtoId);

        mockMvc.perform(post("/vendas/" + vendaId + "/itens")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk());
    }
}