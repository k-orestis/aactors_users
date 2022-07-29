package com.agileactors.usersproject.exceptions;

import org.springframework.dao.DataAccessException;

public class InvalidPostBodyException extends DataAccessException {
    public InvalidPostBodyException(String msg) {
        super(msg);
    }
}
