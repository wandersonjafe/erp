package com.empresa.erp.clientes.infrastructure;

import com.empresa.erp.clientes.application.ClienteService;
import com.empresa.erp.clientes.application.dto.ClienteRequest;
import com.empresa.erp.clientes.application.dto.ClienteResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> cadastrar(@RequestBody ClienteRequest request) {
        return ResponseEntity.ok(clienteService.cadastrar(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> listarTodos() {
        return ResponseEntity.ok(clienteService.listarTodos());
    }

    @PutMapping("{id}")
    public ResponseEntity<ClienteResponse> atualizar(
            @PathVariable UUID id,
            @RequestBody ClienteRequest request) {
        return ResponseEntity.ok(clienteService.atualizar(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        clienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
