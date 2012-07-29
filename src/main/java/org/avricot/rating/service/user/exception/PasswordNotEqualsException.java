package org.avricot.rating.service.user.exception;

public class PasswordNotEqualsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public PasswordNotEqualsException(final String message) {
        super(message);
    }
}
