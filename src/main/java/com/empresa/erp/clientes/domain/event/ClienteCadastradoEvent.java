package com.empresa.erp.clientes.domain.event;

import com.empresa.erp.shared.event.DomainEvent;

import java.time.LocalDateTime;
import java.util.UUID;

public class ClienteCadastradoEvent implements DomainEvent {

    private final UUID clienteId;
    private final String email;
    private final LocalDateTime ocorridoEm;

    public ClienteCadastradoEvent(UUID clienteId, String email) {
        this.clienteId = clienteId;
        this.email = email;
        this.ocorridoEm = LocalDateTime.now();
    }

    @Override
    public LocalDateTime occurredOn() {
        return ocorridoEm;
    }

    public UUID getClienteId() { return clienteId; }
    public String getEmail() { return email; }
}
