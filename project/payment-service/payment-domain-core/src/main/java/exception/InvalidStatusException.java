package exception;

public class InvalidStatusException extends DomainException {
    public InvalidStatusException(String message) {
        super(message);
    }
}
