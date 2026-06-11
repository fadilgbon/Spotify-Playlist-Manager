/**
 * CPSC 219 W26 Project Demo 3
 * <p>
 * GenreTest Class
 * <p>
 * Contains JUnit tests for the Genre enum in the Music Playlist Generator.
 * Tests the fromString() method for safe conversion from strings to Genre values,
 * including case-insensitivity and invalid input handling.
 *
 * @author Jacob Delve, Fadil Gbonjubola, Ali Jaffary, Sofia Laganas
 * @version 1.0
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * JUnit tests for the Genre enum
 */
public class GenreTest {

    @Test
    void fromString_Pop_returnsPopEnum() {
        assertEquals(Genre.POP, Genre.fromString("POP"));
    }

    @Test
    void fromString_Rock_returnsRockEnum() {
        assertEquals(Genre.ROCK, Genre.fromString("ROCK"));
    }

    @Test
    void fromString_Rap_returnsRapEnum() {
        assertEquals(Genre.RAP, Genre.fromString("RAP"));
    }

    @Test
    void fromString_Jazz_returnsJazzEnum() {
        assertEquals(Genre.JAZZ, Genre.fromString("JAZZ"));
    }

    @Test
    void fromString_Country_returnsCountryEnum() {
        assertEquals(Genre.COUNTRY, Genre.fromString("COUNTRY"));
    }

    @Test
    void fromString_Blues_returnsOtherEnum() {
        assertEquals(Genre.OTHER, Genre.fromString("BLUES"));
    }

}
