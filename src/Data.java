/**
 * CPSC 219 W26 Project Demo 3
 * <p>
 * Data Class
 * <p>
 * Stores and manages songs for the Music Playlist Generator.
 * Handles adding songs, calculating statistics, filtering by genre,
 * and retrieving top-rated songs.
 *
 * @author Jacob Delve, Fadil Gbonjubola, Ali Jaffary, Sofia Laganas
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.Collections;

/**
 * Stores and manages songs in the playlist.
 */
public class Data {

    public static final int MIN_RATING = 1;
    public static final int MAX_RATING = 5;

    private ArrayList<Song> songs;

    /**
     * Creates an empty list to store data.
     */
    public Data() {
        songs = new ArrayList<>();
    }

    /**
     * Adds a song when the rating is valid.
     *
     * @param title    song title
     * @param artist   song artist
     * @param genre    song genre
     * @param duration song length in seconds
     * @param rating   rating from MIN_RATING to MAX_RATING
     * @return true if added, false if invalid
     */
    public boolean addSong(String title, String artist, Genre genre, int duration, double rating) {
        if (rating < MIN_RATING || rating > MAX_RATING || duration < 0) {
            return false;
        }

        Song song = new Song(title, artist, genre, duration, rating);
        songs.add(song);

        return true;
    }

    /**
     * Returns a copy of the current song list.
     *
     * @return list of stored songs
     */
    public ArrayList<Song> getAllSongs() {
        return new ArrayList<>(songs);
    }

    /**
     * Sums the duration of all stored songs.
     *
     * @return total duration in seconds
     */
    public int getTotalDuration() {
        int total = 0;

        for (Song song : songs) {
            total += song.getDuration();
        }

        return total;
    }

    /**
     * Computes the average rating of stored songs.
     *
     * @return average rating, or 0 if none
     */
    public double getAverageRating() {
        if (songs.isEmpty()) {
            return 0;
        }

        double total = 0;

        for (Song song : songs) {
            total += song.getRating();
        }

        return total/ songs.size();
    }

    /**
     * Filters songs by a genre string.
     *
     * @param genre genre name to match
     * @return list of songs with that genre
     */
    public ArrayList<Song> getSongsByGenre(String genre) {
        ArrayList<Song> result = new ArrayList<>();
        Genre g = Genre.fromString(genre);

        for (Song song : songs) {
            if (song.getGenre() == g) {
                result.add(song);
            }
        }

        return result;
    }

    /**
     * Returns up to 5 highest-rated songs.
     *
     * @return list of top-rated songs
     */
    public ArrayList<Song> getTop5Rated() {
        ArrayList<Song> clone = new ArrayList<>(songs);
        Collections.sort(clone);
        ArrayList<Song> top5 = new ArrayList<>();

        for(int i = 0; i < clone.size() && i <5; i++) {
            top5.add(clone.get(i));
        }

        return top5;
    }
}