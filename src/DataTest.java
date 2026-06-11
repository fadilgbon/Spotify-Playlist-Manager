/**
 * CPSC 219 W26 Project Demo 3
 * <p>
 * DataTest Class
 * <p>
 * Contains JUnit tests for the Data class.
 * Tests adding songs, retrieving songs, calculating statistics,
 * filtering by genre, and validating input cases.
 *
 * @author Jacob Delve, Fadil Gbonjubola, Ali Jaffary, Sofia Laganas
 * @version 1.0
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class DataTest {

    private Data data;

    @BeforeEach
    void setUp() {
        data = new Data();
    }

    @Test
    void addSong() {
        Genre genre= Genre.fromString("Rock");
        assertTrue(data.addSong("Song 1", "Artist 1",genre, 100, 5));
        assertEquals(1,  data.getAllSongs().size());
    }

    @Test
    void getAllSongs() {
        Genre genre= Genre.fromString("Rock");
        data.addSong("Song 1", "Artist 1", genre, 200, 5);
        assertEquals(1, data.getAllSongs().size());
    }

    @Test
    void getTotalDuration() {
        Genre genre= Genre.fromString("Rock");
        Genre genre2= Genre.fromString("Rap");
        data.addSong("Song 1", "Artist 1",genre, 200,5);
        data.addSong("Song 2", "Artist 2", genre2, 100, 3);
        assertEquals(300, data.getTotalDuration());
    }

    @Test
    void getAverageRating() {
        Genre genre= Genre.fromString("Rock");
        Genre genre2= Genre.fromString("Rap");

        data.addSong("Song 1", "Artist 1", genre, 200, 5);
        data.addSong("Song 2", "Artist 2", genre2, 100, 3);
        assertEquals(4.0, data.getAverageRating());
    }

    @Test
    void getSongsByGenre() {
        Genre genre= Genre.fromString("Rock");
        Genre genre2= Genre.fromString("Rap");
        data.addSong("Rock Song", "Artist 1", genre, 200, 5);
        data.addSong("Rap Song", "Artist 2", genre2, 100, 5);
        assertEquals(1, data.getSongsByGenre(genre.toString()).size());
    }
    @Test
    void getTop5Rated() {
        // Add 6 songs and verify only the top 5 are returned
        Genre genre= Genre.fromString("Rock");
        Genre genre2= Genre.fromString("Rap");
        Genre genre3= Genre.fromString("Pop");
        Genre genre4= Genre.fromString("Jazz");
        Genre genre5= Genre.fromString("Country");
        Genre genre6= Genre.fromString("Blues");

        data.addSong("Song 1", "Artist 1", genre, 200, 5);
        data.addSong("Song 2", "Artist 2", genre2, 150, 3);
        data.addSong("Song 3", "Artist 3", genre3, 125, 4);
        data.addSong("Song 4", "Artist 4", genre4, 100, 5);
        data.addSong("Song 5", "Artist 5", genre5, 75, 4);
        data.addSong("Song 6", "Artist 6", genre6, 50, 1);
        ArrayList<Song> top5 = data.getTop5Rated();
        assertEquals(5, top5.size());
    }

    @Test
    void addSongInvalidRatingLow() {
        Genre genre = Genre.fromString("Rock");
        assertFalse(data.addSong("Bad Song", "Artist 1", genre, 200, 0));
        assertEquals(0, data.getAllSongs().size());
    }

    @Test
    void addSongInvalidRatingHigh() {
        Genre genre = Genre.fromString("Rock");
        assertFalse(data.addSong("Bad Song", "Artist 1", genre, 200, 6));
        assertEquals(0, data.getAllSongs().size());
    }

    @Test
    void addSongInvalidDuration() {
        Genre genre = Genre.fromString("Rock");
        assertFalse(data.addSong("Bad Song", "Artist 1", genre, -10, 4));
        assertEquals(0, data.getAllSongs().size());
    }

    @Test
    void getSongsByGenreInvalidStringUsesOther() {
        Genre other = Genre.fromString("Blues");
        data.addSong("Unknown Song", "Artist 1", other, 200, 4);
        assertEquals(1, data.getSongsByGenre(other.toString()).size());
    }

}
