package com.vag.util;

public class HexaConstants {

    // ==== Artwork Queries ====
    public static final String INSERT_ARTWORK = 
        "INSERT INTO Artwork (Title, Description, CreationDate, Medium, ImageURL, ArtistID) VALUES (?, ?, ?, ?, ?, ?)";
    
    public static final String UPDATE_ARTWORK = 
        "UPDATE Artwork SET Title = ?, Description = ?, CreationDate = ?, Medium = ?, ImageURL = ?, ArtistID = ? WHERE ArtworkID = ?";
    
    public static final String DELETE_ARTWORK = 
        "DELETE FROM Artwork WHERE ArtworkID = ?";
    
    public static final String GET_ARTWORK_BY_ID = 
        "SELECT * FROM Artwork WHERE ArtworkID = ?";
    
    public static final String SEARCH_ARTWORKS = 
        "SELECT * FROM Artwork WHERE Title LIKE ? OR Description LIKE ?";
    
    public static final String INSERT_FAVORITE_ARTWORK = 
        "INSERT INTO User_Favorite_Artwork (UserID, ArtworkID) VALUES (?, ?)";
    
    public static final String DELETE_FAVORITE_ARTWORK = 
        "DELETE FROM User_Favorite_Artwork WHERE UserID = ? AND ArtworkID = ?";
    
    public static final String GET_USER_FAVORITE_ARTWORKS = 
        "SELECT a.* FROM Artwork a INNER JOIN User_Favorite_Artwork ufa ON a.ArtworkID = ufa.ArtworkID WHERE ufa.UserID = ?";

    // ==== Gallery Queries ====
    public static final String INSERT_GALLERY =
        "INSERT INTO Gallery (Name, Description, Location, CuratorID, OpeningHours) VALUES (?, ?, ?, ?, ?)";

    public static final String UPDATE_GALLERY =
        "UPDATE Gallery SET Name=?, Description=?, Location=?, CuratorID=?, OpeningHours=? WHERE GalleryID=?";

    public static final String DELETE_GALLERY =
        "DELETE FROM Gallery WHERE GalleryID=?";

    public static final String SEARCH_GALLERIES =
        "SELECT * FROM Gallery WHERE Name LIKE ? OR Description LIKE ?";

}
