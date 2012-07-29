package org.avricot.rating.service.user;

public class UniqueEmailException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UniqueEmailException(final String string) {
        super(string);
    }

}
