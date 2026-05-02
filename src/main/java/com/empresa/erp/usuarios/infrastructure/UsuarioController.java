package com.empresa.erp.usuarios.infrastructure;

import com.empresa.erp.usuarios.application.UsuarioService;
import com.empresa.erp.usuarios.application.dto.LoginRequest;
import com.empresa.erp.usuarios.application.dto.LoginResponse;
import com.empresa.erp.usuarios.application.dto.UsuarioRequest;
import com.empresa.erp.usuarios.application.dto.UsuarioResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioResponse> cadastrar(@RequestBody UsuarioRequest request) {
        return ResponseEntity.ok(usuarioService.cadastrar(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(usuarioService.login(request));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listarTodos() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
