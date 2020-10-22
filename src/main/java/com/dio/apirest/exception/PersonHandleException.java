package com.dio.apirest.exception;

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
import java.util.NoSuchElementException;

@RestControllerAdvice
@ControllerAdvice
public class PersonHandleException  extends ResponseEntityExceptionHandler {

    //Intercept NoSuchElementException e outras.
    @ExceptionHandler({NoSuchElementException.class, Exception.class, RuntimeException.class})
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest webRequest){
        if (ex instanceof MethodArgumentNotValidException) {
            List<MsgErro> message = new ArrayList<>();
            List<ObjectError> list = ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors();
            for (ObjectError err : list) {
                message.add(new MsgErro(err.getDefaultMessage()));
            }
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else {
            var msg = new MsgErro();
            msg.setMessage(ex.getMessage());
            return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        }
    }

    //Intercept exceções do tipo PersonNotFoundException, devido ser personalizada não recebe todos elementos
    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<Object> personException(Exception ex){
        var msg = new MsgErro();
        msg.setMessage(ex.getMessage());
        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    }
}
