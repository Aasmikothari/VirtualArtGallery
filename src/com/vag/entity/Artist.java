package com.vag.entity;

import java.util.Date;

public class Artist {
    private int artistId;
    private String name;
    private String biography;
    private Date birthDate;
    private String nationality;
    private String website;
    private String contactInfo;

    public Artist() {}

    public Artist(int artistId, String name, String biography, Date birthDate,
                  String nationality, String website, String contactInfo) {
        this.artistId = artistId;
        this.name = name;
        this.biography = biography;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.website = website;
        this.contactInfo = contactInfo;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Override
    public String toString() {
        return "Artist [artistId=" + artistId + ", name=" + name + ", biography=" + biography +
                ", birthDate=" + birthDate + ", nationality=" + nationality + ", website=" + website +
                ", contactInfo=" + contactInfo + "]";
    }
}
