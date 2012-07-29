package org.avricot.rating.web.controller.rate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.avricot.rating.model.company.Company;
import org.avricot.rating.model.company.EditionStep;
import org.avricot.rating.model.company.Sector;
import org.avricot.rating.service.CompanyAndRatingProperties;
import org.avricot.rating.service.CompanyCommand;
import org.avricot.rating.service.CompanyReport;
import org.avricot.rating.service.CompanySearchCriterion;
import org.avricot.rating.service.ICompanyService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @InitBinder
    public void binder(final WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String toAdd(final Model model) {
        model.addAttribute("command", new Company());
        model.addAttribute("sectors", Sector.values());
        return "rate/add";
    }

    @RequestMapping(value = "/add/{companyId}", method = RequestMethod.GET)
    public String toAddExisting(final Model model, @PathVariable final Long companyId) {
        Company company = companyService.getCompanyForCurrentUser(companyId);
        model.addAttribute("command", company);
        model.addAttribute("sectors", Sector.values());
        return "rate/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(final Model model, @Valid @ModelAttribute("command") final CompanyCommand command, final BindingResult result) {
        if (result.hasErrors()) {
            return "rate/add";
        }
        Long companyId = companyService.updateCompany(command);
        if (command.isNext()) {
            return "redirect:/rate/edit/" + companyId + "/0";
        }
        return "redirect:/rate/add/" + companyId;
    }

    @RequestMapping(value = "/edit/{companyId}/{step}", method = RequestMethod.POST)
    public String editStep(final Model model, @PathVariable final Long companyId, @PathVariable final int step, @ModelAttribute("command") final PropertyCommand command) {
        EditionStep es = EditionStep.getByOrdinal(step);
        if (es == null) {
            return "redirect:/rate/edit/" + companyId + "/0";
        }
        companyService.updateRatingProperties(es, companyId, command);
        if (!command.isNext()) {
            return "redirect:/rate/edit/" + companyId + "/" + es.ordinal();
        }
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
        for (int i = 0; i < data.getCompany().getYearNumber(); i++) {
            years.add(currentYear - i);
        }
        model.addAttribute("years", years);
        // new int[] { 2, 1, 0 }
        model.addAttribute("currentYear", currentYear);
        model.addAttribute("data", data);
        model.addAttribute("step", es);
        return "rate/edit";
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String show(final Model model) {
        List<Company> companies = companyService.getCompanies();
        model.addAttribute("companies", companies);
        return "rate/view";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(final Model model, @ModelAttribute final CompanySearchCriterion criterion) {
        List<Company> companies = companyService.getCompanies(criterion);
        model.addAttribute("companies", companies);
        return "rate/view";
    }

    @RequestMapping(value = "/show/{companyId}", method = RequestMethod.GET)
    public String show(final Model model, @PathVariable final Long companyId) throws ParseException {
        CompanyReport companies = companyService.getCompanyReport(companyId);
        model.addAttribute("companies", companies);
        return "rate/show";
    }

}
