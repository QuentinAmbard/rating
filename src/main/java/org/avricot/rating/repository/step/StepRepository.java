package org.avricot.rating.repository.step;

import java.util.List;

import org.avricot.rating.model.company.Sector;
import org.avricot.rating.model.company.Step;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface StepRepository extends CrudRepository<Step, Long> {

    @Query("from Step s where s.sector = null or s.sector=?1 order by s.order ASC")
    List<Step> getBySector(Sector sector);

    @Query("from Step s where s.order>?1 and (s.sector = null or s.sector=?2) order by s.order ASC")
    List<Step> getNext(Integer stepOrder, Sector sector, Pageable pageable);
}
