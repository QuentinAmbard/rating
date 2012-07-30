package org.avricot.rating.repository.company;

import java.util.List;

import org.avricot.rating.model.company.Company;
import org.avricot.rating.service.CompanySearchCriterion;

public interface CompanyRepositoryCustom {
    List<Company> search(Long userId, CompanySearchCriterion criterion);

}
