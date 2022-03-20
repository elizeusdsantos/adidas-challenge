package com.adidas.challenge.mailingservice;

import com.adidas.challenge.subscription.model.Subscription;
import java.util.HashMap;
import java.util.Map;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

  }

  @RabbitListener(queues = "new-subscriptions")
  public void listen(Subscription subscription) {
    Map<String, String> mailProperties = new HashMap<>();
    mailProperties.put("address", subscription.email());
    mailProperties.put("uuid", subscription.id().toString());

    sendEmailNewSubscriptions(mailProperties);
  }

  public void sendEmailNewSubscriptions(Map<String, String> mailProperties){
    String fakeText = "Hello, thanks for your subscription. To unubscribe click on this link: " + mailProperties.get("uuid");
    System.out.println(fakeText);
  }
}