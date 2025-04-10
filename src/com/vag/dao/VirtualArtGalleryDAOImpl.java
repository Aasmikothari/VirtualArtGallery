package com.vag.dao;

import com.vag.entity.*;
import com.vag.util.*;
import com.vag.exception.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VirtualArtGalleryDAOImpl implements IVirtualArtGallery {

    private static Connection connection;

    public VirtualArtGalleryDAOImpl() {
        connection = DBConnection.getConnection();
    }

    @Override
    public boolean addArtwork(Artwork artwork) {
        String sql = "INSERT INTO Artwork (Title, Description, CreationDate, Medium, ImageURL, ArtistID) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, artwork.getTitle());
            ps.setString(2, artwork.getDescription());
            ps.setDate(3, new java.sql.Date(artwork.getCreationDate().getTime()));
            ps.setString(4, artwork.getMedium());
            ps.setString(5, artwork.getImageUrl());
            ps.setInt(6, artwork.getArtistId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateArtwork(Artwork artwork) {
        String sql = "UPDATE Artwork SET Title = ?, Description = ?, CreationDate = ?, Medium = ?, ImageURL = ?, ArtistID = ? WHERE ArtworkID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, artwork.getTitle());
            ps.setString(2, artwork.getDescription());
            ps.setDate(3, new java.sql.Date(artwork.getCreationDate().getTime()));
            ps.setString(4, artwork.getMedium());
            ps.setString(5, artwork.getImageUrl());
            ps.setInt(6, artwork.getArtistId());
            ps.setInt(7, artwork.getArtworkId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean removeArtwork(int artworkId) {
        String sql = "DELETE FROM Artwork WHERE ArtworkID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, artworkId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Artwork getArtworkById(int artworkId) throws ArtWorkNotFoundException{
        String sql = "SELECT * FROM Artwork WHERE ArtworkID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, artworkId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Artwork(
                    rs.getInt("ArtworkID"),
                    rs.getString("Title"),
                    rs.getString("Description"),
                    rs.getDate("CreationDate"),
                    rs.getString("Medium"),
                    rs.getString("ImageURL"),
                    rs.getInt("ArtistID")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Artwork> searchArtworks(String keyword) {
        List<Artwork> artworks = new ArrayList<>();
        String sql = "SELECT * FROM Artwork WHERE Title LIKE ? OR Description LIKE ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            String likeKeyword = "%" + keyword + "%";
            ps.setString(1, likeKeyword);
            ps.setString(2, likeKeyword);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                artworks.add(new Artwork(
                    rs.getInt("ArtworkID"),
                    rs.getString("Title"),
                    rs.getString("Description"),
                    rs.getDate("CreationDate"),
                    rs.getString("Medium"),
                    rs.getString("ImageURL"),
                    rs.getInt("ArtistID")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artworks;
    }

    @Override
    public boolean addArtworkToFavorite(int userId, int artworkId) {
        String sql = "INSERT INTO User_Favorite_Artwork (UserID, ArtworkID) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, artworkId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean removeArtworkFromFavorite(int userId, int artworkId) {
        String sql = "DELETE FROM User_Favorite_Artwork WHERE UserID = ? AND ArtworkID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, artworkId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Artwork> getUserFavoriteArtworks(int userId) throws UserNotFoundException {
        List<Artwork> favorites = new ArrayList<>();
        String sql = "SELECT a.* FROM Artwork a INNER JOIN User_Favorite_Artwork ufa ON a.ArtworkID = ufa.ArtworkID WHERE ufa.UserID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                favorites.add(new Artwork(
                    rs.getInt("ArtworkID"),
                    rs.getString("Title"),
                    rs.getString("Description"),
                    rs.getDate("CreationDate"),
                    rs.getString("Medium"),
                    rs.getString("ImageURL"),
                    rs.getInt("ArtistID")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return favorites;
    }  

    @Override
    public boolean addGallery(Gallery gallery) {
        String query = "INSERT INTO Gallery (Name, Description, Location, CuratorID, OpeningHours) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, gallery.getName());
            ps.setString(2, gallery.getDescription());
            ps.setString(3, gallery.getLocation());
            ps.setInt(4, gallery.getCuratorId());
            ps.setString(5, gallery.getOpeningHours());
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateGallery(Gallery gallery) {
        String query = "UPDATE Gallery SET Name=?, Description=?, Location=?, CuratorID=?, OpeningHours=? WHERE GalleryID=?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, gallery.getName());
            ps.setString(2, gallery.getDescription());
            ps.setString(3, gallery.getLocation());
            ps.setInt(4, gallery.getCuratorId());
            ps.setString(5, gallery.getOpeningHours());
            ps.setInt(6, gallery.getGalleryId());
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeGallery(int galleryId) {
        String query = "DELETE FROM Gallery WHERE GalleryID=?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, galleryId);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Gallery> searchGalleries(String keyword) {
        List<Gallery> galleries = new ArrayList<>();
        String query = "SELECT * FROM Gallery WHERE Name LIKE ? OR Description LIKE ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Gallery gallery = new Gallery(
                    rs.getInt("GalleryID"),
                    rs.getString("Name"),
                    rs.getString("Description"),
                    rs.getString("Location"),
                    rs.getInt("CuratorID"),
                    rs.getString("OpeningHours")
                );
                galleries.add(gallery);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return galleries;
    }
    
    public boolean addArtist(Artist artist) {
        String sql = "INSERT INTO Artist (Name, Biography, BirthDate, Nationality, Website, ContactInfo) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, artist.getName());
            ps.setString(2, artist.getBiography());
            ps.setDate(3, new java.sql.Date(artist.getBirthDate().getTime()));
            ps.setString(4, artist.getNationality());
            ps.setString(5, artist.getWebsite());
            ps.setString(6, artist.getContactInfo());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
