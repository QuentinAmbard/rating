package org.avricot.rating.web.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping(method = RequestMethod.GET)
    public String home(final Model model) {
        return "home";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String home2(final Model model) {
        return "home";
    }
}
