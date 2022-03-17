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

  public Boolean unsubscribe(String uuid) {
    Boolean response = subscriptionApiClient.get().uri("/subscriptions/remove/" + uuid).retrieve()
        .bodyToMono(Boolean.class).block(Duration.ofSeconds(3));
    return response;
  }

  public Boolean subscribe(Subscription subscription) {
    throw new UnsupportedOperationException();
  }
}
