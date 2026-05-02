package com.empresa.erp.usuarios.domain.event;

import com.empresa.erp.shared.event.DomainEvent;

import java.time.LocalDateTime;
import java.util.UUID;

public class UsuarioCadastradoEvent implements DomainEvent {

    private final UUID usuarioId;
    private final String email;
    private final LocalDateTime ocorridoEm;

    public UsuarioCadastradoEvent(UUID usuarioId, String email) {
        this.usuarioId = usuarioId;
        this.email = email;
        this.ocorridoEm = LocalDateTime.now();
    }

    @Override
    public LocalDateTime occurredOn() {
        return ocorridoEm;
    }

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public String getEmail() {
        return email;
    }
}
