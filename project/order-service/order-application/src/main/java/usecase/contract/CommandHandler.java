package usecase.contract;

public interface CommandHandler<C, R> {
    R handle(C command);
}
