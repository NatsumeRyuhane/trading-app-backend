package com.flag3.tradingappbackend;

import com.flag3.tradingappbackend.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TransactionItemInvalidException.class)
    public ResponseEntity<ErrorResponse> handleTransactionItemInvalidException(TransactionItemInvalidException ex) {
        return new ResponseEntity<>(
                new ErrorResponse(ex.getMessage(), ex.toString()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return new ResponseEntity<>(
                new ErrorResponse(ex.getMessage(), ex.toString()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(ItemOperationUnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleItemOperationUnauthorizedException(ItemOperationUnauthorizedException ex) {
        return new ResponseEntity<>(
                new ErrorResponse(ex.getMessage(), ex.toString()),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(AssetDoesNotExistException.class)
    public ResponseEntity<ErrorResponse> handleAssetDoesNotExistException(AssetDoesNotExistException ex) {
        return new ResponseEntity<>(
                new ErrorResponse(ex.getMessage(), ex.toString()),
                HttpStatus.NOT_FOUND
        );
    }

}
