package com.vag.entity;

public class Gallery {
    private int galleryId;
    private String name;
    private String description;
    private String location;
    private int curatorId;
    private String openingHours;

    public Gallery() {}

    public Gallery(int galleryId, String name, String description, String location,
                   int curatorId, String openingHours) {
        this.galleryId = galleryId;
        this.name = name;
        this.description = description;
        this.location = location;
        this.curatorId = curatorId;
        this.openingHours = openingHours;
    }

    public int getGalleryId() {
        return galleryId;
    }

    public void setGalleryId(int galleryId) {
        this.galleryId = galleryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCuratorId() {
        return curatorId;
    }

    public void setCuratorId(int curatorId) {
        this.curatorId = curatorId;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    @Override
    public String toString() {
        return "Gallery [galleryId=" + galleryId + ", name=" + name + ", description=" + description +
                ", location=" + location + ", curatorId=" + curatorId + ", openingHours=" + openingHours + "]";
    }
}
