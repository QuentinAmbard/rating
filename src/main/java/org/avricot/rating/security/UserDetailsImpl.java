package org.avricot.rating.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.avricot.rating.model.Role;
import org.avricot.rating.model.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

/**
 * Implementation of {@link UserDetails} of spring-security that contains a
 * {@link User}.
 */
public final class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private final User user;

    private final List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();

    /**
     * Unique constructor of {@link UserDetailsImpl} that takes a {@link User}
     * in order to construct the list of {@link GrantedAuthority}.
     */
    public UserDetailsImpl(final User user) {
        Assert.notNull(user, "user must not be null");
        Assert.notNull(grantedAuthorities, "grantedAuthorities must not be null");
        this.user = user;
        for (final Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.name()));
        }
        grantedAuthorities.add(new SimpleGrantedAuthority(Role.ROLE_USER.name()));
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof UserDetailsImpl) {
            final UserDetailsImpl userDetailsImpl = (UserDetailsImpl) obj;
            return ObjectUtils.nullSafeEquals(userDetailsImpl.getUser(), getUser());
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return 31 * ObjectUtils.nullSafeHashCode(getUser());
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new StringBuilder(getClass().getSimpleName()).append("[username:").append(getUsername()).append("]").toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.security.core.userdetails.UserDetails#getAuthorities
     * ()
     */
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }

    /**
     * Returns the current {@link User}.
     */
    public User getUser() {
        return user;
    }

}