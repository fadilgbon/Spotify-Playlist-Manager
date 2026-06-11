/**
 * CPSC 219 W26 Project Demo 3
 * <p>
 * Menu Class
 * <p>
 * Handles menu-related functionality for the Music Playlist Generator.
 *
 * @author Jacob Delve, Fadil Gbonjubola, Ali Jaffary, Sofia Laganas
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

/**
 * Handles user interaction for the Music Playlist Generator.
 * Instantiated with a Data object and provides an interactive console menu.
 * Menu and its helper methods are instance methods to follow OO design principles.
 * Only showMenu() is the entry point; all helpers operate on the instance's data field.
 */
public class Menu {

    private final Scanner scanner;
    private Data data;

    /**
     * Constructs a Menu with the given Data object and a shared Scanner.
     *
     * @param data the Data object to operate on
     */
    public Menu(Data data) {
        this.data = data;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Allows the user to navigate between playlist features.
     * The menu runs until the user selects the exit option (9).
     */
    public void showMenu() {
        boolean active = true;

        while (active) {
            System.out.println("==============================");
            System.out.println("   Music Playlist Generator");
            System.out.println("==============================");
            System.out.println("1. Add Song");
            System.out.println("2. View All Songs");
            System.out.println("3. Total Duration");
            System.out.println("4. Average Rating");
            System.out.println("5. Top 5 Rated");
            System.out.println("6. Filter by Genre");
            System.out.println("7. Save Playlist");
            System.out.println("8. Load Playlist");
            System.out.println("9. Exit");
            System.out.println("==============================");

            System.out.print("Choose an option: ");
            int decision = scanner.nextInt();
            scanner.nextLine();
            System.out.println("==============================");

            // Determines which menu option the user has selected and runs its corresponding method
            switch (decision) {
                case 1:
                    addSongMenu();
                    break;
                case 2:
                    viewAllSongs();
                    break;
                case 3:
                    showTotalDuration();
                    break;
                case 4:
                    showAverageRating();
                    break;
                case 5:
                    showTop5Rated();
                    break;
                case 6:
                    sortByGenre();
                    break;
                case 7:
                    saveSongsCSV();
                    break;
                case 8:
                    loadSongsCSV();
                    break;
                case 9:
                    System.out.println("\n \n Exiting Playlist Program...");
                    active = false;
                    break;
                default:
                    System.out.println("\n Choice Invalid! Try again. \n \n");
            }
        }
    }

    /**
     * Prompts the user for song details and adds the song to the playlist if valid.
     */
    private void addSongMenu() {
        System.out.println(" ");

        // Prompts user for song title input
        System.out.print("Enter song title: ");
        String title = scanner.nextLine();

        // Prevents empty titles from being entered
        while (title.isEmpty()) {
            System.out.println(" ");
            System.out.println("***** Must enter a song title! *****");
            System.out.print("Enter song title: ");
            title = scanner.nextLine();
        }

        // Prompts user for artist input
        System.out.println(" ");
        System.out.print("Enter artist: ");
        String artist = scanner.nextLine();

        // Prevents empty artists from being entered
        while (artist.isEmpty()) {
            System.out.println(" ");
            System.out.println("***** Must enter an artist! *****");
            System.out.print("Enter artist: ");
            artist = scanner.nextLine();
        }

        // Prompts user for genre input
        System.out.println(" ");
        System.out.print("Enter genre: ");
        String g = scanner.nextLine();

        // Prompts user for duration input (seconds)
        System.out.println(" ");
        System.out.print("Enter duration (seconds):  ");
        int duration = scanner.nextInt();

        // Prompts user for rating input (1-5)
        System.out.println(" ");
        System.out.print("Enter rating from 1-5:  ");
        double rating = scanner.nextDouble();

        System.out.println("\n");

        scanner.nextLine();

        // Calls Data to add song if valid
        Genre genre = Genre.fromString(g);
        boolean success = data.addSong(title, artist, genre, duration, rating);

        if (success) {
            System.out.println("**********");
            System.out.println("Song has been added to the catalogue!");
            System.out.println("**********\n");
        } else {
            System.out.println("**********");
            System.out.println("Song was not added to the catalogue. :(");
            System.out.println("-> Duration must be a positive number.");
            System.out.println("-> Rating must be between " + data.MIN_RATING + " and " + data.MAX_RATING + ". (inclusive)");
            System.out.println("**********\n");
        }
    }

    /**
     * Displays all songs in the playlist, or a message if none exist.
     */
    private void viewAllSongs() {
        ArrayList<Song> songs = data.getAllSongs();

        if (songs.isEmpty()) {
            System.out.println(" ");
            System.out.println("**********");
            System.out.println("No songs have been added yet. :(");
            System.out.println("**********\n");
            return;
        }

        System.out.println();
        System.out.println("All Songs in the Catalogue");
        System.out.println("*****");

        for (Song song : songs) {
            System.out.println(song);
        }

        System.out.println();
    }

    /**
     * Displays the total duration of all songs, or a message if no songs exist.
     */
    private void showTotalDuration() {
        if (data.getAllSongs().isEmpty()) {
            System.out.println(" ");
            System.out.println("**********");
            System.out.println("No songs have been added yet. :(");
            System.out.println("**********\n");
            return;
        }

        double duration = data.getTotalDuration();
        System.out.println("\nTotal duration of all songs: " + duration + " seconds\n");
    }

    /**
     * Displays the average rating of all songs, or a message if none exist.
     */
    private void showAverageRating() {
        if (data.getAllSongs().isEmpty()) {
            System.out.println(" ");
            System.out.println("**********");
            System.out.println("No songs have been added yet. :(");
            System.out.println("**********\n");
            return;
        }

        double avg = data.getAverageRating();
        System.out.println("\nAverage rating of all songs: " + avg + "\n");
    }

    /**
     * Displays the top 5 highest-rated songs, or a message if none exist.
     */
    private void showTop5Rated() {
        ArrayList<Song> top5 = data.getTop5Rated();

        if (top5.isEmpty()) {
            System.out.println(" ");
            System.out.println("**********");
            System.out.println("No songs have been added yet. :(");
            System.out.println("**********\n");
            return;
        }

        System.out.println("\nTop 5 Rated Songs:");

        for (int i = 0; i < top5.size(); i++) {
            Song song = top5.get(i);
            System.out.println((i + 1) + ". " + song.getTitle() + " by " +
                    song.getArtist() + ", Rating: " + song.getRating());
        }
    }

    /**
     * Prompts the user for a genre and displays all songs in that genre.
     */
    private void sortByGenre() {
        System.out.println(" ");
        System.out.print("Please enter genre to sort by: ");
        String genre = scanner.nextLine().trim();
        Genre genreEnum = Genre.fromString(genre);

        ArrayList<Song> sorted = data.getSongsByGenre(genre);

        if (sorted.isEmpty()) {
            System.out.println(" ");
            System.out.println("**********");
            System.out.println("No songs found in genre: " + genre.toUpperCase());
            System.out.println("**********\n");
            return;
        }

        // Clarifies through output that all non-preset genres are categorized as OTHER
        if (genreEnum == Genre.OTHER) {
            System.out.println(" ");
            System.out.println("Songs in genre: OTHER (i.e. " + genre.toUpperCase() + ")");
            System.out.println("*****");
        } else {
            System.out.println(" ");
            System.out.println("Songs in genre: " + genre.toUpperCase());
            System.out.println("*****");
        }

        for (Song song : sorted) {
            System.out.println(song.getTitle() + " | " + song.getArtist() + " | "
                    + song.getRating() + " | " + song.getDuration());
        }

        System.out.println(" ");
    }

    /**
     * Prompts the user for a filename and saves all songs to a CSV file.
     */
    private void saveSongsCSV() {
        if (data.getAllSongs().isEmpty()) {
            System.out.println("**********");
            System.out.println("No songs to save!");
            System.out.println("**********\n");
            return;
        }
        System.out.println(" ");
        System.out.print("Enter filename to save songs to file (ex: song.csv): ");
        String filename = scanner.nextLine().trim();

        boolean valid = FileHandler.saveAsCSV(filename, data);

        if (valid) {
            System.out.println(" ");
            System.out.println("*****");
            System.out.println("Songs have been successfully saved to: " + filename);
            System.out.println("*****\n");
        } else {
            System.out.println(" ");
            System.out.println("*****");
            System.out.println("Failure to save songs.");
            System.out.println("*****\n");
        }
    }

    /**
     * Prompts the user for a filename and loads songs from a CSV file into the playlist.
     * Validates that the file exists and contains valid songs before adding them.
     */
    private void loadSongsCSV() {
        System.out.println(" ");
        System.out.print("Enter filename to load songs into program (ex:song.csv): ");
        String filename = scanner.nextLine().trim();

        File file = new File(filename);
        if (!file.exists()) {
            System.out.println(" ");
            System.out.println("*****");
            System.out.println("File cannot be found: " + filename);
            System.out.println("*****\n");
            return;
        }

        Data loadedData = FileHandler.loadFromCSV(filename);
        ArrayList<Song> loadedSongs = loadedData.getAllSongs();

        if (loadedSongs.isEmpty()) {
            System.out.println(" ");
            System.out.println("*****");
            System.out.println("No valid songs found in file: " + filename);
            System.out.println("*****\n");
            return;
        }

        for (Song song : loadedSongs) {
            data.addSong(song.getTitle(), song.getArtist(), song.getGenre(), song.getDuration(), song.getRating());
        }

        System.out.println(" ");
        System.out.println("*****");
        System.out.println("Songs have been successfully loaded from file: " + filename);
        System.out.println("*****\n");
    }
}
