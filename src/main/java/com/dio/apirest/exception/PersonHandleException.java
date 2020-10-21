package com.dio.apirest.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class PersonHandleException  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<Object> PersonException(PersonNotFoundException person, WebRequest webRequest){
        var msg = new msg();
        msg.setMessage(person.getMessage());
        return handleExceptionInternal(person, msg, new HttpHeaders(), HttpStatus.NOT_FOUND, webRequest);
    }
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class msg{
    private String message;
}