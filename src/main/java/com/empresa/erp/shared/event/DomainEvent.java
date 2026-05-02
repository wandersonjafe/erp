package com.empresa.erp.shared.event;

import java.time.LocalDateTime;

public interface DomainEvent {
    LocalDateTime occurredOn();
}
