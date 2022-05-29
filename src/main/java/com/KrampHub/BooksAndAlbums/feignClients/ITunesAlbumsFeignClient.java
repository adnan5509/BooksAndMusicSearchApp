package com.KrampHub.BooksAndAlbums.feignClients;

import com.KrampHub.BooksAndAlbums.feignClientConfig.ITunesFeignClientConfig;
import com.google.gson.JsonObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "https://itunes.apple.com", name = "ITunes", configuration = ITunesFeignClientConfig.class)
public interface ITunesAlbumsFeignClient {
    @GetMapping("/search")
    JsonObject getAlbumsBySearchText(@RequestParam(name = "term") String searchText, @RequestParam(name = "entity") String entity, @RequestParam(name = "limit") int limit);
}
