package org.avricot.rating.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;

import org.avricot.rating.model.company.Company;
import org.avricot.rating.model.company.EditionStep;
import org.avricot.rating.model.rating.RatingProperty;
import org.avricot.rating.model.rating.RatingPropertyValue;
import org.avricot.rating.model.rating.RatingType;
import org.avricot.rating.model.user.User;
import org.avricot.rating.repository.company.CompanyRepository;
import org.avricot.rating.repository.rating.RatingPropertyRepository;
import org.avricot.rating.repository.rating.RatingPropertyValueRepository;
import org.avricot.rating.repository.rating.RatingTypeRepository;
import org.avricot.rating.security.SecurityUtils;
import org.avricot.rating.security.UnauthorizedException;
import org.avricot.rating.service.CompanyAndRatingProperties;
import org.avricot.rating.service.CompanyReport;
import org.avricot.rating.service.ICompanyService;
import org.avricot.rating.service.rule.IRulesService;
import org.avricot.rating.web.controller.rate.PropertyCommand;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CompanyService implements ICompanyService {

    @Inject
    private CompanyRepository companyRepository;
    @Inject
    private RatingTypeRepository ratingTypeRepository;
    @Inject
    private RatingPropertyValueRepository ratingPropertyValueRepository;
    @Inject
    private RatingPropertyRepository ratingPropertyRepository;
    @Inject
    private IRulesService<Map<String, ?>> rulesService;

    @Override
    public Long createCompany(final Company company) {
        company.setUser(SecurityUtils.getCurrentUser());
        companyRepository.save(company);
        return company.getId();
    }

    @Override
    public CompanyAndRatingProperties getRatingProperties(final EditionStep es, final Long companyId) {
        Company company = getCompanyForCurrentUser(companyId);
        // All entries
        List<RatingType> ratingTypes = ratingTypeRepository.findByStepAndSectorOrSectorIsNull(es, company.getSector());
        Map<RatingType, RatingProperty> properties = new LinkedHashMap<RatingType, RatingProperty>();
        for (RatingType type : ratingTypes) {
            properties.put(type, null);
        }
        for (Entry<RatingType, RatingProperty> e : company.getProperties().entrySet()) {
            if (e.getKey().getStep() == es && (e.getKey().getSector() == null || e.getKey().getSector() == company.getSector())) {
                properties.put(e.getKey(), e.getValue());
            }
        }
        return new CompanyAndRatingProperties(company, properties);
    }

    @Override
    public void updateRatingProperties(final EditionStep es, final Long companyId, final PropertyCommand command) {
        Company company = getCompanyForCurrentUser(companyId);
        for (Entry<String, Map<Integer, String>> e : command.getProperties().entrySet()) {
            RatingType ratingType = ratingTypeRepository.findByNameAndStep(e.getKey(), es);
            RatingProperty property = company.getProperties().get(ratingType);
            if (property == null) {
                property = new RatingProperty(company, ratingType);
            }
            for (Entry<Integer, String> values : e.getValue().entrySet()) {
                RatingPropertyValue propertyValue = property.getValues().get(values.getKey());
                if (propertyValue == null) {
                    propertyValue = new RatingPropertyValue(property, values.getKey());
                }
                propertyValue.setValue(values.getValue());
                property.getValues().put(values.getKey(), propertyValue);
            }
            ratingPropertyRepository.save(property);
        }
    }

    private Company getCompanyForCurrentUser(final Long companyId) {
        User currentUser = SecurityUtils.getCurrentUser();
        Company company = companyRepository.getByIdAndUserId(companyId, currentUser.getId());
        if (company == null) {
            throw new UnauthorizedException("can't access company " + companyId + ", " + currentUser.getId());
        }
        return company;
    }

    @Override
    public CompanyReport getCompanyReport(final Long companyId) {
        Company company = getCompanyForCurrentUser(companyId);
        Map<String, RatingProperty> properties = new HashMap<String, RatingProperty>();
        for (Entry<RatingType, RatingProperty> e : company.getProperties().entrySet()) {
            properties.put(e.getKey().getName(), e.getValue());
        }
        Map<String, ?> result = rulesService.executeRules(new HashMap<String, Object>(), "result", new String[] { "prop", "compay" }, new Object[] { properties, company });
        return new CompanyReport();
    }
}
