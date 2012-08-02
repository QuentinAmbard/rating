package org.avricot.rating.service.sector;

import org.avricot.rating.model.company.Sector;

public interface ISectorService {
    Iterable<Sector> findAll();
}
