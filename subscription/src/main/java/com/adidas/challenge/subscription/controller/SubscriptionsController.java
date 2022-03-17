package com.adidas.challenge.subscription.controller;

import com.adidas.challenge.subscription.business.SubscriptionService;
import com.adidas.challenge.subscription.model.Subscription;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(APIConstants.V1_PATH_PREFIX + "/subscriptions")
public class SubscriptionsController {

  private final SubscriptionService subscriptionService;

  public SubscriptionsController(SubscriptionService subscriptionService) {
    this.subscriptionService = subscriptionService;
  }

  @GetMapping
  public List<Subscription> findAll() {
    return subscriptionService.findAll();
  }

  @GetMapping("/{email}")
  public Subscription findByEmail(@PathVariable("email") String email) {
    return subscriptionService.findByEmail(email);
  }

  // TODO: rename to remove
  @RequestMapping(value = "/remove/{uuid}", method = RequestMethod.GET, produces = { "application/json" })
  public boolean remove(@PathVariable("uuid") String uuid) {
    return true;
//    return subscriptionService.unsubscribe(uuid);
  }

  // TODO: rename to create
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Subscription create(@RequestBody Subscription subscription) {
    return subscriptionService.save(subscription);
  }
}
