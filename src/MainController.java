/**
 * CPSC 219 W26 Project Demo 3
 * <p>
 * MainController Class
 * <p>
 * Controls the user interface for the Music Playlist Generator.
 * Handles user input, button actions, and updates the display
 * by interacting with the Data and FileHandler classes.
 *
 * @author Jacob Delve, Fadil Gbonjubola, Ali Jaffary, Sofia Laganas
 * @version 1.0
 */

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.File;
import java.util.ArrayList;

import static java.awt.SystemColor.text;

public class MainController {

    private Data data = new Data();

    @FXML private TextField titleField;
    @FXML private TextField artistField;
    @FXML private TextField genreField;
    @FXML private TextField durationField;
    @FXML private TextField ratingField;
    @FXML private TextField fileNameField;
    @FXML private TextArea outputArea;

    /**
     * Handles adding a new song from user input.
     */
    @FXML
    public void handleAddSong() {
        // Removes leading/trailing spaces from user input
        String title = titleField.getText().trim();
        String artist = artistField.getText().trim();
        String genreStr = genreField.getText().trim();
        String durationStr = durationField.getText().trim();
        String ratingStr = ratingField.getText().trim();

        // Collects all the error messages to display at once
        StringBuilder error = new StringBuilder();

        // Checks for empty fields
        if (title.isEmpty()) {
            error.append("Please enter a title!\n");
        }
        if (artist.isEmpty()) {
            error.append("Please enter an artist!\n");
        }
        if (durationStr.isEmpty()) {
            error.append("Please enter duration!\n");
        }
        if (ratingStr.isEmpty()) {
            error.append("Please enter rating!\n");
        }

        int duration = 0;
        double rating = 0;

        // Checks number format only if no previous errors
        if (error.length() == 0) {
            // Checks duration is an integer
            try {
                duration = Integer.parseInt(durationStr);
            } catch (NumberFormatException e) {
                error.append("Duration must be a positive integer!\n");
            }

            // Checks rating is a number
            try {
                rating = Double.parseDouble(ratingStr);
            } catch (NumberFormatException e) {
                error.append("Rating must be a number!\n");
            }
        }

        // Stop method if there are errors
        if (error.length() > 0) {
            outputArea.setText(error.toString());
            return;
        }

        // Converts input to a valid genre
        Genre genre = Genre.fromString(genreStr.isEmpty() ? "OTHER" : genreStr);

        // Attempts to add song to data
        boolean success = data.addSong(title, artist, genre, duration, rating);

        if (success) {
            outputArea.setText("Song added!\n");
        } else {
            outputArea.setText("Invalid input:\n> Rating must be from 1–5\n> Duration must be a positive integer\n");
        }

        clearFields();
    }

    /**
     * Displays all songs stored.
     */
    @FXML
    public void handleViewAll() {
        ArrayList<Song> songs = data.getAllSongs();

        StringBuilder output = new StringBuilder();

        output.append("All Songs\n");
        output.append("********************************\n");

        for (Song s : songs) {
            output.append(s.toString()).append("\n");
        }

        outputArea.setText(output.toString());
    }

    /**
     * Filters songs by genre and displays the results.
     */
    @FXML
    public void handleGenreFilter() {
        // Removes leading/trailing spaces
        String input = genreField.getText().trim();

        // Converts non-preset genre entries to OTHER
        if (input.isEmpty()) {
            input = "OTHER";
        }

        Genre genreEnum = Genre.fromString(input);
        ArrayList<Song> songs = data.getSongsByGenre(input);
        StringBuilder output = new StringBuilder();

        // Case 1: No songs stored
        if (data.getAllSongs().isEmpty()) {
            output.append("No songs in directory.\n");
        }
        // Case 2: No stored songs are in genre
        else if (songs.isEmpty()) {
            output.append("No songs found in genre: ").append(genreEnum).append("\n");
        }
        // Case 3: Genre matches to stored songs
        else {
            output.append("Songs found in genre: ").append(genreEnum).append("\n");
            output.append("********************************\n");

            for (Song s : songs) {
                output.append(s.toString()).append("\n");
            }
        }

        outputArea.setText(output.toString());

        clearFields();
    }

    /**
     * Displays the top 5 highest-rated songs.
     */
    @FXML
    public void handleTop5() {
        ArrayList<Song> top = data.getTop5Rated();

        StringBuilder output = new StringBuilder();

        if (data.getAllSongs().isEmpty()) {
            output.append("No songs in directory\n");
        }
        else {
            output.append("Top 5 Songs\n");
            output.append("********************************\n");

            for (int i = 0; i < top.size(); i++) {
                Song s = top.get(i);

                String prefix;

                // Adds medal emojis for the top 3
                if (i == 0) prefix = "🥇 ";
                else if (i == 1) prefix = "🥈 ";
                else if (i == 2) prefix = "🥉 ";
                else prefix = "        ";

                output.append(prefix).append(s.toString()).append("\n");
            }
        }

        outputArea.setText(output.toString());
    }

