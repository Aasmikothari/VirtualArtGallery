package com.vag.test;

import static org.junit.jupiter.api.Assertions.*;

import com.vag.dao.VirtualArtGalleryDAOImpl;
import com.vag.entity.Artist;
import com.vag.entity.Artwork;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.List;

public class ArtworkDAOTest {

    static VirtualArtGalleryDAOImpl service;
    static Artist testArtist;

    @BeforeAll
    public static void setUp() {
        service = new VirtualArtGalleryDAOImpl();

        // Add a test artist once
        testArtist = new Artist(1, "Test Artist", "Sample bio", Date.valueOf("1970-01-01"), "Testland", "http://testartist.com", "test@test.com");
        service.addArtist(testArtist);
    }

    @Test
    public void testAddArtwork() {
        Artwork artwork = new Artwork(0, "Test Title", "Test Desc", new Date(System.currentTimeMillis()), "Oil", "url.jpg", testArtist);
        boolean result = service.addArtwork(artwork);
        assertTrue(result);
    }

    @Test
    public void testUpdateArtwork() {
        Date creationDate = Date.valueOf("1503-10-01");
        Artwork original = new Artwork(0, "Mona Lisa", "Original description", creationDate, "Oil", "https://original.img", testArtist);
        service.addArtwork(original);

        List<Artwork> found = service.searchArtworks("Mona Lisa");
        assertFalse(found.isEmpty());
        int idToUpdate = found.get(0).getArtworkId();

        Artwork updatedArtwork = new Artwork(idToUpdate, "Mona Lisa (Updated)", "Updated description", creationDate, "Oil on canvas", "https://updatedmonalisa.img", testArtist);
        boolean result = service.updateArtwork(updatedArtwork);
        assertTrue(result);
    }

    @Test
    public void testRemoveArtwork() {
        Date creationDate = Date.valueOf("1503-10-01");
        Artwork artwork = new Artwork(0, "ToDelete", "To be deleted", creationDate, "Ink", "https://temp.img", testArtist);
        service.addArtwork(artwork);

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
}
