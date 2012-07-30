package org.avricot.rating.repository.shareholder;

import org.avricot.rating.model.company.ShareHolder;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ShareholderRepository extends CrudRepository<ShareHolder, Long> {
    @Modifying
    @Query("delete from ShareHolder where company.id= ?1")
    void deleteByCompanyId(Long companyId);
}
