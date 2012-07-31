package org.avricot.rating.repository.fork;

import org.avricot.rating.model.company.Fork;
import org.springframework.data.repository.CrudRepository;

public interface ForkRepository extends CrudRepository<Fork, Long> {
    Fork findByName(String name);
}
