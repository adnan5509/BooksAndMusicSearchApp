package com.KrampHub.BooksAndAlbums.model;

public class Album {
    private String title;
    private String artist;
    private String information;

    public Album() {
    }

    public Album(String title, String artist, String information) {
        this.title = title;
        this.artist = artist;
        this.information = information;
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
}
