package com.adidas.challenge.subscription.business;

import com.adidas.challenge.subscription.model.Subscription;
import com.adidas.challenge.subscription.respository.SubscriptionRepository;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.UUID;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {

  private final SubscriptionRepository repository;
  private final RabbitTemplate rabbitTemplate;

  public SubscriptionService(SubscriptionRepository repository, RabbitTemplate rabbitTemplate) {
    this.repository = repository;
    this.rabbitTemplate = rabbitTemplate;
  }

  // create a new subscription
  public Subscription create(Subscription subscription) {
    Subscription currentSubscription = repository.findByEmail(subscription.email());

    if (currentSubscription != null) {
      if (Boolean.TRUE.equals(currentSubscription.active())) {
        return currentSubscription;
      }

      repository.reactivate(currentSubscription.id(), subscription.campaignId());
      subscription = repository.findByEmail(subscription.email());

      rabbitTemplate.convertAndSend("new-subscriptions", subscription);

      return subscription;
    }

    subscription = activateSubscription(includeUUID(subscription));
    subscription = repository.save(subscription);

    rabbitTemplate.convertAndSend("new-subscriptions", subscription);

    return subscription;
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
