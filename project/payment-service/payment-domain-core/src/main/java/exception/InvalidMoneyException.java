package exception;

public class InvalidMoneyException extends DomainException {
    public InvalidMoneyException(String message) {
        super(message);
    }
}
