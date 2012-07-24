package org.avricot.rating.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/errors")
public class ErrorsController {

    /**
     * Error 403.
     */
    @RequestMapping("/403")
    public String error403(final Model model) {
        model.addAttribute("errorCode", "error.code.403");
        return "error/403";
    }

    /**
     * Error 404.
     */
    @RequestMapping("/404")
    public String error404(final Model model) {
        model.addAttribute("errorCode", "error.code.404");
        return "error/404";
    }

    /**
     * Error 500.
     */
    @RequestMapping("/500")
    public String error500(final Model model) {
        model.addAttribute("errorCode", "error.code.500");
        return "error/503";
    }

    /**
     * Error 500.
     */
    @RequestMapping("/503")
    public String error503(final Model model) {
        model.addAttribute("errorCode", "error.code.500");
        return "error/503";
    }
}
