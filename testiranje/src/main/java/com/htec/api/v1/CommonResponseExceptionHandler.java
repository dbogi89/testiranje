package com.htec.api.v1;


import com.htec.api.dto.response.ErrorResponse;
import com.htec.exception.CityException;
import com.htec.exception.CommnetException;
import com.htec.exception.CountryException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dbogicevic
 */
@RestController
@RestControllerAdvice
@Slf4j
public class CommonResponseExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("Validation error in rest API", ex);
        final List<String> errors = new ArrayList<String>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> errors.add(fieldError.getDefaultMessage()));
        ex.getBindingResult().getGlobalErrors().forEach(globalError -> errors.add(globalError.getDefaultMessage()));
        return ResponseEntity.badRequest().body(
                ErrorResponse.of(
                        HttpStatus.BAD_REQUEST,
                        1001,
                        "Validation failed.",
                        String.join(". ", errors))
        );
    }

    @ExceptionHandler(CityException.class)
    public ResponseEntity<Object> handleInvalidOperationException(CityException e, WebRequest webRequest) {
        log.error("CityException", e);
        return ResponseEntity.badRequest().body(
                ErrorResponse.of(
                        HttpStatus.BAD_REQUEST,
                        1002,
                        "",
                        e.getMessage())
        );
    }

    @ExceptionHandler(CommnetException.class)
    public ResponseEntity<Object> handleInvalidIdProvidedException(CommnetException e, WebRequest webRequest) {
        log.error("CommnetException", e);
        return ResponseEntity.badRequest().body(
                ErrorResponse.of(
                        HttpStatus.BAD_REQUEST,
                        1003,
                        "",
                        e.getMessage())
        );
    }

    @ExceptionHandler(CountryException.class)
    public ResponseEntity<Object> handleIdNotForCallerException(CountryException e, WebRequest webRequest) {
        log.error("CountryException", e);
        return ResponseEntity.badRequest().body(
                ErrorResponse.of(
                        HttpStatus.BAD_REQUEST,
                        1004,

                        "",
                        e.getMessage())
        );
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> globalExceptionHandler(Exception e, WebRequest webRequest) {
        log.error("Unexpected error in rest API", e);
        return ResponseEntity.badRequest().body(
                ErrorResponse.of(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        1000,
                        "Internal server error.",
                        e.getMessage())
        );
    }

}