    /**
     * Displays the average rating of all songs.
     */
    @FXML
    public void handleAverage() {
        double avg = data.getAverageRating();

        // Rounds to the hundredths place
        outputArea.setText("Average rating: " + String.format("%.2f", avg));
    }

    /**
     * Calculates and displays the total duration of all songs.
     */
    @FXML
    public void handleTotalDuration() {
        int total = data.getTotalDuration();

        // Converts to __minutes __seconds
        int minutes = total / 60;
        int seconds = total % 60;

        outputArea.setText("Total Duration: " + minutes + " min " + seconds + " sec");
    }

    /**
     * Saves the current stored songs to a CSV file.
     */
    @FXML
    public void handleSave() {
        // Uses the title field as filename input
        String filename = fileNameField.getText().trim();

        // Checks title field is empty
        if (filename.isEmpty()) {
            outputArea.setText("Enter a file name in the File Name field (e.g. songs.csv)");
            return;
        }

        // Checks if user input ends in .csv
        if (!filename.endsWith(".csv")) {
            filename += ".csv";
        }

        // Attempts to save file
        boolean success = FileHandler.saveAsCSV(filename, data);

        if (success) {
            outputArea.setText("Songs saved to: " + filename);
        } else {
            outputArea.setText("Could not save file!");
        }

        clearFields();
    }

    /**
     * Loads songs from a CSV file into the playlist.
     */
    @FXML
    public void handleLoad() {
        // Uses the title field as filename input
        String filename = fileNameField.getText().trim();

        // Checks title field is empty
        if (filename.isEmpty()) {
            outputArea.setText("Enter a file name in the File Name field (e.g. songs.csv)");
            return;
        }

        // Checks if user input ends in .csv
        if (!filename.endsWith(".csv")) {
            filename += ".csv";
        }

        File file = new File(filename);

        // Checks file exists
        if (!file.exists()) {
            outputArea.setText("File not found: " + filename);
            return;
        }

        // Loads data
        Data loadedData = FileHandler.loadFromCSV(filename);
        ArrayList<Song> loadedSongs = loadedData.getAllSongs();

        // File exists but no valid songs stored inside
        if (loadedSongs.isEmpty()) {
            outputArea.setText("No valid songs found in file: " + filename);
            return;
        }

        // Merges loaded songs into current data
        for (Song song : loadedSongs) {
            data.addSong(
                    song.getTitle(),
                    song.getArtist(),
                    song.getGenre(),
                    song.getDuration(),
                    song.getRating()
            );
        }

        outputArea.setText("Songs loaded from: " + filename);

        clearFields();
    }

    /**
     * Prompts the user to confirm exiting the application.
     */
    @FXML
    public void handleExit() {
        // Popup window
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Are you sure you want to exit?");

        // Exits program if user confirms
        if (alert.showAndWait().get() == ButtonType.OK) {
            Platform.exit();
        }
    }

    /**
     * About pop-up
     */
    @FXML
    public void handleAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About Menu");
        alert.setHeaderText("Music Playlist Generator");

        alert.setContentText(
                "Version: 1.0\n" +
                        "Authors: Jacob Delve, Fadil Gbonjubola, Ali Jaffary, Sofia Laganas \n" +
                        "Contacts: jacob.delve@ucalgary.ca, fadil.gbonjubola@ucalgary.ca, ali.jaffary@ucalgary.ca, sofia.laganas@ucalgary.ca \n" +
                        "This program allows users to add songs, save and load files, and view data from stored songs."
        );

        alert.showAndWait();
    }

    /**
     * Loads songs from a CSV file during application startup.
     * Used when a filename is provided as a command-line argument.
     * If the file is not found or contains no valid songs,
     * displays an error message in the output area.
     *
     * @param filename the name of the CSV file to load
     */
    public void loadFromFile(String filename){
        File file = new File(filename);

        if(!file.exists()){
            outputArea.setText("Startup file not found: " + filename);
            return;
        }

        Data startData = FileHandler.loadFromCSV(filename);

        ArrayList<Song> startSongs = startData.getAllSongs();

        for(int i = 0; i < startSongs.size(); i++){
            Song song = startSongs.get(i);

            data.addSong(song.getTitle(), song.getArtist(), song.getGenre(),
                    song.getDuration(), song.getRating());
        }

        outputArea.setText("Loaded startup csv file: " + filename);
    }

    /**
     * Helper method to clear text fields.
     */
    private void clearFields() {
        titleField.clear();
        artistField.clear();
        genreField.clear();
        durationField.clear();
        ratingField.clear();
    }

}