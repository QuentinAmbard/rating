package org.avricot.rating.security;

import java.util.Collection;

import javax.inject.Inject;

import org.avricot.rating.model.user.User;
import org.avricot.rating.repository.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring security {@link UserDetailsService} implementation. Load a user given
 * its email and returns a {@link UserDetailsImpl} if the user exists.
 */
@Service
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private final UserRepository userRepository;

    private final RoleHierarchy roleHierarchy;

    /**
     * Unique constructor.
     */
    @Inject
    public UserDetailsServiceImpl(final UserRepository userRepository, final RoleHierarchy roleHierarchy) {
        this.userRepository = userRepository;
        this.roleHierarchy = roleHierarchy;

    }

    @Override
    public UserDetails loadUserByUsername(final String username) {
        LOGGER.debug("Processing loadUserByUsername() for username '{}'", username);

        final User user = userRepository.findByEmail(username);

        if (user == null) {
            LOGGER.debug("Couldn't find user for username '{}'", username);
            throw new UsernameNotFoundException("Couldn't find user '" + username + "'");
        }
        LOGGER.debug("Find user '{}' '{}', building UserDetails", user);
        UserDetailsImpl userDetails = new UserDetailsImpl(user);
        Collection<? extends GrantedAuthority> authorities = roleHierarchy.getReachableGrantedAuthorities(userDetails.getAuthorities());
        userDetails.getAuthorities().clear();
        userDetails.getAuthorities().addAll(authorities);
        return userDetails;
    }
}