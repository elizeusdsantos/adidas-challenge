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

  public Boolean delete(String uuid) {
    return subscriptionApiClient.delete().uri("/subscriptions/remove/" + uuid).retrieve()
        .bodyToMono(Boolean.class).block(Duration.ofMillis(100));
  }

  public Boolean create(Subscription subscription) {
    return subscriptionApiClient.post().uri("/subscriptions/").bodyValue(subscription).retrieve()
        .bodyToMono(Boolean.class).block(Duration.ofMillis(100));
  }
}
