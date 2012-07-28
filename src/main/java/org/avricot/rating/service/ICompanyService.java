package org.avricot.rating.service;

import org.avricot.rating.model.company.Company;
import org.avricot.rating.model.company.EditionStep;
import org.avricot.rating.web.controller.rate.PropertyCommand;

public interface ICompanyService {
    Long createCompany(Company command);

    CompanyAndRatingProperties getRatingProperties(EditionStep es, Long companyId);

    void updateRatingProperties(EditionStep es, Long companyId, PropertyCommand command);

    CompanyReport getCompanyReport(Long companyId);
}
