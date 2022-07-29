package com.agileactors.usersproject.exceptions;

public class WrongMailFormatException extends RuntimeException {
    public WrongMailFormatException(String message) {
        super(message);
    }
}
