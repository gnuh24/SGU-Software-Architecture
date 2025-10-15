package sgu.sa.application.usecase.common;

public interface RequestHandler<I extends Request<O>, O> {
    O handle(I in);
}
