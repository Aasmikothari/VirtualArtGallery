package com.vag.test;

import static org.junit.jupiter.api.Assertions.*;

import com.vag.dao.VirtualArtGalleryDAOImpl;
import com.vag.entity.Gallery;
import com.vag.entity.Artist;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.List;

public class GalleryDAOTest {

    static VirtualArtGalleryDAOImpl service;
    static Artist testArtist;

    @BeforeAll
    public static void setUp() {
        service = new VirtualArtGalleryDAOImpl();

        // Add a test artist once (this artist will be used as the curator)
        testArtist = new Artist(1, "Test Curator", "Sample curator", Date.valueOf("1980-01-01"), "CuratorLand", "http://curator.com", "curator@test.com");
        service.addArtist(testArtist);
    }

    @Test
    public void testAddGallery() {
        // Create a Gallery with the Artist as curator instead of artistId
        Gallery gallery = new Gallery(0, "Modern Art Space", "A test gallery", "New York", testArtist, "10AM - 6PM");
        boolean result = service.addGallery(gallery);
        assertTrue(result);
    }

    @Test
    public void testUpdateGallery() {
        // Create an initial Gallery with the Artist as curator
        Gallery original = new Gallery(0, "Old Gallery", "Old Desc", "Paris", testArtist, "9AM - 5PM");
        service.addGallery(original);

        // Search for the gallery to get its ID
        List<Gallery> found = service.searchGalleries("Old Gallery");
        assertFalse(found.isEmpty());
        int galleryId = found.get(0).getGalleryId();

        // Update the gallery and set the new information
        Gallery updated = new Gallery(galleryId, "Updated Gallery", "New Desc", "Paris", testArtist, "11AM - 7PM");
        boolean result = service.updateGallery(updated);
        assertTrue(result);
    }

    @Test
    public void testRemoveGallery() {
        // Create a gallery to remove
        Gallery gallery = new Gallery(0, "Temp Gallery", "To be removed", "Berlin", testArtist, "10AM - 4PM");
        service.addGallery(gallery);

        // Find the gallery and get its ID
        List<Gallery> found = service.searchGalleries("Temp Gallery");
        assertFalse(found.isEmpty());
        int idToRemove = found.get(0).getGalleryId();

        // Remove the gallery
        boolean result = service.removeGallery(idToRemove);
        assertTrue(result);
    }

    @Test
    public void testSearchGalleries() {
        // Search galleries with a keyword
        List<Gallery> galleries = service.searchGalleries("Modern");
        assertNotNull(galleries);
        assertTrue(galleries.size() >= 0);
    }
}
