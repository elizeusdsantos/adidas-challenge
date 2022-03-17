package com.adidas.challenge.subscription.respository;

import com.adidas.challenge.subscription.model.Subscription;
import java.util.UUID;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {

  @Query("SELECT * FROM subscription WHERE email = :email")
  Subscription findByEmail(@Param("email") String email);

  @Modifying
  @Query("UPDATE subscription SET active = false WHERE id = :uuid")
  Boolean unsubscribe(@Param("uuid") UUID uuid);

}
