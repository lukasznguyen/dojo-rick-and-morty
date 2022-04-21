package com.example.demo.rickandmorty.exceptions;

public class InvalidSeasonNumberException extends Exception {

    public InvalidSeasonNumberException(String message) {
        super(message);
    }

    public InvalidSeasonNumberException(String message, Throwable cause) {
        super(message, cause);
    }
}
