package com.empresa.erp.config;

import com.empresa.erp.usuarios.domain.model.Usuario;
import com.empresa.erp.usuarios.domain.model.PerfilUsuario;
import com.empresa.erp.usuarios.domain.repository.UsuarioRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements ApplicationRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (usuarioRepository.listarTodos().isEmpty()) {
            String email = System.getenv().getOrDefault("ADMIN_EMAIL", "admin@erp.com");
            String senha = System.getenv().getOrDefault("ADMIN_SENHA", "admin123");

            Usuario admin = new Usuario("Admin", email, passwordEncoder.encode(senha), PerfilUsuario.ADMIN);
            usuarioRepository.salvar(admin);

            System.out.println("✅ Usuário admin criado: " + email);
        }
    }
}