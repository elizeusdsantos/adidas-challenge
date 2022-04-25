package com.adidas.challenge.publicservice.controller;

import com.adidas.challenge.publicservice.dto.Gender;
import com.adidas.challenge.publicservice.dto.Subscription;
import com.adidas.challenge.publicservice.service.SubscriptionService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SubscriptionsControllerTest {

    private static SubscriptionService subscriptionService;
    private static SubscriptionsController subscriptionsController;

    @BeforeAll
    static void beforeAll() {
        subscriptionService = mock(SubscriptionService.class);
    }

    @BeforeEach
    void setUp() {
        subscriptionsController = new SubscriptionsController(subscriptionService);
    }

    @Test
    void create_whenSubscriptionIsValid_mustReturnSubscription() {
        // arrange
        Subscription subscription = new Subscription("first_email@adidas.com", "MyFirstName",
                Gender.MALE, "11/11/2001", 1L);
        when(subscriptionService.create(any(Subscription.class))).thenReturn(subscription);

        // act
        Subscription result = subscriptionsController.create(subscription);

        // assert
        assertEquals(subscription, result);
    }

    @Test
    void create_whenSubscriptionIsNotValid_mustReturnNull() {
        // arrange
        Subscription subscription = new Subscription("first_email@adidas.com", "MyFirstName",
                Gender.MALE, "11/11/2001", 1L);
        when(subscriptionService.create(any(Subscription.class))).thenReturn(null);

        // act
        Subscription result = subscriptionsController.create(subscription);

        // assert
        assertNull(result);
    }

}