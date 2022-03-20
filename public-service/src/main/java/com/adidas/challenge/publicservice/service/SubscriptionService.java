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

  // We are using a basic auth here just to keep simple, in a real project I would use oAuth2
  public void delete(String uuid) {
    subscriptionApiClient.delete().uri("/subscriptions/" + uuid)
        .headers(httpHeaders -> httpHeaders.setBasicAuth("YWRtaW46cGFzc3dvcmQ=")).retrieve()
        .bodyToMono(Boolean.class).block(Duration.ofSeconds(3));
  }

  public Subscription create(Subscription subscription) {
    return subscriptionApiClient.post().uri("/subscriptions/").bodyValue(subscription)
        .headers(httpHeaders -> httpHeaders.setBasicAuth("YWRtaW46cGFzc3dvcmQ=")).retrieve()
        .bodyToMono(Subscription.class).block(Duration.ofSeconds(3));
  }
}
