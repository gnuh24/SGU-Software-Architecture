package sgu.sa.core.common;

import java.time.Instant;
import java.util.UUID;

public interface DomainEvent {
    Instant occurredOn();
    default UUID id() {
        return UUID.randomUUID();
    }
}
