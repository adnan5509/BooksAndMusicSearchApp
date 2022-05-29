package com.KrampHub.BooksAndAlbums.model;

public class Book {
    private String title;
    private String author;
    private String information;

    public Book() {
    }

    public Book(String title, String author, String information) {
        this.title = title;
        this.author = author;
        this.information = information;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
