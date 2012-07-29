package org.avricot.rating.service;

import java.util.List;
import java.util.Map;

import org.avricot.rating.model.company.Company;
import org.avricot.rating.model.rating.Result;

public class CompanyReport {
    private Company company;
    private final Map<String, List<Result>> resultTypes;
    private Map<String, Object> results;

    public CompanyReport(final Company company, final Map<String, List<Result>> resultTypes, final Map<String, Object> results) {
        this.company = company;
        this.results = results;
        this.resultTypes = resultTypes;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(final Company company) {
        this.company = company;
    }

    public Map<String, List<Result>> getResultTypes() {
        return resultTypes;
    }

    public Map<String, Object> getResults() {
        return results;
    }

    public void setResults(final Map<String, Object> results) {
        this.results = results;
    }

}
