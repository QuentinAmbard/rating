package org.avricot.rating.service.user;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class PasswordCommand {
    @NotEmpty
    private String oldPassword;
    @NotEmpty
    @Size(min = 4)
    private String password;
    @Size(min = 4)
    @NotEmpty
    private String password2;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(final String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(final String password2) {
        this.password2 = password2;
    }

}
