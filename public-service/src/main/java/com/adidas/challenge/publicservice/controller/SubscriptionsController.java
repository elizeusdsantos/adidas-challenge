package com.adidas.challenge.publicservice.controller;

import com.adidas.challenge.publicservice.dto.Subscription;
import com.adidas.challenge.publicservice.service.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(APIConstants.V1_PATH_PREFIX + "/subscriptions")
public class SubscriptionsController {

  private final SubscriptionService subscriptionService;

  public SubscriptionsController(SubscriptionService subscriptionService) {
    this.subscriptionService = subscriptionService;
  }


  @Operation(description = "Creates a new subscription")
  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Subscription create(@RequestBody Subscription subscription) {
    return subscriptionService.create(subscription);
  }

  @Operation(description = "Removes logically a subscription")
  @GetMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
  public void remove(@RequestParam String uuid) {
    subscriptionService.delete(uuid);
  }
}
