package org.avricot.rating.service.sector.impl;

import javax.inject.Inject;

import org.avricot.rating.model.company.Sector;
import org.avricot.rating.repository.sector.SectorRepository;
import org.avricot.rating.service.sector.ISectorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SectorService implements ISectorService {

    @Inject
    private SectorRepository sectorRepository;

    @Override
    public Iterable<Sector> findAll() {
        return sectorRepository.findAll();
    }

}
