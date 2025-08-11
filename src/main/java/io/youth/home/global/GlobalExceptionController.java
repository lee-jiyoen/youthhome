package io.youth.home.global;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {
    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorResponse> handleUserException(UserException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(errorCode.name())
                .message(errorCode.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, errorCode.getStatus());
    }
}
