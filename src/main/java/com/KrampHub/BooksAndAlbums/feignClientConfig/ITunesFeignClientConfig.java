package com.KrampHub.BooksAndAlbums.feignClientConfig;

import feign.codec.Decoder;
import feign.gson.GsonDecoder;
import org.springframework.context.annotation.Bean;

public class ITunesFeignClientConfig {

    @Bean
    public Decoder decoder() {
        return new GsonDecoder();
    }
}
