package com.adidas.challenge.subscription.business;

import com.adidas.challenge.subscription.model.Subscription;
import com.adidas.challenge.subscription.respository.SubscriptionRepository;
import com.google.common.collect.Lists;
import java.util.LinkedHashMap;
import java.util.LinkedList;
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
  public Subscription save(Subscription subscription) {
    subscription = includeUUID(subscription);
    return repository.save(subscription);
  }

  // cancel a subscription
  public Boolean unsubscribe(UUID uuid) {
    return repository.unsubscribe(uuid);
  }

  // get details of a subscription
  public Subscription findByEmail(String email) {
    return repository.findByEmail(email);
  }

  // get all subscriptions
  public List<Subscription> findAll() {
    Iterable<Subscription> subscriptions = repository.findAll();
    LinkedHashMap<String, String> x = new LinkedHashMap<>();

    return Lists.newArrayList(subscriptions);
  }

  private Subscription includeUUID(Subscription subscription) {
    return new Subscription(UUID.randomUUID(),
        subscription.subscriptionId(), subscription.email(), subscription.firstName(),
        subscription.gender(), subscription.birthDate(), subscription.active(),
        subscription.campaignId());
  }
}
