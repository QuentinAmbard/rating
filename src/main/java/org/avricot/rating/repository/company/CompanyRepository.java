package org.avricot.rating.repository.company;

import java.util.List;

import org.avricot.rating.model.company.Company;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Long>, CompanyRepositoryCustom {

    @Query(value = "select c from Company c left join fetch c.properties p left join fetch p.values where c.id=?1 and c.user.id=?2")
    Company getByIdAndUserId(Long companyId, Long userId);

    List<Company> getByUserId(Long userId);

    @Modifying
    @Query(value = "delete from Company c where c.user.id=?1 and c.id=?2 ")
    void delete(Long userId, Long companyId);
}
