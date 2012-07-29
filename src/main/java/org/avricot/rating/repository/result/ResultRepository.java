package org.avricot.rating.repository.result;

import java.util.List;

import org.avricot.rating.model.company.Sector;
import org.avricot.rating.model.rating.Result;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ResultRepository extends CrudRepository<Result, Long> {
    @Query("from Result r where r.sector=?1 or r.sector is null order by r.order ASC")
    List<Result> findBySectorOrSectorIsNull(Sector sector);
}
