package com.KrampHub.BooksAndAlbums.model;

import java.time.LocalDate;
import java.util.Date;

public class Album {
    private String title;
    private String artist;
    private String information;
    private String collectionName;
    private LocalDate releaseDate;
    private String country;
    private String genre;
    private String imageLink;

    private double trackPrice;

    private double collectionPrice;

    private String trackDuration;

    private String currency;

    public Album() {
    }

    public Album(String title, String artist, String information) {
        this.title = title;
        this.artist = artist;
        this.information = information;
    }

    public Album(String title, String artist, String information, String collectionName, LocalDate releaseDate, String country, String genre, String imageLink, double trackPrice, double collectionPrice, String trackDuration, String currency) {
        this.title = title;
        this.artist = artist;
        this.information = information;
        this.collectionName = collectionName;
        this.releaseDate = releaseDate;
        this.country = country;
        this.genre = genre;
        this.imageLink = imageLink;
        this.trackPrice = trackPrice;
        this.collectionPrice = collectionPrice;
        this.trackDuration = trackDuration;
        this.currency = currency;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public double getTrackPrice() {
        return trackPrice;
    }

    public void setTrackPrice(double trackPrice) {
        this.trackPrice = trackPrice;
    }

    public double getCollectionPrice() {
        return collectionPrice;
    }

    public void setCollectionPrice(double collectionPrice) {
        this.collectionPrice = collectionPrice;
    }

    public String getTrackDuration() {
        return trackDuration;
    }

    public void setTrackDuration(String trackDuration) {
        this.trackDuration = trackDuration;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
