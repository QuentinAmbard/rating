package org.avricot.rating.web.controller.home;

import org.avricot.rating.model.user.User;
import org.avricot.rating.security.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {

    @ModelAttribute("user")
    public User getUser() {
        if (!SecurityUtils.isAuthenticated()) {
            return null;
        }
        return SecurityUtils.getCurrentUser();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String home(final Model model) {
        return "home";
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contact(final Model model) {
        return "/public/contact";
    }

    @RequestMapping(value = "/service", method = RequestMethod.GET)
    public String service(final Model model) {
        return "/public/service";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about(final Model model) {
        return "/public/about";
    }

    @RequestMapping(value = "/solution", method = RequestMethod.GET)
    public String solution(final Model model) {
        return "/public/solution";
    }

    @RequestMapping(value = "/career", method = RequestMethod.GET)
    public String career(final Model model) {
        return "/public/career";
    }

}
