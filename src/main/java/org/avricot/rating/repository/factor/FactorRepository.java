package org.avricot.rating.repository.factor;

import java.util.List;

import org.avricot.rating.model.company.Sector;
import org.avricot.rating.model.rating.Factor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FactorRepository extends CrudRepository<Factor, Long> {

    @Query("from Factor f where f.sector=?1 or f.sector is null order by f.order ASC")
    List<Factor> findBySectorOrSectorIsNull(Sector s);
}
