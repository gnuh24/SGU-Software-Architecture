package usecase.contract;

public interface QueryHandler<Q, R> {
    R handle(Q query);
}
