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

    // ==== Artwork ====

    @Override
    public boolean addArtwork(Artwork artwork) {
        String sql = HexaConstants.INSERT_ARTWORK;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, artwork.getTitle());
            ps.setString(2, artwork.getDescription());
            ps.setDate(3, new java.sql.Date(artwork.getCreationDate().getTime()));
            ps.setString(4, artwork.getMedium());
            ps.setString(5, artwork.getImageUrl());
            ps.setInt(6, artwork.getArtist().getArtistId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateArtwork(Artwork artwork) {
        String sql = HexaConstants.UPDATE_ARTWORK;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, artwork.getTitle());
            ps.setString(2, artwork.getDescription());
            ps.setDate(3, new java.sql.Date(artwork.getCreationDate().getTime()));
            ps.setString(4, artwork.getMedium());
            ps.setString(5, artwork.getImageUrl());
            ps.setInt(6, artwork.getArtist().getArtistId());
            ps.setInt(7, artwork.getArtworkId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean removeArtwork(int artworkId) {
        try (PreparedStatement ps = connection.prepareStatement(HexaConstants.DELETE_ARTWORK)) {
            ps.setInt(1, artworkId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Artwork getArtworkById(int artworkId) throws ArtWorkNotFoundException {
        try (PreparedStatement ps = connection.prepareStatement(HexaConstants.GET_ARTWORK_BY_ID)) {
            ps.setInt(1, artworkId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Artist artist = extractArtist(rs);
                return new Artwork(
                    rs.getInt("ArtworkID"),
                    rs.getString("Title"),
                    rs.getString("Description"),
                    rs.getDate("CreationDate"),
                    rs.getString("Medium"),
                    rs.getString("ImageURL"),
                    artist
                );
            } else {
                throw new ArtWorkNotFoundException("No artwork found with ID: " + artworkId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ArtWorkNotFoundException("Database error while fetching artwork with ID: " + artworkId);
        }
    }

    @Override
    public List<Artwork> searchArtworks(String keyword) {
        List<Artwork> artworks = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(HexaConstants.SEARCH_ARTWORKS)) {
            String like = "%" + keyword + "%";
            ps.setString(1, like);
            ps.setString(2, like);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Artist artist = extractArtist(rs);
                Artwork artwork = new Artwork(
                    rs.getInt("ArtworkID"),
                    rs.getString("Title"),
                    rs.getString("Description"),
                    rs.getDate("CreationDate"),
                    rs.getString("Medium"),
                    rs.getString("ImageURL"),
                    artist
                );
                artworks.add(artwork);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artworks;
    }

    @Override
    public boolean addArtworkToFavorite(int userId, int artworkId) {
        try (PreparedStatement ps = connection.prepareStatement(HexaConstants.INSERT_FAVORITE_ARTWORK)) {
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
        try (PreparedStatement ps = connection.prepareStatement(HexaConstants.DELETE_FAVORITE_ARTWORK)) {
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
        try (PreparedStatement ps = connection.prepareStatement(HexaConstants.GET_USER_FAVORITE_ARTWORKS)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Artist artist = extractArtist(rs);
                Artwork artwork = new Artwork(
                    rs.getInt("ArtworkID"),
                    rs.getString("Title"),
                    rs.getString("Description"),
                    rs.getDate("CreationDate"),
                    rs.getString("Medium"),
                    rs.getString("ImageURL"),
                    artist
                );
                favorites.add(artwork);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserNotFoundException("Database error while fetching favorites for user ID: " + userId);
        }

        if (favorites.isEmpty()) {
            throw new UserNotFoundException("No favorite artworks found for user ID: " + userId);
        }

        return favorites;
    }

    // ==== Gallery ====

    @Override
    public boolean addGallery(Gallery gallery) {
        try (PreparedStatement ps = connection.prepareStatement(HexaConstants.INSERT_GALLERY)) {
            ps.setString(1, gallery.getName());
            ps.setString(2, gallery.getDescription());
            ps.setString(3, gallery.getLocation());
            ps.setInt(4, gallery.getCurator().getArtistId());
            ps.setString(5, gallery.getOpeningHours());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateGallery(Gallery gallery) {
        try (PreparedStatement ps = connection.prepareStatement(HexaConstants.UPDATE_GALLERY)) {
            ps.setString(1, gallery.getName());
            ps.setString(2, gallery.getDescription());
            ps.setString(3, gallery.getLocation());
            ps.setInt(4, gallery.getCurator().getArtistId());
            ps.setString(5, gallery.getOpeningHours());
            ps.setInt(6, gallery.getGalleryId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean removeGallery(int galleryId) {
        try (PreparedStatement ps = connection.prepareStatement(HexaConstants.DELETE_GALLERY)) {
            ps.setInt(1, galleryId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Gallery> searchGalleries(String keyword) {
        List<Gallery> galleries = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(HexaConstants.SEARCH_GALLERIES)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Artist curator = extractArtist(rs);
                Gallery gallery = new Gallery(
                    rs.getInt("GalleryID"),
                    rs.getString("Name"),
                    rs.getString("Description"),
                    rs.getString("Location"),
                    curator,
                    rs.getString("OpeningHours")
                );
                galleries.add(gallery);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return galleries;
    }

    // ==== Artist ====

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

    // ==== Utility Method ====

    private Artist extractArtist(ResultSet rs) throws SQLException {
        return new Artist(
            rs.getInt("ArtistID"),
            rs.getString("Name"),
            rs.getString("Biography"),
            rs.getDate("BirthDate"),
            rs.getString("Nationality"),
            rs.getString("Website"),
            rs.getString("ContactInfo")
        );
    }
}
