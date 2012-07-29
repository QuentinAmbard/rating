package org.avricot.rating.security;

/**
 * The password size is too small.
 */
public class PasswordSizeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public PasswordSizeException(String message) {
        super(message);
    }
}
