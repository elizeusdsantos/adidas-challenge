package com.adidas.challenge.publicservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Public Service API", version = "1.0", description = "Provides a public interface to all api services"))
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
