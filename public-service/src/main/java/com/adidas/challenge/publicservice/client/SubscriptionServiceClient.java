package com.adidas.challenge.publicservice.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class SubscriptionServiceClient {

    @Value("${apis.subscription_api_url}")
    private String subscriptionApiUrl;

    @Bean
    public WebClient client() {
        return WebClient.create(subscriptionApiUrl);
    }

}
