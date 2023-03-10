package ru.osipov.moneytransfer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.osipov.moneytransfer.logger.Logger;
import ru.osipov.moneytransfer.model.ErrorClass;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    private final Logger logger = Logger.getInstance();

    @ExceptionHandler(ErrorInputData.class)
    public ResponseEntity<ErrorClass> handleInputData(ErrorInputData e) {
        logger.log("Чето с картой не то: " + e.getMessage());
        return new ResponseEntity<>(new ErrorClass("Чето с картой не то: " + e.getMessage(), 1), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ErrorTransfer.class)
    public ResponseEntity<ErrorClass> handleErrorTransfer(ErrorTransfer e) {
        logger.log("Че-то не так со связью: " + e.getMessage());
        return new ResponseEntity<>(new ErrorClass("Че-то не так со связью: " + e.getMessage(), 2), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ErrorConfirmation.class)
    public ResponseEntity<ErrorClass> handleErrorConfirmation(ErrorConfirmation e) {
        logger.log("Обмануть пытаетесь?" + e.getMessage());
        return new ResponseEntity<>(new ErrorClass("Обмануть пытаетесь? ", 3), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}