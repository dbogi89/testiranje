package com.bigjava.exception;

/**
 * Created by dbogicevic
 */
public class CityException extends RuntimeException{

    public CityException() {
    }

    public CityException(String message) {
        super(message);
    }
}
