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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

@Service
public class CallingAlbumsService {

    Logger logger = LoggerFactory.getLogger(CallingAlbumsService.class);

    @Autowired
    ITunesAlbumsFeignClient iTunesAlbumsFeignClient;

    @Value("${apiCalls.maxResult}")
    private int maxResult;

    @CircuitBreaker(name = "ITunes", fallbackMethod = "fallbackMethodGetAlbums")
    public List<Album> getAlbums(String searchText) {
        JsonObject itunesAlbumsResponse = iTunesAlbumsFeignClient.getAlbumsBySearchText(searchText, "musicTrack",
                maxResult);
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
            String collectionName = "";
            LocalDate releaseDate = null;
            String country = "";
            String genre = "";
            String imageLink = "";
            double trackPrice = 0.00;
            double collectionPrice = 0.00;
            String trackDuration = "";
            String currency = "";

            JsonObject jsonResultObject = jsonResultElement.getAsJsonObject();

            title = jsonResultObject.has("trackName") ? jsonResultObject.get("trackName").getAsString()
                    : "Track Title Not Available";
            artist = jsonResultObject.has("artistName") ? jsonResultObject.get("artistName").getAsString()
                    : "Artist Not Available";
            if (jsonResultObject.has("releaseDate")) {
                DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'",
                        Locale.ENGLISH);
                DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
                LocalDate date = LocalDate.parse(jsonResultObject.get("releaseDate").getAsString(), inputFormatter);
                String formattedDate = outputFormatter.format(date);
                releaseDate = LocalDate.parse(formattedDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }
            collectionName = jsonResultObject.has("collectionName")
                    ? jsonResultObject.get("collectionName").getAsString()
                    : "Collection Name Not Available";
            country = jsonResultObject.has("country") ? jsonResultObject.get("country").getAsString()
                    : "Country Name Not Available";
            genre = jsonResultObject.has("primaryGenreName") ? jsonResultObject.get("primaryGenreName").getAsString()
                    : "Genre Not Available";
            imageLink = jsonResultObject.has("artworkUrl100") ? jsonResultObject.get("artworkUrl100").getAsString()
                    : "Image Not Available";

            trackPrice = jsonResultObject.has("trackPrice") ? jsonResultObject.get("trackPrice").getAsDouble()
                    : 0.00;

            collectionPrice = jsonResultObject.has("collectionPrice") ? jsonResultObject.get("collectionPrice").getAsDouble()
                    : 0.00;

            if (jsonResultObject.has("trackTimeMillis")) {
                long durationInMilliSecs = jsonResultObject.get("trackTimeMillis").getAsLong();
                long minutesDuration = (durationInMilliSecs / 1000) / 60;
                int secondsDuration = (int) ((durationInMilliSecs / 1000) % 60);
                trackDuration = minutesDuration + " Minutes & " + secondsDuration + " seconds";
            } else {
                trackDuration = "Track Duration Not available";
            }

            currency = jsonResultObject.has("currency") ? jsonResultObject.get("currency").getAsString()
                    : "Currency Not Available";

            albumList.add(new Album(title, artist, "Album", collectionName, releaseDate, country, genre, imageLink, trackPrice, collectionPrice, trackDuration, currency));
        }
        return albumList;
    }
}
