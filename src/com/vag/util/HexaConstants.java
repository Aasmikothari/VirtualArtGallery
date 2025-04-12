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
        """
        SELECT a.*, ar.ArtistID, ar.Name, ar.Biography, ar.BirthDate, ar.Nationality, ar.Website, ar.ContactInfo
        FROM Artwork a
        JOIN Artist ar ON a.ArtistID = ar.ArtistID
        WHERE a.ArtworkID = ?
        """;

    public static final String SEARCH_ARTWORKS = 
        """
        SELECT a.*, ar.ArtistID, ar.Name, ar.Biography, ar.BirthDate, ar.Nationality, ar.Website, ar.ContactInfo
        FROM Artwork a
        JOIN Artist ar ON a.ArtistID = ar.ArtistID
        WHERE a.Title LIKE ? OR a.Description LIKE ?
        """;

    public static final String INSERT_FAVORITE_ARTWORK = 
        "INSERT INTO User_Favorite_Artwork (UserID, ArtworkID) VALUES (?, ?)";

    public static final String DELETE_FAVORITE_ARTWORK = 
        "DELETE FROM User_Favorite_Artwork WHERE UserID = ? AND ArtworkID = ?";

    public static final String GET_USER_FAVORITE_ARTWORKS =
    	    """
    	    SELECT a.*, 
    	           ar.ArtistID, ar.Name AS ArtistName, ar.Biography, ar.BirthDate, ar.Nationality, ar.Website, ar.ContactInfo,
    	           u.UserID, u.Username, u.Email, u.Password
    	    FROM Artwork a
    	    JOIN Artist ar ON a.ArtistID = ar.ArtistID
    	    JOIN User_Favorite_Artwork ufa ON a.ArtworkID = ufa.ArtworkID
    	    JOIN User u ON ufa.UserID = u.UserID
    	    WHERE u.UserID = ?
    	    """;

    // ==== Gallery Queries ====

    public static final String INSERT_GALLERY =
        "INSERT INTO Gallery (Name, Description, Location, CuratorID, OpeningHours) VALUES (?, ?, ?, ?, ?)";

    public static final String UPDATE_GALLERY =
        "UPDATE Gallery SET Name=?, Description=?, Location=?, CuratorID=?, OpeningHours=? WHERE GalleryID=?";

    public static final String DELETE_GALLERY =
        "DELETE FROM Gallery WHERE GalleryID=?";

    public static final String SEARCH_GALLERIES =
    	    """
    	    SELECT g.*, a.ArtistID, a.Name, a.Biography, a.BirthDate, a.Nationality, a.Website, a.ContactInfo
    	    FROM Gallery g
    	    JOIN Artist a ON g.CuratorID = a.ArtistID
    	    WHERE g.Name LIKE ? OR g.Description LIKE ?
    	    """;
}