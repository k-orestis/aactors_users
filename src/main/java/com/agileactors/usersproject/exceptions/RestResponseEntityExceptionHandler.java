package com.agileactors.usersproject.exceptions;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = MailAlreadyExistsException.class)
    protected ResponseEntity<Object> invalidMail(
            RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage() + " already exists", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = WrongMailFormatException.class)
    protected ResponseEntity<Object> wrongMail(
            RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage() + " has wrong format", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = DataAccessException.class)
    protected ResponseEntity<Object> invalidPost(
            RuntimeException ex) {
        return new ResponseEntity<>("All fields required", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidIdException.class)
    protected ResponseEntity<Object> invalidIdHandler(Exception e){
        return new ResponseEntity<>("Invalid ID", HttpStatus.NOT_FOUND);
    }
}
