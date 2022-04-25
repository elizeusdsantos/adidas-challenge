package com.adidas.challenge.subscription.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

public record Subscription(UUID id, @Id Long subscriptionId, @NotNull String email,
                           String firstName, String gender,
                           @NotNull @JsonFormat(pattern = "dd/MM/yyyy") LocalDate birthDate,
                           Boolean active, @NotNull Long campaignId) implements Serializable {

}
