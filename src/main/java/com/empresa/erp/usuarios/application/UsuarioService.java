package com.empresa.erp.usuarios.application;

import com.empresa.erp.config.JwtService;
import com.empresa.erp.shared.exception.ErpException;
import com.empresa.erp.usuarios.application.dto.LoginRequest;
import com.empresa.erp.usuarios.application.dto.LoginResponse;
import com.empresa.erp.usuarios.application.dto.UsuarioRequest;
import com.empresa.erp.usuarios.application.dto.UsuarioResponse;
import com.empresa.erp.usuarios.domain.model.Usuario;
import com.empresa.erp.usuarios.domain.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          PasswordEncoder passwordEncoder,
                          JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public UsuarioResponse cadastrar(UsuarioRequest request) {
        usuarioRepository.buscarPorEmail(request.email())
                .ifPresent(u -> {throw new ErpException("Email já cadastrado"); });

        String senhaCriptografada = passwordEncoder.encode(request.senha());
        Usuario usuario = new Usuario(
                request.nome(),
                request.email(),
                senhaCriptografada,
                request.perfil()
        );
        usuarioRepository.salvar(usuario);
        return toResponse(usuario);
    }

    public LoginResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepository.buscarPorEmail(request.email())
                .orElseThrow(() -> new ErpException("Credenciais inválidas"));

        if (!passwordEncoder.matches(request.senha(), usuario.getSenha()))
            throw new ErpException("Credenciais inválidas");

        String token = jwtService.gerarToken(usuario.getEmail());
        return new LoginResponse(token, usuario.getNome(), usuario.getEmail());
    }
    public UsuarioResponse buscarPorId(UUID id) {
        Usuario usuario = usuarioRepository.buscarPorId(id)
                .orElseThrow(() -> new ErpException("Usuário não encontrado"));
        return toResponse(usuario);
    }

    public List<UsuarioResponse> listarTodos() {
        return usuarioRepository.listarTodos()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public void deletar(UUID id) {
        usuarioRepository.buscarPorId(id)
                .orElseThrow(() -> new ErpException("Usuário não encontrado"));
        usuarioRepository.deletar(id);
    }

    private UsuarioResponse toResponse(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getPerfil()
        );
    }
}
