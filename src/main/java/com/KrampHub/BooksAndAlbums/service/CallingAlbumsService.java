package com.KrampHub.BooksAndAlbums.service;

import com.KrampHub.BooksAndAlbums.feignClients.ITunesAlbumsFeignClient;
import com.KrampHub.BooksAndAlbums.model.Album;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class CallingAlbumsService {

    Logger logger = LoggerFactory.getLogger(CallingAlbumsService.class);

    @Autowired
    ITunesAlbumsFeignClient iTunesAlbumsFeignClient;

    @Value("${apiCalls.maxResult}")
    private int maxResult;

    @CircuitBreaker(name = "ITunes", fallbackMethod = "fallbackMethodGetAlbums")
    public List<Album> getAlbums(String searchText) {
        JsonObject itunesAlbumsResponse = iTunesAlbumsFeignClient.getAlbumsBySearchText(searchText, "musicTrack", maxResult);
        List<Album> albumList = parseITunesAlbumsResponse(itunesAlbumsResponse);
        albumList.sort(Comparator.comparing(album -> album.getTitle()));
        return albumList;
    }

    public List<Album> fallbackMethodGetAlbums(String searchText, Throwable th) {
        logger.error("Error for ITunes Albums Service" + th);
        List<Album> albumList = new ArrayList<>();
        albumList.add(new Album("Unable to Fetch", "Unable to Fetch", "Unable to Fetch"));
        return albumList;
    }

    public List<Album> parseITunesAlbumsResponse(JsonObject jsonAlbumsObject) {
        List<Album> albumList = new ArrayList<>();
        JsonArray jsonResultsArray = jsonAlbumsObject.getAsJsonArray("results");
        for (JsonElement jsonResultElement : jsonResultsArray) {
            String title = "";
            String artist = "";
            JsonObject jsonResultObject = jsonResultElement.getAsJsonObject();
            if (jsonResultObject.has("collectionName")) {
                title = jsonResultObject.get("collectionName").getAsString();
            } else {
                title = "Album Title Not Available";
            }
            if (jsonResultObject.has("artistName")) {
                artist = jsonResultObject.get("artistName").getAsString();
            } else {
                artist = "Artist Not Available";
            }
            albumList.add(new Album(title, artist, "Album"));
        }
        return albumList;
    }
}
