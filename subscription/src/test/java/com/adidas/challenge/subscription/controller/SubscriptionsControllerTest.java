package com.adidas.challenge.subscription.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.adidas.challenge.subscription.business.SubscriptionService;
import com.adidas.challenge.subscription.model.Gender;
import com.adidas.challenge.subscription.model.Subscription;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SubscriptionsControllerTest {

  private static SubscriptionService subscriptionService;
  private SubscriptionsController subscriptionsController;

  @BeforeAll
  static void beforeAll() {
    subscriptionService = mock(SubscriptionService.class);
  }

  @BeforeEach
  void setUp() {
    subscriptionsController = new SubscriptionsController(subscriptionService);
  }

  @Test
  void findAll_WhenThereIsNoSubscriptions_mustReturnEmptyList() throws Exception {
    // arrange
    when(subscriptionService.findAll()).thenReturn(new ArrayList<>());

    // act
    List<Subscription> result = subscriptionsController.findAll();

    // assert
    assertTrue(result.isEmpty());
  }

  @Test
  void findAll_mustReturnAllSubscriptions() {

    // arrange
    Subscription subscriptionOne = new Subscription(UUID.randomUUID(), 1L, "first_email@adidas.com",
        "MyFirstName1", Gender.MALE, LocalDate.of(2001, 01, 11), true, 1L);

    Subscription subscriptionTwo = new Subscription(UUID.randomUUID(), 2L,
        "second_email@adidas.com",
        "MyFirstName2", Gender.FEMALE, LocalDate.of(2002, 02, 12), true, 2L);

    when(subscriptionService.findAll()).thenReturn(Arrays.asList(subscriptionOne, subscriptionTwo));

    // act
    List<Subscription> result = subscriptionsController.findAll();

    // assert
    assertEquals(2, result.size());
    assertEquals(subscriptionOne, result.get(0));
    assertEquals(subscriptionTwo, result.get(1));
  }

  @Test
  void findByEmail_WhenEmailNotFound_mustReturnNullObject() {
    // arrange
    when(subscriptionService.findByEmail(anyString())).thenReturn(null);

    // act
    Subscription result = subscriptionsController.findByEmail("mypersonalemail@adidas.com");

    // assert
    assertNull(result);
  }

  @Test
  void findByEmail_whenSubscriptionIsFound_mustReturnTheSubscription() {
    // arrange
    String email = "first_email@adidas.com";

    Subscription subscription = new Subscription(UUID.randomUUID(), 1L, email,
        "MyFirstName", Gender.MALE, LocalDate.of(2001, 01, 11), true, 1L);

    when(subscriptionService.findByEmail(email)).thenReturn(subscription);

    // act
    Subscription result = subscriptionsController.findByEmail(email);

    // assert
    assertEquals(subscription, result);
  }

  @Test
  void unsubscribe_WhenSubscriptionActive_mustReturnTrue() {
    // arrange
    when(subscriptionService.unsubscribe(any())).thenReturn(true);

    // act
    Boolean result = subscriptionsController.unsubscribe(UUID.randomUUID());

    // assert
    assertTrue(result);
  }

  @Test
  void unsubscribe_whenSubscriptionNotActiveOrDoesNotExist_mustReturnFalse() {
    // arrange
    when(subscriptionService.unsubscribe(any())).thenReturn(false);

    // act
    Boolean result = subscriptionsController.unsubscribe(UUID.randomUUID());

    // assert
    assertFalse(result);
  }

  @Test
  void save_whenSubscriptionIsValid_mustReturnSubscription() {
    // arrange
    Subscription subscription = new Subscription(UUID.randomUUID(), 1L, "first_email@adidas.com",
        "MyFirstName", Gender.MALE, LocalDate.of(2001, 01, 11), true, 1L);
    when(subscriptionService.save(any(Subscription.class))).thenReturn(subscription);

    // act
    Subscription result = subscriptionsController.save(subscription);

    // assert
    assertEquals(subscription, result);
  }

  @Test
  void save_whenSubscriptionIsNotValid_mustReturnNull() {
    // arrange
    Subscription subscription = new Subscription(UUID.randomUUID(), 1L, "first_email@adidas.com",
        "MyFirstName", Gender.MALE, LocalDate.of(2001, 01, 11), true, 1L);
    when(subscriptionService.save(any(Subscription.class))).thenReturn(null);

    // act
    Subscription result = subscriptionsController.save(subscription);

    // assert
    assertNull(result);
  }
}