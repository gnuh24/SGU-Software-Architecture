package sgu.sa.application.mediator;

@HandlerComponent
public interface RequestHandler<I extends Request<O>, O> {
    O handle(I in);
}
