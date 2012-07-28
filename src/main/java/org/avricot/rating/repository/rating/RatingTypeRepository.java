package org.avricot.rating.repository.rating;

import java.util.List;

import org.avricot.rating.model.company.EditionStep;
import org.avricot.rating.model.company.Sector;
import org.avricot.rating.model.rating.RatingType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RatingTypeRepository extends CrudRepository<RatingType, Long> {
    @Query("from RatingType rt where rt.step=?1 and (rt.sector=?2 or rt.sector is null)")
    List<RatingType> findByStepAndSectorOrSectorIsNull(EditionStep es, Sector sector);

    RatingType findByNameAndStep(String name, EditionStep es);
}
