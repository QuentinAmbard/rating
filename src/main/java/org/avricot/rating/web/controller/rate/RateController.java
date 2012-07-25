package org.avricot.rating.web.controller.rate;

import javax.inject.Inject;

import org.avricot.rating.model.company.Company;
import org.avricot.rating.model.company.EditionStep;
import org.avricot.rating.model.company.Sector;
import org.avricot.rating.service.ICompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/rate")
public class RateController {
    @Inject
    ICompanyService companyService;

    @RequestMapping(value = { "/home" }, method = RequestMethod.GET)
    public String home(final Model model) {
        return "rate/home";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String toAdd(final Model model) {
        model.addAttribute("command", new Company());
        model.addAttribute("sectors", Sector.values());
        return "rate/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(final Model model, final Company command) {
        companyService.createCompany(command);
        return "redirect:/rate/edit/0";
    }

    @RequestMapping(value = "/add/{step}", method = RequestMethod.POST)
    public String editStep(final Model model, final Company command, @PathVariable final int step) {
        EditionStep es = EditionStep.getByOrdinal(step);
        if (es == null) {
            es = EditionStep.getByOrdinal(0);
        }
        model.addAttribute("step", es.getNext());
        return "rate/edit";
    }
}
