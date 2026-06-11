/**
 * CPSC 219 W26 Project Demo 3
 * <p>
 * SongTest Class
 * <p>
 * Contains JUnit tests for the Song class in the Music Playlist Generator.
 *
 * @author Jacob Delve, Fadil Gbonjubola, Ali Jaffary, Sofia Laganas
 * @version 1.0
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit tests for the Song class, covers: getters, equals, hashCode,
 * compareTo, toString, and getDisplayInfo.
 */
public class SongTest {

    private Song song1;
    private Song song2;
    private Song song3;

    /** Creates reusable Song objects before each test. */
    @BeforeEach
    void setUp() {
        song1 = new Song("Bohemian Rhapsody", "Queen", Genre.ROCK, 354, 5.0);
        song2 = new Song("Bohemian Rhapsody", "Queen", Genre.ROCK, 354, 5.0); // same as song1
        song3 = new Song("Blinding Lights", "The Weeknd", Genre.POP, 200, 4.0);
    }

    @Test
    void getTitle_returnsCorrect() {
        assertEquals("Bohemian Rhapsody", song1.getTitle());
    }

    @Test
    void getArtist_returnsCorrect() {
        assertEquals("Queen", song1.getArtist());
    }

    @Test
    void getGenre_returnsCorrect() {
        assertEquals(Genre.ROCK, song1.getGenre());
    }

    @Test
    void getDuration_returnsCorrect() {
        assertEquals(354, song1.getDuration());
    }

    @Test
    void getRating_returnsCorrect() {
        assertEquals(5.0, song1.getRating());
    }

    @Test
    void equals_sameTitleAndArtistTrue() {
        assertEquals(song1, song2);
    }

    @Test
    void equals_differentTitleOrArtistFalse() {
        assertNotEquals(song1, song3);
    }

    @Test
    void equals_caseInsensitiveTitleAndArtist() {
        Song lower = new Song("bohemian rhapsody", "queen", Genre.ROCK, 200, 3.0);
        assertEquals(song1, lower);
    }

    @Test
    void hashCode_equalSongsHaveSame() {
        // Equal objects must produce the same hash code
        assertEquals(song1.hashCode(), song2.hashCode());
    }

    @Test
    void hashCode_caseInsensitiveConsistency() {
        Song lower = new Song("bohemian rhapsody", "queen", Genre.ROCK, 100, 2.0);
        assertEquals(song1.hashCode(), lower.hashCode());
    }

    @Test
    void compareTo_higherRatedSongComesFirst() {
        // song1 (5.0) should sort before song3 (4.0)
        assertTrue(song1.compareTo(song3) < 0);
    }

    @Test
    void compareTo_lowerRatedSongComesLast() {
        // song3 (4.0) should sort after song1 (5.0)
        assertTrue(song3.compareTo(song1) > 0);
    }

    @Test
    void compareTo_equalRatingReturnsZero() {
        Song equalRating = new Song("Different", "Artist", Genre.JAZZ, 100, 5.0);
        assertEquals(0, song1.compareTo(equalRating));
    }

    @Test
    void getDisplayInfo_containsTitleArtistGenreRatingDuration() {
        String info = song1.getDisplayInfo();
        assertTrue(info.contains("Bohemian Rhapsody"));
        assertTrue(info.contains("Queen"));
        assertTrue(info.contains("ROCK"));
        assertTrue(info.contains("5.0"));
        assertTrue(info.contains("354"));
    }

    @Test
    void toString_matchesGetDisplayInfo() {
        assertEquals(song1.getDisplayInfo(), song1.toString());
    }

}


