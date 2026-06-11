/**
 * CPSC 219 W26 Project Demo 3
 * <p>
 * FilerHandler Test Class
 * <p>
 * Contains JUnit tests for the FileHandler class.
 * Tests adding songs, retrieving songs, calculating statistics,
 * filtering by genre, and validating input cases.
 *
 * @author Jacob Delve, Fadil Gbonjubola, Ali Jaffary, Sofia Laganas
 * @version 1.0
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileHandlerTest {
    private Data data;
    private final String testFile = "fileHandlerTest.csv";

    // Runs before each test to ensure clean data
    @BeforeEach
    void setUp() {
        data = new Data();
    }

    // Runs after each test to remove temp csv file
    @AfterEach
    void cleanUp() {
        File file = new File(testFile);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void saveAsCSV() {
        Genre genre = Genre.fromString("Rock");
        Genre genre2 = Genre.fromString("Rap");
        data.addSong("Song 1", "Artist 1", genre, 200, 5);
        data.addSong("Song 2", "Artist 2", genre2, 150, 4);

        assertTrue(FileHandler.saveAsCSV(testFile, data));

        Data loaded = FileHandler.loadFromCSV(testFile);
        assertEquals(2, loaded.getAllSongs().size());
        assertEquals("Song 1", loaded.getAllSongs().get(0).getTitle());
        assertEquals("Artist 2", loaded.getAllSongs().get(1).getArtist());
    }


    @Test
    void loadFromCSV() throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(testFile))) {
            writer.println("Title,Artist,Genre,Duration,Rating");
            writer.println("Rock Song,Artist A,Rock,210,4.0");
            writer.println("Rap Song,Artist B,Rap,150,3.5");
        }

        Data loaded = FileHandler.loadFromCSV(testFile);

        assertEquals(2, loaded.getAllSongs().size());
        assertEquals("Rock Song", loaded.getAllSongs().get(0).getTitle());
        assertEquals(Genre.RAP, loaded.getAllSongs().get(1).getGenre());
    }

    // Bad cases
    @Test
    void saveAsCSVInvalidPath() {
        boolean result = FileHandler.saveAsCSV("/path/that/does/not/exist/songs.csv", data);
        assertFalse(result);
    }

    @Test
    void loadFromCSVMissingFileReturnsEmptyData() {
        Data loaded = FileHandler.loadFromCSV("missingFile.csv");
        assertEquals(0, loaded.getAllSongs().size());
    }

    @Test
    void loadFromCSVSkipsRowsWithMissingValues() throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(testFile))) {
            writer.println("Title,Artist,Genre,Duration,Rating");
            writer.println("Bad Row,Only Two Values");
            writer.println("Good Song,Artist C,Pop,180,4.0");
        }

        Data loaded = FileHandler.loadFromCSV(testFile);
        assertEquals(1, loaded.getAllSongs().size());
        assertEquals("Good Song", loaded.getAllSongs().get(0).getTitle());
    }

    @Test
    void loadFromCSVInvalidNumericRowAndContinues() throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(testFile))) {
            writer.println("Title,Artist,Genre,Duration,Rating");
            writer.println("Song 1,Artist 1,Rock,100,4.0");
            writer.println("Song 2,Artist 2,Jazz,abc,4.5");
            writer.println("Song 3,Artist 3,Pop,120,5.0");
        }

        Data loaded = FileHandler.loadFromCSV(testFile);
        assertEquals(2, loaded.getAllSongs().size());
        assertEquals("Song 1", loaded.getAllSongs().get(0).getTitle());
        assertEquals("Song 3", loaded.getAllSongs().get(1).getTitle());
    }

}
