package com.bigjava.exception;

/**
 * Created by dbogicevic
 */
public class CountryException extends RuntimeException{

    public CountryException() {
    }

    public CountryException(String message) {
        super(message);
    }
}
