package com.vag.entity;

public class ArtworkGallery {
    private int artworkId;
    private int galleryId;

    public ArtworkGallery() {}

    public ArtworkGallery(int artworkId, int galleryId) {
        this.artworkId = artworkId;
        this.galleryId = galleryId;
    }

    public int getArtworkId() {
        return artworkId;
    }

    public void setArtworkId(int artworkId) {
        this.artworkId = artworkId;
    }

    public int getGalleryId() {
        return galleryId;
    }

    public void setGalleryId(int galleryId) {
        this.galleryId = galleryId;
    }

    @Override
    public String toString() {
        return "ArtworkGallery [artworkId=" + artworkId + ", galleryId=" + galleryId + "]";
    }
}
