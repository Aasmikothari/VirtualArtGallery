package com.vag.entity;

public class UserFavoriteArtwork {
    private int userId;
    private int artworkId;

    public UserFavoriteArtwork() {}

    public UserFavoriteArtwork(int userId, int artworkId) {
        this.userId = userId;
        this.artworkId = artworkId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getArtworkId() {
        return artworkId;
    }

    public void setArtworkId(int artworkId) {
        this.artworkId = artworkId;
    }

    @Override
    public String toString() {
        return "UserFavoriteArtwork [userId=" + userId + ", artworkId=" + artworkId + "]";
    }
}
