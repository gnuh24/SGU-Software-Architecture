package common;

import java.util.ArrayList;
import java.util.List;

public interface HasDomainEvent {
    List<DomainEvent> domainEvents = new ArrayList<>();

    default List<DomainEvent> getDomainEvents() {
        return List.copyOf(domainEvents);
    }

    default void addDomainEvent(DomainEvent event) {
        domainEvents.add(event);
    };

    default void clearDomainEvents() {
        domainEvents.clear();
    };
}
