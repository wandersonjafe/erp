package com.empresa.erp.vendas.infrastructure;

import com.empresa.erp.vendas.application.VendaService;
import com.empresa.erp.vendas.application.dto.AdicionarItemRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Void> fecharVenda (@PathVariable UUID vendaId) {
        vendaService.fecharVenda(vendaId);
        return ResponseEntity.ok().build();
    }
}
