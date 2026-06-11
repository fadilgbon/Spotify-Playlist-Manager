/**
 * CPSC 219 W26 Project Demo 3
 * <p>
 * FileHandler Class
 * <p>
 * Handles saving and loading songs for the Music Playlist Generator.
 * Provides methods to write song data to CSV files and read songs
 * from CSV files into a Data object.
 *
 * @author Jacob Delve, Fadil Gbonjubola, Ali Jaffary, Sofia Laganas
 * @version 1.0
 */

import java.io.*;
import java.util.ArrayList;

/**
 * Utility class that is used for saving and loading songs to and from CSV files.
 * <p>
 * Provides methods that save all songs from a Data object o a CSV file.
 * Also provides methods to load songs from CSV files into a Data object.
 */
public class FileHandler {

    /**
     * Saves all songs from the given Data object to a CSV filename.
     *
     * @param filename the name of the CSV file to save the Data object to
     * @param data the Data object that contains the songs to save
     * @return if the file was saved successfully, false if an error occurs in saving the data object
     */
    public static boolean saveAsCSV(String filename, Data data) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {

            // Header row
            writer.println("Title,Artist,Genre,Duration,Rating");

            ArrayList<Song> songs = data.getAllSongs();

            for (int i = 0; i < songs.size(); i++) {
                writer.printf("%s,%s,%s,%d,%.1f%n",
                        songs.get(i).getTitle().trim(),
                        songs.get(i).getArtist().trim(),
                        songs.get(i).getGenre(),
                        songs.get(i).getDuration(),
                        songs.get(i).getRating());
            }

            return true;
        }

        // If error, program will print message and return false without crashing
        catch (IOException e) {
            System.out.println("Error saving as CSV file: " + e.getMessage());
            return false;
        }
    }

    /**
     * Loads songs from a CSV file into a new Data object.
     * Lines that are invalid (missing values, invalid numeric values, or empty lines are skipped in parsing.
     * If the file is not found or is not of a form that can be read, empty Data object is returned.
     *
     * @param filename the name of the CSV file to read
     * @return a Data object containing the songs loaded from the file
     */
    public static Data loadFromCSV(String filename) {
        Data data = new Data();

        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){

            String line;

            // Skip header (Title,Artist,...)
            reader.readLine();

            while((line = reader.readLine()) != null){
                line = line.trim();

                if(line.isEmpty()){
                    continue;
                }

                String[] parts = line.split(",");

                // If a row has fewer than 5 values, skip it and continue
                if(parts.length != 5){
                    continue;
                }

                try {
                    String title = parts[0].trim();
                    String artist = parts[1].trim();
                    String g = parts[2].trim();
                    int duration = Integer.parseInt(parts[3].trim());
                    double rating = Double.parseDouble(parts[4].trim());

                    Genre genre = Genre.fromString(g);
                    data.addSong(title, artist, genre, duration, rating);

                }// Problem with numeric value in CSV
                catch(NumberFormatException e){
                    System.out.println("Error parsing numeric value(s): " + e.getMessage());
                }
            }
        }

        // File does not exist
        catch(FileNotFoundException e){
            System.out.println("File not found: " + filename);

        }

        // Problem reading the loaded file
        catch(IOException e){
            System.out.println("Error loading file: " + e.getMessage());
        }



        return data;
    }

}
