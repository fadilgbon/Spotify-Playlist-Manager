/**
 * CPSC 219 W26 Project Demo 3
 * <p>
 * Song Class
 * <p>
 * Represents a song in the Music Playlist Generator.
 * Stores song details and supports comparison for sorting
 * songs by rating in descending order.
 *
 * @author Jacob Delve, Fadil Gbonjubola, Ali Jaffary, Sofia Laganas
 * @version 1.0
 */

import java.util.Objects;

/**
 * Represents a song with a title, artist, genre, duration, and rating.
 * Extends MediaItem for shared media fields and implements Comparable
 * to allow sorting by rating in descending order.
 */
public class Song extends MediaItem implements Comparable<Song> {

    private Genre genre;
    private double rating;

    /**
     * Constructs a new Song with the given fields.
     *
     * @param title    song title
     * @param artist   song artist
     * @param genre    song genre
     * @param duration song length in seconds
     * @param rating   rating from MIN_RATING to MAX_RATING
     */
    public Song(String title, String artist, Genre genre, int duration, double rating) {
        super(title, artist, duration);
        this.genre = genre;
        this.rating = rating;
    }

    /**
     * Returns the genre of the song.
     *
     * @return song genre
     */
    public Genre getGenre() {
        return genre;
    }

    /**
     * Returns the rating of the song.
     *
     * @return song rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * Returns a formatted display string for this song.
     * Satisfies the abstract method from MediaItem.
     *
     * @return formatted string representation
     */
    @Override
    public String getDisplayInfo() {
        return title + " by " + artist + " | " + genre + " | " + rating + " / 5 | " + duration + " second(s)";
    }

    /**
     * Checks equality based on title and artist (case-insensitive).
     * Two songs are considered equal if they share the same title and artist.
     *
     * @param obj the object to compare against
     * @return true if both songs have the same title and artist
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Song)) return false;
        Song other = (Song) obj;
        return title.equalsIgnoreCase(other.title) && artist.equalsIgnoreCase(other.artist);
    }

    /**
     * Returns a hash code consistent with equals().
     * Songs equal by title and artist will produce the same hash code.
     *
     * @return hash code based on lowercased title and artist
     */
    @Override
    public int hashCode() {
        return Objects.hash(title.toLowerCase(), artist.toLowerCase());
    }

    /**
     * Compares this song to another by rating in descending order.
     * Higher-rated songs sort first.
     *
     * @param other the song to compare against
     * @return negative if this song has a higher rating, positive if lower, 0 if equal
     */
    @Override
    public int compareTo(Song other) {
        return Double.compare(other.rating, this.rating); // highest first
    }

}
