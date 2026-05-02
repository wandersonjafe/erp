package com.empresa.erp.config;

import com.empresa.erp.usuarios.application.UsuarioService;
import com.empresa.erp.usuarios.application.dto.LoginRequest;
import com.empresa.erp.usuarios.application.dto.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(usuarioService.login(request));
    }
}