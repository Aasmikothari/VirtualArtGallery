package com.vag.test;

import static org.junit.jupiter.api.Assertions.*;

import com.vag.dao.*;
import com.vag.entity.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.List;

public class VirtualArtGalleryDAOImplTest {

    static VirtualArtGalleryDAOImpl service;

    @BeforeAll
    public static void setUp() {
        service = new VirtualArtGalleryDAOImpl();

        // Insert test artist once before tests run
        Artist artist = new Artist(0, "Test Artist", "Sample bio", Date.valueOf("1970-01-01"), "Testland", "http://testartist.com", "test@test.com");
        service.addArtist(artist);
    }

    @Test
    public void testAddArtwork() {
        Artwork artwork = new Artwork(0, "Test Title", "Test Desc", new Date(System.currentTimeMillis()), "Oil", "url.jpg", 1);
        boolean result = service.addArtwork(artwork);
        assertTrue(result);
    }

    @Test
    public void testUpdateArtwork() {
        Date creationDate = Date.valueOf("1503-10-01");
        // Add original artwork
        Artwork original = new Artwork(0, "Mona Lisa", "Original description", creationDate, "Oil", "https://original.img", 1);
        service.addArtwork(original);

        // Get the auto-generated ID
        List<Artwork> found = service.searchArtworks("Mona Lisa");
        assertFalse(found.isEmpty());
        int idToUpdate = found.get(0).getArtworkId();

        // Update it
        Artwork updatedArtwork = new Artwork(idToUpdate, "Mona Lisa (Updated)", "Updated description", creationDate, "Oil on canvas", "https://updatedmonalisa.img", 1);
        boolean result = service.updateArtwork(updatedArtwork);
        assertTrue(result);
    }

    @Test
    public void testRemoveArtwork() {
        Date creationDate = Date.valueOf("1503-10-01");
        Artwork artwork = new Artwork(0, "ToDelete", "To be deleted", creationDate, "Ink", "https://temp.img", 1);
        service.addArtwork(artwork);

        // Get the inserted artwork ID
        List<Artwork> found = service.searchArtworks("ToDelete");
        assertFalse(found.isEmpty());
        int idToDelete = found.get(0).getArtworkId();

        boolean result = service.removeArtwork(idToDelete);
        assertTrue(result);
    }

    @Test
    public void testSearchArtworks() {
        List<Artwork> artworks = service.searchArtworks("Test");
        assertNotNull(artworks);
        assertTrue(artworks.size() >= 0);
    }
    
    @Test
    public void testAddGallery() {
        Gallery gallery = new Gallery(0, "Modern Art Space", "A test gallery", "New York", 1, "10AM - 6PM");
        boolean result = service.addGallery(gallery);
        assertTrue(result);
    }

    @Test
    public void testUpdateGallery() {
        // Add gallery first
        Gallery original = new Gallery(0, "Old Gallery", "Old Desc", "Paris", 1, "9AM - 5PM");
        service.addGallery(original);

        // Search to get generated ID
        List<Gallery> found = service.searchGalleries("Old Gallery");
        assertFalse(found.isEmpty());
        int galleryId = found.get(0).getGalleryId();

        // Update details
        Gallery updated = new Gallery(galleryId, "Updated Gallery", "New Desc", "Paris", 1, "11AM - 7PM");
        boolean result = service.updateGallery(updated);
        assertTrue(result);
    }

    @Test
    public void testRemoveGallery() {
        // Add gallery first
        Gallery gallery = new Gallery(0, "Temp Gallery", "To be removed", "Berlin", 1, "10AM - 4PM");
        service.addGallery(gallery);

        // Search to get ID
        List<Gallery> found = service.searchGalleries("Temp Gallery");
        assertFalse(found.isEmpty());
        int idToRemove = found.get(0).getGalleryId();

        boolean result = service.removeGallery(idToRemove);
        assertTrue(result);
    }

    @Test
    public void testSearchGalleries() {
        List<Gallery> galleries = service.searchGalleries("Modern");
        assertNotNull(galleries);
        assertTrue(galleries.size() >= 0);
    }

}
