package com.empresa.erp.produtos.infrastructure;

import com.empresa.erp.produtos.application.ProdutoService;
import com.empresa.erp.produtos.application.dto.ProdutoRequest;
import com.empresa.erp.produtos.application.dto.ProdutoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;

    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> cadastrar(@RequestBody ProdutoRequest request) {
        return ResponseEntity.ok(produtoService.cadastrar(request));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> listarTodos() {
        return ResponseEntity.ok(produtoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> atualizar(
            @PathVariable UUID id,
            @RequestBody ProdutoRequest request) {
        return ResponseEntity.ok(produtoService.atualizar(id, request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
