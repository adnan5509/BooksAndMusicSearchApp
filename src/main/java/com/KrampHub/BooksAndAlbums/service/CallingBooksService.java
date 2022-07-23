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
                JsonObject responseJsonObject = googleBooksFeignClient.getBooksBySearchText(searchText,
                                googleBooksApiKey,
                                "full", "books", maxResult);
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
                        String publisher = "";
                        String publishDate = "";
                        String description = "";
                        int pageCount = 0;
                        String category = "";
                        String imageLink = "";
                        String language = "";

                        JsonObject jsonItemObject = jsonItemElement.getAsJsonObject();
                        JsonObject jsonVolumeInfoObject = jsonItemObject.getAsJsonObject("volumeInfo");

                        title = jsonVolumeInfoObject.has("title") ? jsonVolumeInfoObject.get("title").getAsString()
                                        : "Title Not Available";

                        if (jsonVolumeInfoObject.has("authors")) {
                                authors = jsonVolumeInfoObject.getAsJsonArray("authors").toString();
                                authors = authors.substring(1, authors.length() - 1);
                                authors = authors.replace("\"", "");
                        } else {
                                authors = "Author Not Available";
                        }

                        publisher = jsonVolumeInfoObject.has("publisher")
                                        ? jsonVolumeInfoObject.get("publisher").getAsString()
                                        : "Publisher Not Available";

                        publishDate = jsonVolumeInfoObject.has("publishedDate")
                                        ? jsonVolumeInfoObject.get("publishedDate").getAsString()
                                        : "Published Date Not Available";

                        description = jsonVolumeInfoObject.has("description")
                                        ? jsonVolumeInfoObject.get("description").getAsString()
                                        : "Description Not Available";

                        pageCount = jsonVolumeInfoObject.has("pageCount")
                                        ? jsonVolumeInfoObject.get("pageCount").getAsInt()
                                        : 0;

                        if (jsonVolumeInfoObject.has("categories")) {
                                category = jsonVolumeInfoObject.getAsJsonArray("categories").toString();
                                category = category.substring(1, category.length() - 1);
                                category = category.replace("\"", "");
                        } else {
                                category = "Categories Not Available";
                        }

                        imageLink = jsonVolumeInfoObject.has("imageLinks")
                                        ? jsonVolumeInfoObject.get("imageLinks").getAsJsonObject().get("thumbnail")
                                                        .getAsString()
                                        : "Image Not Available";

                        language = jsonVolumeInfoObject.has("language")
                                        ? jsonVolumeInfoObject.get("language").getAsString().toUpperCase()
                                        : "Language Not Available";

                        bookList.add(new Book(title, authors, "Book", publisher, publishDate, description, pageCount,
                                        category, imageLink, language));
                }
                return bookList;
        }
}
