package com.adidas.challenge.subscription.model;

import java.time.LocalDate;
import java.util.UUID;
import javax.validation.constraints.NotNull;

public record Subscription(UUID id, Long subscriptionId, @NotNull String email, String firstName,
                           Gender gender, @NotNull LocalDate birthDate, Boolean active,
                           @NotNull Long campaignId) {

}
