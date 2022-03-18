package com.adidas.challenge.subscription.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;

public record Subscription(UUID id, @Id Long subscriptionId, @NotNull String email, String firstName,
                           String gender, @NotNull @JsonFormat(pattern = "dd/MM/yyyy") LocalDate birthDate, Boolean active,
                           @NotNull Long campaignId) {

}
