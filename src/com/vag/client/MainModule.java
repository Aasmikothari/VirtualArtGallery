package com.vag.client;

import com.vag.dao.*;
import com.vag.entity.*;
import com.vag.exception.*;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class MainModule {
    public static void main(String[] args) {
        VirtualArtGalleryDAOImpl service = new VirtualArtGalleryDAOImpl();
        Scanner sc = new Scanner(System.in);
        int choice;

        // Add a sample artist for gallery curator and artwork artist
        Artist testArtist = new Artist(1, "Test Artist", "Sample biography", Date.valueOf("1980-01-01"), "Country", "http://artist.com", "artist@test.com");
        service.addArtist(testArtist);

        do {
            System.out.println("\n===== VIRTUAL ART GALLERY MENU =====");
            System.out.println("1. Add Artwork");
            System.out.println("2. Update Artwork");
            System.out.println("3. Remove Artwork");
            System.out.println("4. Search Artwork");
            System.out.println("5. Get Artwork by ID");
            System.out.println("6. Add Gallery");
            System.out.println("7. Update Gallery");
            System.out.println("8. Remove Gallery");
            System.out.println("9. Search Gallery");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Description: ");
                    String description = sc.nextLine();
                    System.out.print("Creation Date (yyyy-mm-dd): ");
                    String dateStr = sc.nextLine();
                    Date creationDate = Date.valueOf(dateStr);
                    System.out.print("Medium: ");
                    String medium = sc.nextLine();
                    System.out.print("Image URL: ");
                    String imageUrl = sc.nextLine();
                    System.out.print("Artist ID (for artwork artist): ");
                    int artistId = Integer.parseInt(sc.nextLine());

                    // Retrieve the artist object by ID
                    Artist artworkArtist = new Artist(artistId, "Test Artist", "Sample biography", Date.valueOf("1980-01-01"), "Country", "http://artist.com", "artist@test.com");

                    Artwork newArtwork = new Artwork(0, title, description, creationDate, medium, imageUrl, artworkArtist);
                    if (service.addArtwork(newArtwork)) {
                        System.out.println("Artwork added successfully!");
                    } else {
                        System.out.println("Failed to add artwork.");
                    }
                    break;

                case 2:
                    System.out.print("Artwork ID to update: ");
                    int upId = Integer.parseInt(sc.nextLine());
                    System.out.print("New Title: ");
                    String upTitle = sc.nextLine();
                    System.out.print("New Description: ");
                    String upDesc = sc.nextLine();
                    System.out.print("New Creation Date (yyyy-mm-dd): ");
                    Date upDate = Date.valueOf(sc.nextLine());
                    System.out.print("New Medium: ");
                    String upMedium = sc.nextLine();
                    System.out.print("New Image URL: ");
                    String upImg = sc.nextLine();
                    System.out.print("New Artist ID (for artwork artist): ");
                    int upArtistId = Integer.parseInt(sc.nextLine());

                    // Retrieve the artist object by ID
                    Artist upArtworkArtist = new Artist(upArtistId, "Test Artist", "Sample biography", Date.valueOf("1980-01-01"), "Country", "http://artist.com", "artist@test.com");

                    Artwork upArtwork = new Artwork(upId, upTitle, upDesc, upDate, upMedium, upImg, upArtworkArtist);
                    if (service.updateArtwork(upArtwork)) {
                        System.out.println("Artwork updated.");
                    } else {
                        System.out.println("Failed to update artwork.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Artwork ID to remove: ");
                    int remId = Integer.parseInt(sc.nextLine());
                    if (service.removeArtwork(remId)) {
                        System.out.println("Artwork removed.");
                    } else {
                        System.out.println("Invalid ID or could not remove artwork.");
                    }
                    break;

                case 4:
                    System.out.print("Enter keyword to search: ");
                    String keyword = sc.nextLine();
                    List<Artwork> results = service.searchArtworks(keyword);
                    if (results.isEmpty()) {
                        System.out.println("No artworks found.");
                    } else {
                        results.forEach(System.out::println);
                    }
                    break;

                case 5:
                    System.out.print("Enter Artwork ID to retrieve: ");
                    int aId = Integer.parseInt(sc.nextLine());
                    try {
                        Artwork art = service.getArtworkById(aId);
                        System.out.println(art);
                    } catch (ArtWorkNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 6:
                    System.out.print("Gallery Name: ");
                    String gName = sc.nextLine();
                    System.out.print("Description: ");
                    String gDesc = sc.nextLine();
                    System.out.print("Location: ");
                    String gLoc = sc.nextLine();
                    System.out.print("Curator ID (for gallery curator): ");
                    int curatorId = Integer.parseInt(sc.nextLine());

                    // Retrieve the artist object by ID for curator
                    Artist curator = new Artist(curatorId, "Test Curator", "Curator biography", Date.valueOf("1970-01-01"), "CuratorLand", "http://curator.com", "curator@test.com");

                    System.out.print("Opening Hours: ");
                    String hours = sc.nextLine();

                    Gallery newGallery = new Gallery(0, gName, gDesc, gLoc, curator, hours);
                    if (service.addGallery(newGallery)) {
                        System.out.println("Gallery added.");
                    } else {
                        System.out.println("Failed to add gallery.");
                    }
                    break;

                case 7:
                    System.out.print("Gallery ID to update: ");
                    int gId = Integer.parseInt(sc.nextLine());
                    System.out.print("New Name: ");
                    String upGName = sc.nextLine();
                    System.out.print("New Description: ");
                    String upGDesc = sc.nextLine();
                    System.out.print("New Location: ");
                    String upGLoc = sc.nextLine();
                    System.out.print("New Curator ID (for gallery curator): ");
                    int upCurator = Integer.parseInt(sc.nextLine());

                    // Retrieve the artist object by ID for curator
                    Artist upCuratorArtist = new Artist(upCurator, "Test Curator", "Curator biography", Date.valueOf("1970-01-01"), "CuratorLand", "http://curator.com", "curator@test.com");

                    System.out.print("New Opening Hours: ");
                    String upHours = sc.nextLine();

                    Gallery upGallery = new Gallery(gId, upGName, upGDesc, upGLoc, upCuratorArtist, upHours);
                    if (service.updateGallery(upGallery)) {
                        System.out.println("Gallery updated.");
                    } else {
                        System.out.println("Failed to update gallery.");
                    }
                    break;

                case 8:
                    System.out.print("Enter Gallery ID to remove: ");
                    int removeGId = Integer.parseInt(sc.nextLine());
                    if (service.removeGallery(removeGId)) {
                        System.out.println("Gallery removed.");
                    } else {
                        System.out.println("Failed to remove gallery.");
                    }
                    break;

                case 9:
                    System.out.print("Enter keyword to search galleries: ");
                    String gKeyword = sc.nextLine();
                    List<Gallery> gResults = service.searchGalleries(gKeyword);
                    if (gResults.isEmpty()) {
                        System.out.println("No galleries found.");
                    } else {
                        gResults.forEach(System.out::println);
                    }
                    break;

                case 0:
                    System.out.println("Exiting... Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 0);

        sc.close();
    }
}