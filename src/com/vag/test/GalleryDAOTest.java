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

    @BeforeAll
    public static void setUp() {
        service = new VirtualArtGalleryDAOImpl();

        
        Artist artist = new Artist(0, "Test Curator", "Sample curator", Date.valueOf("1980-01-01"), "CuratorLand", "http://curator.com", "curator@test.com");
        service.addArtist(artist);
    }

    @Test
    public void testAddGallery() {
        Gallery gallery = new Gallery(0, "Modern Art Space", "A test gallery", "New York", 1, "10AM - 6PM");
        boolean result = service.addGallery(gallery);
        assertTrue(result);
    }

    @Test
    public void testUpdateGallery() {
        Gallery original = new Gallery(0, "Old Gallery", "Old Desc", "Paris", 1, "9AM - 5PM");
        service.addGallery(original);

        List<Gallery> found = service.searchGalleries("Old Gallery");
        assertFalse(found.isEmpty());
        int galleryId = found.get(0).getGalleryId();

        Gallery updated = new Gallery(galleryId, "Updated Gallery", "New Desc", "Paris", 1, "11AM - 7PM");
        boolean result = service.updateGallery(updated);
        assertTrue(result);
    }

    @Test
    public void testRemoveGallery() {
        Gallery gallery = new Gallery(0, "Temp Gallery", "To be removed", "Berlin", 1, "10AM - 4PM");
        service.addGallery(gallery);

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
