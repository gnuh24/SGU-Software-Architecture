package sgu.sa.container.middleware;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sgu.sa.application.exception.AppException;
import sgu.sa.container.response.ErrorResponse;
import sgu.sa.core.exception.DomainException;

@RestControllerAdvice
public class ExceptionHandlerMiddleware {

    @ExceptionHandler({AppException.class, DomainException.class})
    public ResponseEntity<ErrorResponse> handleBusinessException(RuntimeException ex) {
        System.err.println("Business logic error: " + ex.getMessage());
        var response = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        System.err.println("An unexpected error occurred: " + ex.getMessage());
        var response = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An unexpected error occurred.");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}