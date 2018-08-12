package com.example.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@SpringBootApplication
@EnableScheduling
public class RabbitmqApplication {

    @Profile("usage_message")
    @Bean
    public CommandLineRunner usage() {
        return arg0 -> {
            log.info("This app uses Spring Profiles to control its behavior.\n");
            log.info("Sample usage: java -jar rabbit - tutorials.jar " +
                    "--spring.profiles.active = hello - world, sender");
        };
    }

    @Profile("!usage_message")
    @Bean
    public CommandLineRunner tutorial() {
        return new RabbitAmqpTutorialsRunner();
    }

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqApplication.class, args);
    }
}
