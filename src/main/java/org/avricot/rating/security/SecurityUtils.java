package org.avricot.rating.security;

import javax.servlet.http.HttpServletRequest;

import org.avricot.rating.model.Role;
import org.avricot.rating.model.user.User;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.Assert;

/**
 * Security helper that provides static methods to access to the current
 * {@link User}, know if the current user is authenticated, or if he has a
 * {@link Role} or not.
 */
public final class SecurityUtils {

    private static final WebAuthenticationDetailsSource AUTHENTIFICATION_DETAILS_SOURCE = new WebAuthenticationDetailsSource();

    /**
     * No public constructor for utility class.
     */
    private SecurityUtils() {
    }

    /**
     * Returns <tt>true</tt> if the current user is authenticated or
     * <tt>false</tt> if he isn't or he is anonymous.
     */
    public static boolean isAuthenticated() {
        final Authentication authentication = SecurityUtils.getAuthentication();
        if (authentication == null) {
            return false;
        }
        if (authentication instanceof AnonymousAuthenticationToken) {
            return false;
        }
        return authentication.isAuthenticated();
    }

    /**
     * Returns the current {@link User} or throws an
     * {@link CurrentUserNotFoundException} if the user is not authenticated or
     * if there is a security issue.
     */
    public static User getCurrentUser() {
        final Authentication authentication = SecurityUtils.getAuthentication();
        if (authentication == null) {
            throw new CurrentUserNotFoundException("Current user not found in security context");
        }
        if (!authentication.isAuthenticated()) {
            throw new CurrentUserNotFoundException("Current user is not authenticated");

        }
        if (authentication instanceof AnonymousAuthenticationToken) {
            throw new CurrentUserNotFoundException("Current user is anonymous");
        }
        return ((UserDetailsImpl) authentication.getPrincipal()).getUser();
    }

    /**
     * Returns <tt>true</tt> if the current user has at least one of the given
     * <tt>roles</tt> else returns <tt>false</tt> if the current user is not
     * authenticated or if he has any of the given <tt>roles</tt>.
     */
    public static boolean hasAnyRole(final Role... roles) {
        Assert.notNull(roles, "roles must not be null");
        Assert.noNullElements(roles, "roles must not have null elements");

        final Authentication authentication = SecurityUtils.getAuthentication();
        if (authentication == null) {
            return false;
        }
        if (!authentication.isAuthenticated()) {
            return false;
        }
        if (authentication instanceof AnonymousAuthenticationToken) {
            return false;
        }

        for (final Role role : roles) {
            if (authentication.getAuthorities().contains(new SimpleGrantedAuthority(role.name()))) {
                return true;
            }
        }
        return false;

    }

    /**
     * Returns <tt>true</tt> if the current user has all the given
     * <tt>roles</tt> else returns <tt>false</tt> if the current user is not
     * authenticated or if he has not all the given <tt>roles</tt>.
     */
    public static boolean hasAllRoles(final Role... roles) {
        Assert.notNull(roles, "roles must not be null");
        Assert.noNullElements(roles, "roles must not have null elements");

        final Authentication authentication = SecurityUtils.getAuthentication();
        if (authentication == null) {
            return false;
        }
        if (!authentication.isAuthenticated()) {
            return false;
        }
        if (authentication instanceof AnonymousAuthenticationToken) {
            return false;
        }

        for (final Role role : roles) {
            if (!authentication.getAuthorities().contains(new SimpleGrantedAuthority(role.name()))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the IP address of current user if exists or throws a
     * {@link CurrentUserNotFoundException}.
     */
    public static String getCurrentIP() {
        final Authentication authentication = SecurityUtils.getAuthentication();
        if (authentication == null) {
            throw new CurrentUserNotFoundException("Current user not found in security context");
        }
        return ((WebAuthenticationDetails) authentication.getDetails()).getRemoteAddress();
    }

    /**
     * Updates security context with the given user data.
     */
    public static void updateCurrentUser(final User user) {
        final Authentication authentication = SecurityUtils.getAuthentication();
        if (authentication == null) {
            throw new CurrentUserNotFoundException("Current user not found in security context");
        }
        final UserDetails details = new UserDetailsImpl(user);
        final AbstractAuthenticationToken newAuthentication = new UsernamePasswordAuthenticationToken(details, details.getPassword(), details.getAuthorities());
        newAuthentication.setDetails(authentication.getDetails());
        SecurityContextHolder.getContext().setAuthentication(newAuthentication);
    }

    /**
     * Logs-in automatically the given user.
     */
    public static void login(final User user, final HttpServletRequest request) {
        final UserDetails details = new UserDetailsImpl(user);
        final AbstractAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(details, details.getPassword(), details.getAuthorities());

        authentication.setDetails(AUTHENTIFICATION_DETAILS_SOURCE.buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    /**
     * Private method that returns the {@link Authentication} contains in the
     * current {@link org.springframework.security.core.context.SecurityContext}
     * .
     */
    private static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}