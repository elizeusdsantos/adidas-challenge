package com.adidas.challenge.subscription.model;

import java.time.LocalDate;
import java.util.UUID;

public record Subscription(UUID id,
                           Long subscriptionId,
                           String email,
                           String firstName,
                           Gender gender,
                           LocalDate birthDate,
                           Boolean active,
                           Long campaignId) {

}
