package com.example.alkemy.disney.exception.controller;

import com.example.alkemy.disney.exception.MessageException;
import com.example.alkemy.disney.exception.MyEntityIdControlException;
import com.example.alkemy.disney.exception.MyNotFoundIdException;
import com.example.alkemy.disney.exception.MyUniqueControlException;
import com.example.alkemy.disney.service.implementation.CharacterServiceImplementation;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    final static Logger LOGGER = Logger.getLogger(CharacterServiceImplementation.class);


    @ExceptionHandler(value = {MyNotFoundIdException.class})
    protected ResponseEntity<Object> handleNotFoundElement(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "NOT FOUND: "+ex.getMessage()+", CHECK REQUEST PARAMS";
        LOGGER.error(bodyOfResponse);
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    protected ResponseEntity<Object> handleMismatchType(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "WRONG TYPE INPUT PARAM: " +ex.getLocalizedMessage();
        LOGGER.error(bodyOfResponse);
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {MyEntityIdControlException.class})
    protected ResponseEntity<Object> handleEntityIdControl(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        LOGGER.error(bodyOfResponse);
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {MyUniqueControlException.class})
    protected ResponseEntity<Object> handleUniqueControl(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        LOGGER.error(bodyOfResponse);
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }



    // ------------ OVERRIDE METHODS ------------

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        MessageException bodyOfResponse = new MessageException(LocalDateTime.now().format(formatter), status.value()+" -> " + status.getReasonPhrase(),  new ArrayList<>());
        bodyOfResponse.getMessage().add("IT IS NECESSARY TO VALIDATE INPUT DATA");

        for (FieldError fieldError: ex.getBindingResult().getFieldErrors()) {

            bodyOfResponse.getMessage().add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
        }
        for (ObjectError objectError : ex.getBindingResult().getGlobalErrors()) {

            bodyOfResponse.getMessage().add(objectError.getObjectName() + ": " + objectError.getDefaultMessage());
        }

        LOGGER.error(bodyOfResponse.toString());

        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String bodyOfResponse = null;

        if (ex.getMostSpecificCause().getMessage().indexOf(";") != -1){

            bodyOfResponse = "WRONG TYPE INPUT PARAM: " + ex.getMostSpecificCause().getMessage().substring(0,ex.getMostSpecificCause().getMessage().indexOf(";"));
        }else {

            bodyOfResponse = "WRONG TYPE INPUT PARAM: " + ex.getMostSpecificCause().getMessage();
        }

        LOGGER.error(bodyOfResponse);
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}
