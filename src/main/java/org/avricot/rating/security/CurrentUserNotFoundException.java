package org.avricot.rating.security;

/**
 * We can't acces to the current user in the current session (not logged, not
 * securized etc).
 */
public class CurrentUserNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new {@link CurrentUserNotFoundException} with a given
     * <tt>message</tt>.
     */
    public CurrentUserNotFoundException(final String message) {
        super(message);
    }

}
