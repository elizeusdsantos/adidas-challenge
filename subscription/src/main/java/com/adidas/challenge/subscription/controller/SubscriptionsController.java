package com.adidas.challenge.subscription.controller;

import com.adidas.challenge.subscription.business.SubscriptionService;
import com.adidas.challenge.subscription.model.Subscription;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(APIConstants.V1_PATH_PREFIX + "/subscriptions")
public class SubscriptionsController {

  private final SubscriptionService subscriptionService;

  public SubscriptionsController(SubscriptionService subscriptionService) {
    this.subscriptionService = subscriptionService;
  }

  @GetMapping
  @Operation(description = "Returns all subscriptions.")
  public List<Subscription> findAll() {
    return subscriptionService.findAll();
  }

  @GetMapping("/{email}")
  @Operation(description = "Searches a subscription by email.")
  public Subscription findByEmail(@PathVariable("email") String email) {
    return subscriptionService.findByEmail(email);
  }

  @DeleteMapping(value = "/remove/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(description = "Removes logically a subscription (active = false).")
  public boolean remove(@PathVariable("uuid") String uuid) {
    return subscriptionService.remove(uuid);
  }

  @PostMapping
  @Operation(description = "Creates a new subscription.")
  public Subscription create(@RequestBody Subscription subscription) {
    return subscriptionService.create(subscription);
  }
}
