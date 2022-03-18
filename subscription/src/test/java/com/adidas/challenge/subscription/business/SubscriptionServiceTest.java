package com.adidas.challenge.subscription.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.adidas.challenge.subscription.model.Subscription;
import com.adidas.challenge.subscription.respository.SubscriptionRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


class SubscriptionServiceTest {

  private static SubscriptionRepository repository;
  private SubscriptionService service;

  @BeforeAll
  static void beforeAll() {
    repository = Mockito.mock(SubscriptionRepository.class);
  }

  @BeforeEach
  void setUp() {
    service = new SubscriptionService(repository);
  }


  @Test
  void save_whenValidSubscription_mustReturnTheSubscription() {
    // arrange
    Subscription subscription = new Subscription(UUID.randomUUID(), 1L, "first_email@adidas.com",
        null, null, LocalDate.of(2001, 1, 11), true, 1L);

    when(repository.save(any())).thenReturn(subscription);

    // act
    Subscription result = service.create(subscription);

    // assert
    assertEquals(subscription, result);
  }

  @Test
  void unsubscribe_whenReceiveNonNullUuid_mustReturnTrue() {
    // arrange
    when(repository.remove(any())).thenReturn(true);

    // act
    Boolean result = service.remove(UUID.randomUUID().toString());

    // assert
    assertTrue(result);
  }

  @Test
  void findByEmail_whenSubSubscriptionNotFound_mustReturnNull() {
    // arrange
    when(repository.findByEmail(any())).thenReturn(null);

    // act
    Subscription result = service.findByEmail("unregistered_email@adidas.com");

    // assert
    assertNull(result);
  }

  @Test
  void findByEmail_whenSubscriptionFound_mustReturnSubscription() {
    // arrange
    Subscription subscription = new Subscription(UUID.randomUUID(), 1L, "first_email@adidas.com",
        null, null, LocalDate.of(2001, 1, 11), true, 1L);

    when(repository.findByEmail(any())).thenReturn(subscription);

    // act
    Subscription result = service.findByEmail("egistered_email@adidas.com");

    // assert
    assertEquals(subscription, result);
  }

  @Test
  void findAll_whenThereAreSubscriptions_mustReturnAllSubscriptions() {
    // arrange
    Subscription subscriptionOne = new Subscription(UUID.randomUUID(), 1L, "first_email@adidas.com",
        "MyFirstName1", "male", LocalDate.of(2001, 1, 11), true, 1L);

    Subscription subscriptionTwo = new Subscription(UUID.randomUUID(), 2L,
        "second_email@adidas.com", "MyFirstName2", "male", LocalDate.of(2002, 2, 12), true,
        2L);

    when(repository.findAll()).thenReturn(Arrays.asList(subscriptionOne, subscriptionTwo));

    // act
    List<Subscription> result = service.findAll();

    // assert
    assertEquals(2, result.size());
    assertEquals(subscriptionOne, result.get(0));
    assertEquals(subscriptionTwo, result.get(1));
  }

  @Test
  void findAll_whenThereAreNotSubscriptions_mustReturnEmptyArray() {
    // arrange
    when(repository.findAll()).thenReturn(new ArrayList<>());

    // act
    List<Subscription> result = service.findAll();

    // assert
    assertTrue(result.isEmpty());
  }
}