package org.avricot.rating.repository.company;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.avricot.rating.model.company.Company;
import org.avricot.rating.service.CompanySearchCriterion;
import org.avricot.rating.utils.StringUtils;

public class CompanyRepositoryImpl implements CompanyRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public List<Company> search(final Long userId, final CompanySearchCriterion criterion) {
        StringBuilder sb = new StringBuilder("select c from Company c where c.user.id=:userId");
        if (StringUtils.hasText(criterion.getCompanyBusinessId())) {
            sb.append(" and c.businessId like :businessId");
        }
        if (StringUtils.hasText(criterion.getCompanyName())) {
            sb.append(" and c.name like :name");
        }
        Query query = em.createQuery(sb.toString());
        query.setParameter("userId", userId);
        if (StringUtils.hasText(criterion.getCompanyBusinessId())) {
            query.setParameter("businessId", "%" + criterion.getCompanyBusinessId() + "%");
        }
        if (StringUtils.hasText(criterion.getCompanyName())) {
            query.setParameter("name", "%" + criterion.getCompanyName() + "%");
        }
        return query.getResultList();
    }
}
