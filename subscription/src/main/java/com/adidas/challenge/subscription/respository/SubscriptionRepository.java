package com.adidas.challenge.subscription.respository;

import com.adidas.challenge.subscription.model.Subscription;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {

    @Query("SELECT * FROM subscription WHERE email = :email")
    Subscription findByEmail(@Param("email") String email);

    @Modifying
    @Query("UPDATE subscription SET active = false WHERE id = :uuid")
    Boolean remove(@Param("uuid") UUID uuid);

    @Modifying
    @Query("UPDATE subscription SET active = true, campaign_id = :campaignId WHERE id = :uuid")
    Boolean reactivate(@Param("uuid") UUID uuid, @Param("campaignId") Long campaignId);
}
