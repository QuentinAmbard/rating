package org.avricot.rating.repository.company;

import org.avricot.rating.model.company.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Long> {

    @Query(value = "select c from Company c left join fetch c.properties p left join fetch p.values where c.id=?1 and c.user.id=?2")
    Company getByIdAndUserId(Long companyId, Long userId);
}
