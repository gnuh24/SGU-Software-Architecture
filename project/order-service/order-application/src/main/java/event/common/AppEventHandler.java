package event.common;

public interface AppEventHandler<E extends AppEvent> {
    public void handle(E event);
}
