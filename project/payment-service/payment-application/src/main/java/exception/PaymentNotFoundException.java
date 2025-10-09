package exception;

public class PaymentNotFoundException extends AppException {
    public PaymentNotFoundException(String message) {
        super(message);
    }
}
