package com.KrampHub.BooksAndAlbums.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BookAlbum {
    private List<Album> albumList;
    private List<Book> bookList;

    @JsonProperty("Albums")
    public List<Album> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(List<Album> albumList) {
        this.albumList = albumList;
    }

    @JsonProperty("Books")
    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
