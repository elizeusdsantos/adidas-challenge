package com.adidas.challenge.mailingservice.config;

import javax.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

@Component
public class QueueConfig {
  private AmqpAdmin amqpAdmin;

  public QueueConfig(AmqpAdmin amqpAdmin) {
    this.amqpAdmin = amqpAdmin;
  }

  @PostConstruct
  public void createQueues() {
    amqpAdmin.declareQueue(new Queue("new-subscriptions", false));
  }
}
