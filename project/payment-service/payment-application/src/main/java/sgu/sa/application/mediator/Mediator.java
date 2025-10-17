package sgu.sa.application.mediator;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class Mediator {
   private final ApplicationContext applicationContext;
   private final Map<Class<? extends Request<?>>, RequestHandler<?, ?>> handlerMap = new ConcurrentHashMap<>();

   @PostConstruct
   @SuppressWarnings("unchecked")
   public void registerHandlers() {
      Map<String, Object> handlers = applicationContext.getBeansWithAnnotation(HandlerComponent.class);

      handlers.values().forEach(handler -> {
         if (handler instanceof RequestHandler) {
            Class<?>[] generics = GenericTypeResolver.resolveTypeArguments(handler.getClass(), RequestHandler.class);
            if (generics != null && generics.length == 2) {
               Class<? extends Request<?>> commandType = (Class<? extends Request<?>>) generics[0];
               handlerMap.put(commandType, (RequestHandler<?, ?>) handler);
            }
         }
      });
   }

   @SuppressWarnings("unchecked")
   public <R> R send(Request<R> request) {
      RequestHandler<Request<R>, R> handler = (RequestHandler<Request<R>, R>) handlerMap.get(request.getClass());

      if (handler == null) {
         throw new IllegalStateException("No handler registered for request: " + request.getClass().getSimpleName());
      }

      return handler.handle(request);
   }
}
