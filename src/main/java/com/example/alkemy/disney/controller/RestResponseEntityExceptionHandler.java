package com.example.alkemy.disney.controller;

import com.example.alkemy.disney.exception.*;
import com.example.alkemy.disney.service.implementation.CharacterServiceImplementation;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    final static Logger LOGGER = Logger.getLogger(CharacterServiceImplementation.class);


    @ExceptionHandler(value = {MyNotFoundIdException.class})
    protected ResponseEntity<Object> handleNotFoundElement(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "NOT FOUND ID: "+ex.getMessage().replaceAll("[^0-9]", "")+", CHECK REQUEST PARAM";
        LOGGER.error(ex.getMessage()+" ->"+bodyOfResponse+": "+ ex.getClass());
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    protected ResponseEntity<Object> handleMismatchType(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "WRONG TYPE INPUT PARAM";
        LOGGER.error(ex.getMessage()+" ->"+bodyOfResponse+": "+ ex.getClass());
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {MyCreationWithIdException.class})
    protected ResponseEntity<Object> handleCreationWithId(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "AUTOINCREMENT ID, NO NEEDED";
        LOGGER.error(ex.getMessage()+" ->"+bodyOfResponse+": "+ ex.getClass());
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {MyNoIdUpdateException.class})
    protected ResponseEntity<Object> handleUpdateWithoutId(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "NULL ID, ID IS REQUIRED";
        LOGGER.error(ex.getMessage()+" ->"+bodyOfResponse+": "+ ex.getClass());
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {MyNoIdGenreException.class})
    protected ResponseEntity<Object> handleNoIdGenreEntity(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "NULL GENRE ID, ID IS REQUIRED";
        LOGGER.error(ex.getMessage()+" ->"+bodyOfResponse+": "+ ex.getClass());
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }


    @ExceptionHandler(value = {MyNotFoundGenreEntityException.class})
    protected ResponseEntity<Object> handleNotFoundGenreEntity(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "GENRE ID NOT FOUND: "+ex.getMessage().replaceAll("[^0-9]", "")+", CHECK REQUEST PARAM";
        LOGGER.error(ex.getMessage()+" ->"+bodyOfResponse+": "+ ex.getClass());
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }


    @ExceptionHandler(value = {MyNoNeedCharactersException.class})
    protected ResponseEntity<Object> handleListOfCharactersWhenCreatingMovie(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "THIS ENDPOINT CAN NOT UPDATE CHARACTERS";
        LOGGER.error(ex.getMessage()+" ->"+bodyOfResponse+": "+ ex.getClass());
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
