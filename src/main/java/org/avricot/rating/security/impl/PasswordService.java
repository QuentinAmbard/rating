package org.avricot.rating.security.impl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;

import org.apache.commons.lang.RandomStringUtils;
import org.avricot.rating.security.IPasswordService;
import org.avricot.rating.security.PasswordSizeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class PasswordService implements IPasswordService {
    private final PasswordEncoder passwordEncoder;
    private final SaltSource saltSource;
    private final int saltLentgh;
    private final int passwordLength;

    @Inject
    public PasswordService(final SaltSource saltSource, final PasswordEncoder passwordEncoder, @Value("${security.password.length}") final int passwordLength,
            @Value("${security.salt.length}") final int saltLength) {
        this.saltSource = saltSource;
        this.passwordEncoder = passwordEncoder;
        this.passwordLength = passwordLength;
        this.saltLentgh = saltLength;
    }

    @Override
    public String encodePassword(final UserDetails user, final String password) {
        if (password == null || password.length() < passwordLength) {
            throw new PasswordSizeException("min password size is " + passwordLength + " password is" + password);
        }
        return passwordEncoder.encodePassword(password, saltSource.getSalt(user));
    }

    @Override
    public String generateSalt() {
        return generateRandom(saltLentgh);
    }

    @Override
    public String generatePassword() {
        return generateRandom(passwordLength);
    }

    @Override
    public String generateHash() {
        byte[] bytes = generateRandom(20).getBytes();
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        byte[] hash = md.digest(bytes);
        BigInteger i = new BigInteger(1, hash);
        return String.format("%1$032x", i);
    }

    /**
     * Generate a random string of the given length.
     */
    protected String generateRandom(final int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

}
