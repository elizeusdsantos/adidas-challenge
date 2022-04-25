package com.adidas.challenge.mailingservice;

import com.adidas.challenge.subscription.model.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        LOGGER.info("Starting the mailing server.");
    }

    @RabbitListener(queues = "new-subscriptions")
    public void listen(Subscription subscription) {
        Map<String, String> mailProperties = new HashMap<>();
        mailProperties.put("address", subscription.email());
        mailProperties.put("uuid", subscription.id().toString());

        sendEmailNewSubscriptions(mailProperties);
    }

    public void sendEmailNewSubscriptions(Map<String, String> mailProperties) {
        String fakeText =
                "Hello, thanks for your subscription. To unsubscribe click on this link: http://localhost:8080/adidas-public-service/v1/subscriptions/delete/"
                        + mailProperties.get("uuid");
        LOGGER.info(fakeText);
    }
}