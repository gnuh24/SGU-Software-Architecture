package exception;

public class DomainException extends RuntimeException {
    public DomainException(String error) {
        super(error);
    }
}
