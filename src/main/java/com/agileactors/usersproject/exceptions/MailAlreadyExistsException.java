package com.agileactors.usersproject.exceptions;

import org.springframework.dao.DataAccessException;

public class MailAlreadyExistsException extends DataAccessException {
    public MailAlreadyExistsException(String msg) {
        super(msg);
    }
}
