package org.avricot.rating.web.controller.profile;

import javax.inject.Inject;
import javax.validation.Valid;

import org.avricot.rating.model.user.User;
import org.avricot.rating.security.SecurityUtils;
import org.avricot.rating.service.user.IUserService;
import org.avricot.rating.service.user.PasswordCommand;
import org.avricot.rating.service.user.UniqueEmailException;
import org.avricot.rating.service.user.UserCommand;
import org.avricot.rating.service.user.exception.PasswordNotEqualsException;
import org.avricot.rating.service.user.exception.WrongPasswordException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @ModelAttribute("user")
    public User getUser() {
        return SecurityUtils.getCurrentUser();
    }

    @Inject
    IUserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(final Model model) {
        model.addAttribute("userCommand", new UserCommand(getUser()));
        model.addAttribute("passwordCommand", new PasswordCommand());
        return "/profile/profileHome";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(final Model model, @Valid @ModelAttribute final UserCommand userCommand, final BindingResult result) {
        model.addAttribute("passwordCommand", new PasswordCommand());
        if (result.hasErrors()) {
            return "/profile/profileHome";
        }
        try {
            userService.updateCurrentUser(userCommand);
        } catch (UniqueEmailException e) {
            result.rejectValue("email", "email.already.existing", "email existing");
            return "/profile/profileHome";
        }
        return "/profile/profileHome";
    }

    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public String password(final Model model, @Valid @ModelAttribute final PasswordCommand passwordCommand, final BindingResult result) {
        model.addAttribute("userCommand", new UserCommand());
        if (result.hasErrors()) {
            return "/profile/profileHome";
        }
        try {
            userService.updatePassword(passwordCommand);
        } catch (PasswordNotEqualsException e) {
            result.rejectValue("password", "password.not.equals", "password not equals");
            return "/profile/profileHome";
        } catch (WrongPasswordException e) {
            result.rejectValue("password", "password.incorrect", "password incorrect");
            return "/profile/profileHome";
        }
        return "/profile/profileHome";

    }

}
