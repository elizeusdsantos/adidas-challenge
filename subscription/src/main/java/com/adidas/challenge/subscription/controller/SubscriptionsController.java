package com.adidas.challenge.subscription.controller;

import com.adidas.challenge.subscription.business.SubscriptionService;
import com.adidas.challenge.subscription.model.Subscription;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/")
    @Operation(description = "Searches a subscription by email.")
    public Subscription findByEmail(@RequestParam String email) {
        return subscriptionService.findByEmail(email);
    }

    @DeleteMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Deletes logically a subscription (active = false).")
    public boolean remove(@RequestParam String uuid) {
        return subscriptionService.remove(uuid);
    }

    @PostMapping
    @Operation(description = "Creates a new subscription.")
    public Subscription create(@RequestBody Subscription subscription) {
        return subscriptionService.create(subscription);
    }
}
