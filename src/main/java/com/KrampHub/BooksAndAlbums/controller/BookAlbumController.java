package com.KrampHub.BooksAndAlbums.controller;

import com.KrampHub.BooksAndAlbums.model.Album;
import com.KrampHub.BooksAndAlbums.model.Book;
import com.KrampHub.BooksAndAlbums.model.BookAlbum;
import com.KrampHub.BooksAndAlbums.service.BookAlbumService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/BooksAndAlbums")
@CrossOrigin
public class BookAlbumController {

    @Autowired
    BookAlbumService bookAlbumService;

    @Operation(summary = "Get Books and Albums", description = "Get List of Books from Google Books and List of Albums from ITunes related to Search input")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Books and Albums Returned",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BookAlbum.class))}),
            @ApiResponse(responseCode = "404", description = "Unable to return Books and Albums",
                    content = {@Content}),
    })
    @GetMapping(value = "/getBooksAlbums/{searchText}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BookAlbum getBooksAlbumsBySearchText(@PathVariable(name = "searchText") String searchText) {
        return bookAlbumService.getBooksAlbums(searchText);
    }

    @Operation(summary = "Get Books", description = "Get List of Books from Google Books related to Search input")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Books Returned",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Book.class))}),
            @ApiResponse(responseCode = "404", description = "Unable to return Books",
                    content = {@Content}),
    })
    @GetMapping(value = "/getBooks/{searchText}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Book> getBooksBySearchText(@PathVariable(name = "searchText") String searchText) {
        return bookAlbumService.getBooks(searchText);
    }

    @Operation(summary = "Get Albums", description = "Get List of Albums from ITunes related to Search input")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Albums Returned",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Album.class))}),
            @ApiResponse(responseCode = "404", description = "Unable to return Albums",
                    content = {@Content}),
    })
    @GetMapping(value = "/getAlbums/{searchText}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Album> getAlbumsBySearchText(@PathVariable(name = "searchText") String searchText) {
        return bookAlbumService.getAlbums(searchText);
    }

}
