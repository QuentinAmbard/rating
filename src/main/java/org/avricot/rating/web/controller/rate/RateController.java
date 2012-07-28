package org.avricot.rating.web.controller.rate;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import org.avricot.rating.model.company.Company;
import org.avricot.rating.model.company.EditionStep;
import org.avricot.rating.model.company.Sector;
import org.avricot.rating.service.CompanyAndRatingProperties;
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
        Long companyId = companyService.createCompany(command);
        return "redirect:/rate/edit/" + companyId + "/0";
    }

    @RequestMapping(value = "/edit/{companyId}/{step}", method = RequestMethod.POST)
    public String editStep(final Model model, @PathVariable final Long companyId, @PathVariable final int step, final PropertyCommand command) {
        EditionStep es = EditionStep.getByOrdinal(step);
        if (es == null) {
            return "redirect:/rate/edit/" + companyId + "/0";
        }
        companyService.updateRatingProperties(es, companyId, command);
        if (es.getNext() == null) {
            return "redirect:/rate/show/" + companyId;
        }
        return "redirect:/rate/edit/" + companyId + "/" + es.getNext().ordinal();
    }

    @RequestMapping(value = "/edit/{companyId}/{step}", method = RequestMethod.GET)
    public String editStep(final Model model, @PathVariable final Long companyId, @PathVariable final int step) throws ParseException {
        EditionStep es = EditionStep.getByOrdinal(step);
        if (es == null) {
            es = EditionStep.getByOrdinal(0);
        }
        if (companyId == null) {
            return "redirect:/rate/home";
        }
        CompanyAndRatingProperties data = companyService.getRatingProperties(es, companyId);
        Integer currentYear = Calendar.getInstance().get(Calendar.YEAR);
        List<Integer> years = new ArrayList<Integer>();
        for (int i = 0; i < 3; i++) {
            years.add(currentYear - i);
        }
        model.addAttribute("years", years);
        // new int[] { 2, 1, 0 }
        model.addAttribute("currentYear", currentYear);
        model.addAttribute("data", data);
        model.addAttribute("step", es);
        return "rate/edit";
    }

    @RequestMapping(value = "/show/{companyId}", method = RequestMethod.GET)
    public String show(final Model model, @PathVariable final Long companyId) throws ParseException {
        companyService.getCompanyReport(companyId);
        return "rate/show";
    }

}
