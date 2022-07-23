package com.KrampHub.BooksAndAlbums.model;

import java.time.LocalDate;
import java.util.Date;

public class Book {
    private String title;
    private String author;
    private String information;
    private String publisher;
    private String publishDate;
    private String description;
    private int pageCount;
    private String categories;
    private String imageLink;
    private String language;

    public Book() {
    }

    public Book(String title, String author, String information) {
        this.title = title;
        this.author = author;
        this.information = information;
    }

    public Book(String title, String author, String information, String publisher, String publishDate,
                String description,
                int pageCount, String categories, String imageLink, String language) {
        this.title = title;
        this.author = author;
        this.information = information;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.description = description;
        this.pageCount = pageCount;
        this.categories = categories;
        this.imageLink = imageLink;
        this.language = language;
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}
