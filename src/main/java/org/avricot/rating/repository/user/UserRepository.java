package org.avricot.rating.repository.user;

import org.avricot.rating.model.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query("select u from User u left join fetch u.roles where u.email = ?1")
    User findByEmail(String email);
}
