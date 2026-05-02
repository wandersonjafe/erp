package com.empresa.erp.vendas.application;

import com.empresa.erp.clientes.domain.repository.ClienteRepository;
import com.empresa.erp.produtos.domain.model.Produto;
import com.empresa.erp.produtos.domain.repository.ProdutoRepository;
import com.empresa.erp.shared.exception.ErpException;
import com.empresa.erp.vendas.application.dto.AdicionarItemRequest;
import com.empresa.erp.vendas.domain.model.Venda;
import com.empresa.erp.vendas.domain.repository.VendaRepository;
import com.empresa.erp.vendas.domain.valueobject.Dinheiro;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public VendaService(VendaRepository vendaRepository,
                        ClienteRepository clienteRepository,
                        ProdutoRepository produtoRepository) {
        this.vendaRepository = vendaRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    public UUID abrirVenda(UUID clienteId) {
       clienteRepository.buscarPorId(clienteId)
               .orElseThrow(() -> new ErpException("Cliente não encontrado"));
       Venda venda = new Venda(clienteId);
       vendaRepository.salvar(venda);
       return venda.getId();
    }

    public void adicionarItem(UUID vendaId, AdicionarItemRequest request) {
        Venda venda = vendaRepository.buscarPorId(vendaId)
                .orElseThrow(() -> new ErpException("Venda não encontrada"));

        Produto produto = produtoRepository.buscarPorId(request.produtoId())
                .orElseThrow(() -> new ErpException("Produto não encontrado"));

        if (produto.getEstoque() < request.quantidade())
            throw new ErpException("Estoque insuficiente");

        venda.adicionarItem(
                produto.getId(),
                produto.getNome(),
                request.quantidade(),
                new Dinheiro(produto.getPreco())
        );
        produto.removerEstoque(request.quantidade());
        produtoRepository.salvar(produto);
        vendaRepository.salvar(venda);
    }

    public void fecharVenda(UUID vendaId) {
        Venda venda = vendaRepository.buscarPorId(vendaId)
                .orElseThrow(() -> new ErpException("Venda não encontrada"));
        venda.fechar();
        vendaRepository.salvar(venda);

    }
}
