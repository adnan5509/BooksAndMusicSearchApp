package com.KrampHub.BooksAndAlbums;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients("com.KrampHub.BooksAndAlbums.feignClients")
public class BooksAndAlbumsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BooksAndAlbumsApplication.class, args);
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Google Books and ITunes Albums Search")
                        .description("API to get Books from Google Books and Albums from ITunes")
                );
    }
}
