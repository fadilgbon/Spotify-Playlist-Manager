/**
 * CPSC 219 W26 Project Demo 3
 * <p>
 * Genre Enum
 * <p>
 * Represents the different genres used in the Music Playlist Generator.
 * Includes a helper method to convert strings into Genre values safely.
 *
 * @author Jacob Delve, Fadil Gbonjubola, Ali Jaffary, Sofia Laganas
 * @version 1.0
 */

/**
 * Represents the genre of song
 * Possible values: POP, ROCK, RAP, JAZZ, COUNTRY, OTHER
 */
public enum Genre {

    POP,

    ROCK,

    RAP,

    JAZZ,

    COUNTRY,

    OTHER;

    /**
     * Converts a String to a Genre safely. Case-insensitive.
     * Returns OTHER if the input does not match any specified genre.
     *
     * @param input the genre as a string.
     * @return the matching Genre, or OTHER if no match can be found.
     */
    public static Genre fromString(String input) {
        try {
            return Genre.valueOf(input.toUpperCase());
        } catch (Exception e) {
            return OTHER;
        }
    }

}
