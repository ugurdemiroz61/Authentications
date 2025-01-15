package com.uur.Authentications.exceptions;

import javax.naming.AuthenticationException;

public class UnAuthorizeException extends AuthenticationException {
    public UnAuthorizeException(String message) {
        super(message);
    }
}
