package org.avricot.rating.repository.manager;

import org.avricot.rating.model.company.Manager;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ManagerRepository extends CrudRepository<Manager, Long> {
    @Modifying
    @Query("delete from Manager where company.id= ?1")
    void deleteByCompanyId(Long companyId);
}
