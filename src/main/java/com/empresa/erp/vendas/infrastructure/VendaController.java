package com.empresa.erp.vendas.infrastructure;

import com.empresa.erp.vendas.application.VendaService;
import com.empresa.erp.vendas.application.dto.AdicionarItemRequest;
import com.empresa.erp.vendas.domain.model.Venda;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    private final VendaService vendaService;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @PostMapping("/abrir/{clienteId}")
    public ResponseEntity<UUID> abrirVenda(@PathVariable UUID clienteId) {
        UUID vendaId = vendaService.abrirVenda(clienteId);
        return ResponseEntity.ok(vendaId);
    }

    @PostMapping("/{vendaId}/itens")
    public ResponseEntity<Void> adicionarItem(
            @PathVariable UUID vendaId,
            @RequestBody AdicionarItemRequest request) {
        vendaService.adicionarItem(vendaId, request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{vendaId}/fechar")
    public ResponseEntity<Void> fecharVenda(@PathVariable UUID vendaId) {
        vendaService.fecharVenda(vendaId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{vendaId}/cancelar")
    public ResponseEntity<Void> cancelarVenda(@PathVariable UUID vendaId) {
        vendaService.cancelarVenda(vendaId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{vendaId}")
    public ResponseEntity<Venda> buscarVenda(@PathVariable UUID vendaId) {
        return vendaService.buscarPorId(vendaId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Venda>> listarVendas() {
        return ResponseEntity.ok(vendaService.listarTodos());
    }
}