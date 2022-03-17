package com.adidas.challenge.publicservice.dto;

import javax.validation.constraints.NotNull;

public record Subscription(@NotNull String email, String firstName, Gender gender,
                           @NotNull String birthDate, @NotNull Long campaignId) {

}
