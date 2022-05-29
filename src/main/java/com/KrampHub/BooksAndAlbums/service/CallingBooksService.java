package com.KrampHub.BooksAndAlbums.service;

import com.KrampHub.BooksAndAlbums.feignClients.GoogleBooksFeignClient;
import com.KrampHub.BooksAndAlbums.model.Book;
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
public class CallingBooksService {

    Logger logger = LoggerFactory.getLogger(CallingBooksService.class);

    @Value("${googleBooks.apiKey}")
    private String googleBooksApiKey;

    @Value("${apiCalls.maxResult}")
    private int maxResult;

    @Autowired
    GoogleBooksFeignClient googleBooksFeignClient;

    @CircuitBreaker(name = "GoogleBooks", fallbackMethod = "fallbackMethodGetBooks")
    public List<Book> getBooks(String searchText) {
        JsonObject responseJsonObject = googleBooksFeignClient.getBooksBySearchText(searchText, googleBooksApiKey, "lite", "books", maxResult);
        List<Book> bookList = parseGoogleBooksResponse(responseJsonObject);
        bookList.sort(Comparator.comparing(book -> book.getTitle()));
        return bookList;
    }

    public List<Book> fallbackMethodGetBooks(String searchText, Throwable th) {
        logger.error("Error for Google Books Service" + th);
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("Unable to Fetch", "Unable to Fetch", "Unable to Fetch"));
        return bookList;
    }


    public List<Book> parseGoogleBooksResponse(JsonObject jsonBooksObject) {
        List<Book> bookList = new ArrayList<>();
        JsonArray jsonItemsArray = jsonBooksObject.getAsJsonArray("items");
        for (JsonElement jsonItemElement : jsonItemsArray) {
            String title = "";
            String authors = "";
            JsonObject jsonItemObject = jsonItemElement.getAsJsonObject();
            JsonObject jsonVolumeInfoObject = jsonItemObject.getAsJsonObject("volumeInfo");
            if (jsonVolumeInfoObject.has("title")) {
                title = jsonVolumeInfoObject.get("title").getAsString();
            } else {
                title = "Title Not Available";
            }
            if (jsonVolumeInfoObject.has("authors")) {
                authors = jsonVolumeInfoObject.getAsJsonArray("authors").toString();
                authors = authors.substring(1, authors.length() - 1);
                authors = authors.replace("\"", "");
            } else {
                authors = "Author Not Available";
            }
            bookList.add(new Book(title, authors, "Book"));
        }
        return bookList;
    }
}
