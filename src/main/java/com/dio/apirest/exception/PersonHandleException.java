package com.dio.apirest.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@ControllerAdvice
public class PersonHandleException  extends ResponseEntityExceptionHandler {

    @ExceptionHandler({PersonNotFoundException.class, Exception.class, RuntimeException.class, Throwable.class})
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
        List<msg> message = new ArrayList<>();
        if (ex instanceof MethodArgumentNotValidException) {
            List<ObjectError> list = ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors();
            for (ObjectError err : list) {
                message.add(new msg(err.getDefaultMessage()));
            }
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        else {
            var msg = new msg();
            msg.setMessage(ex.getMessage());
            return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        }
    }
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class msg{
    private String message;
}