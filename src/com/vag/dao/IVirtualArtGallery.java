package com.vag.dao;

import java.util.List;
import com.vag.entity.*;
import com.vag.exception.*;

public interface IVirtualArtGallery {

    // === Artwork Management ===

    boolean addArtwork(Artwork artwork);
    boolean updateArtwork(Artwork artwork);
    boolean removeArtwork(int artworkId);
    Artwork getArtworkById(int artworkId)throws ArtWorkNotFoundException;
    List<Artwork> searchArtworks(String keyword);

    // === User Favorites ===

    boolean addArtworkToFavorite(int userId, int artworkId);
    boolean removeArtworkFromFavorite(int userId, int artworkId);
    List<Artwork> getUserFavoriteArtworks(int userId)throws UserNotFoundException;

    // === Gallery Management ===
    
    boolean addGallery(Gallery gallery);
    boolean updateGallery(Gallery gallery);
    boolean removeGallery(int galleryId);
    List<Gallery> searchGalleries(String keyword);

}
