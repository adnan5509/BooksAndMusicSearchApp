package com.KrampHub.BooksAndAlbums.feignClients;

import com.KrampHub.BooksAndAlbums.feignClientConfig.GoogleBooksFeignClientConfig;
import com.google.gson.JsonObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "https://www.googleapis.com/books/v1", name = "GoogleBooks", configuration = GoogleBooksFeignClientConfig.class)
public interface GoogleBooksFeignClient {
    @GetMapping("/volumes")
    JsonObject getBooksBySearchText(@RequestParam(name = "q") String searchText, @RequestParam(name = "key") String key, @RequestParam(name = "projection") String projection, @RequestParam(name = "printType") String printType, @RequestParam(name = "maxResults") int maxResults);
}