package org.avricot.rating.repository.rating;

import java.util.List;

import org.avricot.rating.model.company.Sector;
import org.avricot.rating.model.rating.RatingType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RatingTypeRepository extends CrudRepository<RatingType, Long> {
    @Query("select rt from RatingType rt left join fetch rt.steps steps left join steps.step step left join fetch rt.sectors sector where step.id=?1 and (sector=?2 or sector is null) order by rt.order ASC")
    List<RatingType> findByStepAndSectorOrSectorIsNull(Long stepId, Sector sector);

    @Query("select rt from RatingType rt left join fetch rt.steps steps left join steps.step step where step.id=?2 and rt.name=?1 ")
    RatingType findByNameAndSteps(String name, Long stepId);
}
