package org.avricot.rating.service.user;

public interface IUserService {
    void updateCurrentUser(UserCommand userCommand);

    void updatePassword(PasswordCommand passwordCommand);
}
