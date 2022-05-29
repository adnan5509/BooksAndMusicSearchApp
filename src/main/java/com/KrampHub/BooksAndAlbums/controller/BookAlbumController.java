package com.KrampHub.BooksAndAlbums.controller;

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

@RestController
@RequestMapping("/api/BooksAndAlbums")
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
}
