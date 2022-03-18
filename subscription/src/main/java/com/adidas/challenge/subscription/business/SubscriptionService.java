package com.adidas.challenge.subscription.business;

import com.adidas.challenge.subscription.model.Subscription;
import com.adidas.challenge.subscription.respository.SubscriptionRepository;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {

  private final SubscriptionRepository repository;

  public SubscriptionService(SubscriptionRepository repository) {
    this.repository = repository;
  }

  // create a new subscription
  public Subscription create(Subscription subscription) {
    Subscription existentSubscription = repository.findByEmail(subscription.email());

    if (existentSubscription != null) {
      if (existentSubscription.active()) {
        return existentSubscription;
      }
      return repository.reactivate(existentSubscription.id(), subscription.campaignId());
    }

    subscription = activateSubscription(includeUUID(subscription));

    return repository.save(subscription);
  }

  // cancel a subscription
  public Boolean remove(String uuidString) {
    UUID uuid = UUID.fromString(uuidString);

    return repository.remove(uuid);
  }

  // get details of a subscription
  public Subscription findByEmail(String email) {
    return repository.findByEmail(email);
  }

  // get all subscriptions
  public List<Subscription> findAll() {
    Iterable<Subscription> subscriptions = repository.findAll();

    return Lists.newArrayList(subscriptions);
  }

  // Helper to include UUID.
  private Subscription includeUUID(Subscription subscription) {
    return new Subscription(UUID.randomUUID(), subscription.subscriptionId(), subscription.email(),
        subscription.firstName(), subscription.gender(), subscription.birthDate(),
        subscription.active(), subscription.campaignId());
  }

  // Helper to activate the subscription.
  private Subscription activateSubscription(Subscription subscription) {
    return new Subscription(UUID.randomUUID(), subscription.subscriptionId(), subscription.email(),
        subscription.firstName(), subscription.gender(), subscription.birthDate(), true,
        subscription.campaignId());
  }
}
