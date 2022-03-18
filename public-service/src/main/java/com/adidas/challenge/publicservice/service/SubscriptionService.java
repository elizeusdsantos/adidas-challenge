package com.adidas.challenge.publicservice.service;

import com.adidas.challenge.publicservice.dto.Subscription;
import java.time.Duration;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class SubscriptionService {

  private final WebClient subscriptionApiClient;

  public SubscriptionService(WebClient subscriptionApiClient) {
    this.subscriptionApiClient = subscriptionApiClient;
  }

  public void delete(String uuid) {
    subscriptionApiClient.delete().uri("/subscriptions/" + uuid).retrieve()
        .bodyToMono(Boolean.class).block(Duration.ofSeconds(3));
  }

  public Subscription create(Subscription subscription) {
    return subscriptionApiClient.post().uri("/subscriptions/").bodyValue(subscription).retrieve()
        .bodyToMono(Subscription.class).block(Duration.ofSeconds(3));
  }
}
