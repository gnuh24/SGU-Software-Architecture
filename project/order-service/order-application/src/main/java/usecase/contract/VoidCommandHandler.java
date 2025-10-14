package usecase.contract;

public interface VoidCommandHandler<C> {
    void handle(C command);
}
