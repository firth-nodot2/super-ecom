package org.rhydo.superecom.handler;

import org.rhydo.superecom.exception.APIException;
import org.rhydo.superecom.exception.ResourceNotFoundException;
import org.rhydo.superecom.vo.APIVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class MyGlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> myMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> response = new HashMap<>();

        e.getBindingResult()
                .getAllErrors()
                .forEach((error) -> {
                    String fieldName = ((FieldError)error).getField();
                    String message = error.getDefaultMessage();
                    response.put(fieldName, message);
                });

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIVO> myResourceNotFoundException(ResourceNotFoundException e) {
        APIVO apiVO = new APIVO(e.getMessage(), false);
        return new ResponseEntity<>(apiVO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(APIException.class)
    public ResponseEntity<APIVO> myAPIException(APIException e) {
        APIVO apiVO = new APIVO(e.getMessage(), false);
        return new ResponseEntity<>(apiVO, HttpStatus.BAD_REQUEST);
    }
}
