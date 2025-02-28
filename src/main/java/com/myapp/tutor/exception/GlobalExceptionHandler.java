package com.myapp.tutor.exception;

import com.myapp.tutor.dto.MessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> globalExceptionHandler(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return errors;
    }

//    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Area not found exception")
    @ResponseStatus(HttpStatus.NOT_FOUND) // возможно это излишне - обрабатывает необработанные исключения
    @ExceptionHandler(StudentNotFoundException.class)
    public MessageDto studentNotFound(StudentNotFoundException ex){
        return new MessageDto(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND) // TODO возможно это излишне - обрабатывает необработанные исключения
    @ExceptionHandler(ObjectNotFoundCustomException.class)
    public MessageDto objectNotFoundCustomException(ObjectNotFoundCustomException ex){
        return new MessageDto(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RefreshTokenExpiredException.class)
    public MessageDto refreshTokenExpired(RefreshTokenExpiredException ex){
        return new MessageDto(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmailAlreadyExistException.class)
    public MessageDto emailAlreadyExist(EmailAlreadyExistException ex){
        return new MessageDto(ex.getMessage());
    }

}
