/**
 * CPSC 219 W26 Project Demo 3
 * <p>
 * Main Class
 * <p>
 * Launches the Music Playlist Generator application.
 * Loads the main FXML layout and displays the user interface.
 *
 * @author Jacob Delve, Fadil Gbonjubola, Ali Jaffary, Sofia Laganas
 * @version 1.0
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    /**
     * Launches the JavaFX application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * *Starts the JavaFX application.
     * *Loads the main FXML file and displays user interface.*
     * @param stage      the main application window
     * @throws Exception if the FXML file cannot be loaded
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main-view.fxml"));
        Scene scene = new Scene(loader.load(), 915, 755); // Sets scene size

        stage.setTitle("Music Playlist Generator");
        stage.setScene(scene);
        stage.show();

        MainController controller = loader.getController();
        java.util.List<String> args = getParameters().getRaw();

        if (!args.isEmpty()) {
            String csvFile = args.get(0);
            controller.loadFromFile(csvFile);
        }
    }

}


