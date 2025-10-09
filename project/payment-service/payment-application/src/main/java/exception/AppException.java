package exception;

public class AppException extends RuntimeException {
    public AppException(String error) {
        super(error);
    }
}
