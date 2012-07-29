package org.avricot.rating.service.user.impl;

import javax.inject.Inject;

import org.avricot.rating.model.user.User;
import org.avricot.rating.repository.user.UserRepository;
import org.avricot.rating.security.IPasswordService;
import org.avricot.rating.security.SecurityUtils;
import org.avricot.rating.security.UserDetailsImpl;
import org.avricot.rating.service.user.IUserService;
import org.avricot.rating.service.user.PasswordCommand;
import org.avricot.rating.service.user.UniqueEmailException;
import org.avricot.rating.service.user.UserCommand;
import org.avricot.rating.service.user.exception.PasswordNotEqualsException;
import org.avricot.rating.service.user.exception.WrongPasswordException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService implements IUserService {

    @Inject
    private UserRepository userRepository;
    @Inject
    private IPasswordService passwordService;

    @Override
    public void updateCurrentUser(final UserCommand userCommand) {
        User user = userRepository.findByEmail(SecurityUtils.getCurrentUser().getEmail());
        user.setFirstname(userCommand.getFirstname());
        user.setLastname(userCommand.getLastname());
        if (!userCommand.getEmail().equals(user.getEmail())) {
            User existingUser = userRepository.findByEmail(userCommand.getEmail());
            if (existingUser != null) {
                throw new UniqueEmailException("a user with email" + userCommand.getEmail() + " already exists");
            }
            user.setEmail(userCommand.getEmail());
        }
        SecurityUtils.updateCurrentUser(user);
    }

    @Override
    public void updatePassword(final PasswordCommand passwordCommand) {
        User currentUser = SecurityUtils.getCurrentUser();
        if (!passwordCommand.getPassword().equals(passwordCommand.getPassword2())) {
            throw new PasswordNotEqualsException("password not equals");
        }
        UserDetailsImpl user = new UserDetailsImpl(currentUser);
        String oldPassword = passwordService.encodePassword(user, passwordCommand.getOldPassword());
        if (!oldPassword.equals(currentUser.getPassword())) {
            throw new WrongPasswordException("password isn't correct");
        }
        String newPassword = passwordService.encodePassword(user, passwordCommand.getPassword());
        User u = userRepository.findByEmail(SecurityUtils.getCurrentUser().getEmail());
        u.setPassword(newPassword);
        SecurityUtils.updateCurrentUser(u);
    }
}
