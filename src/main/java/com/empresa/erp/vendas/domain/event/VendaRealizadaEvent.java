package com.empresa.erp.vendas.domain.event;

import com.empresa.erp.shared.event.DomainEvent;

import java.time.LocalDateTime;
import java.util.UUID;

public class VendaRealizadaEvent implements DomainEvent {

    private final UUID vendaId;
    private final UUID clienteId;
    private final LocalDateTime ocorridoEm;

    public VendaRealizadaEvent(UUID vendaId, UUID clienteId) {
        this.vendaId = vendaId;
        this.clienteId = clienteId;
        this.ocorridoEm = LocalDateTime.now();
    }

    @Override
    public LocalDateTime occurredOn() {
        return ocorridoEm;
    }

    public UUID getVendaId() {
        return vendaId;
    }

    public UUID getClienteId() {
        return clienteId;
    }

}
