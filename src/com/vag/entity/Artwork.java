package com.vag.entity;

import java.util.Date;

public class Artwork {
    private int artworkId;
    private String title;
    private String description;
    private Date creationDate;
    private String medium;
    private String imageUrl;
    private Artist artist; 

    public Artwork() {}

    public Artwork(int artworkId, String title, String description, Date creationDate,
                   String medium, String imageUrl, Artist artist) {
        this.artworkId = artworkId;
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.medium = medium;
        this.imageUrl = imageUrl;
        this.artist = artist;
    }

    // Getters and Setters
    public int getArtworkId() {
        return artworkId;
    }

    public void setArtworkId(int artworkId) {
        this.artworkId = artworkId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "Artwork [artworkId=" + artworkId +
                ", title=" + title +
                ", description=" + description +
                ", creationDate=" + creationDate +
                ", medium=" + medium +
                ", imageUrl=" + imageUrl +
                ", artist=" + artist +
                "]";
    }
}
