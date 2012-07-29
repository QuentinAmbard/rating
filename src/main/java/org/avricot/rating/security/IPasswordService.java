package org.avricot.rating.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface IPasswordService {
    /**
     * Create a random hash.
     */
    String generateHash();

    /**
     * Encode the given password with the given user salt.
     */
    String encodePassword(UserDetails user, String password);

    /**
     * Create a random salt string.
     */
    String generateSalt();

    /**
     * Create a random password. Return a plain-text password.
     */
    String generatePassword();

}
