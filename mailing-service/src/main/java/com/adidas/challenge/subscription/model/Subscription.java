package com.adidas.challenge.subscription.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

public record Subscription(UUID id, Long subscriptionId, String email,
                           String firstName, String gender,
                           @JsonFormat(pattern = "dd/MM/yyyy") LocalDate birthDate,
                           Boolean active, Long campaignId) implements Serializable {
}
