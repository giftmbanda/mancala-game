package com.giftmbanda.mancalagame;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableCaching
@EnableMongoRepositories
@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Mancala Game REST API",
                version = "1.0",
                description = "Spring Boot REST API for the classic Mancala game. The application is built with Spring Boot and utilizes MongoDB for the database and Redis for caching game data. The application can be run locally on your host machine or as a containerized environment using Docker.",
                contact = @io.swagger.v3.oas.annotations.info.Contact(
                        name = "Gift Banda ",
                        email = "giftmbanda@gmail.com",
                        url = "https://giftmbanda.com/"
                )
        )
)

public class MancalaGameApplication {
    public static void main(String[] args) {
        SpringApplication.run(MancalaGameApplication.class, args);
    }
}
