package com.adidas.challenge.subscription.respository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

  // TODO: create the query.
  Optional<User> findByUsername(String username);
}
