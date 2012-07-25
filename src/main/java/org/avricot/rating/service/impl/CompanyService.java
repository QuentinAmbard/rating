package org.avricot.rating.service.impl;

import javax.inject.Inject;

import org.avricot.rating.model.company.Company;
import org.avricot.rating.repository.company.CompanyRepository;
import org.avricot.rating.service.ICompanyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CompanyService implements ICompanyService {

    @Inject
    private CompanyRepository companyRepository;

    @Override
    public void createCompany(final Company company) {
        companyRepository.save(company);
    }

}
