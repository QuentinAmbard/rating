package org.avricot.rating.web.controller.rate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/rate")
public class RateController {

    @RequestMapping(value = { "/home" }, method = RequestMethod.GET)
    public String home(final Model model) {
        return "rate/home";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(final Model model) {
        return "rate/add";
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String home2(final Model model) {
        return "rate/show";
    }
}
