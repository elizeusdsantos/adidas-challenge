package com.adidas.challenge.publicservice.controller;

import com.adidas.challenge.publicservice.dto.Subscription;
import com.adidas.challenge.publicservice.service.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(APIConstants.V1_PATH_PREFIX + "/subscriptions")
public class SubscriptionController {

  private final SubscriptionService subscriptionService;

  public SubscriptionController(SubscriptionService subscriptionService) {
    this.subscriptionService = subscriptionService;
  }


  @Operation(description = "Creates a new subscription")
  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Boolean create(@RequestBody Subscription subscription) {
    return subscriptionService.subscribe(subscription);
  }

  @Operation(description = "Removes logically a subscription")
  @DeleteMapping(value = "/remove/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Boolean remove(@PathVariable("uuid") String uuid) {
    return subscriptionService.unsubscribe(uuid);
  }
}
