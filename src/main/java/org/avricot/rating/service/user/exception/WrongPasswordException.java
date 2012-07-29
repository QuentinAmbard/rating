package org.avricot.rating.service.user.exception;

public class WrongPasswordException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public WrongPasswordException(final String message) {
        super(message);
    }
}
