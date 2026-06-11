# Music Playlist Generator

## Authors
Jacob Delve, Fadil Gbonjubola, Ali Jaffary, Sofia Laganas  
CPSC 219 W26 - Tutorial 3 (Shuvam)  
Date: 2026-04-20

## Description
A JavaFX desktop application for managing a music playlist. Users can add
songs, view statistics, filter by genre, and save/load playlists to CSV files.
Built using Java 25, JavaFX, and JUnit 5 following object-oriented design
principles including inheritance, enums, and interfaces.

## Features
- Add songs with title, artist, genre, duration, and rating
- View all songs
- Filter songs by genre
- View top 5 rated songs
- View average rating and total duration
- Save and load playlists to/from CSV files
- Launch with a CSV file to load data on startup

## How to Run

### Option 1: Run from IntelliJ IDEA
1. Open the project in IntelliJ IDEA
2. Run `Main.java`

### Option 2: Run from the .jar file
1. Open terminal on your machine
2. Navigate to the directory containing FinalSubmission.jar using cd /path/to/folder
3. Make sure you have the JavaFX SDK installed on your machine.
4. Run the following command from the terminal:
java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml -jar FinalSubmission.jar
Replace `/path/to/javafx-sdk/lib` with the actual path to your JavaFX SDK.

### Option 3: Load a CSV file on startup
To pre-load a playlist from a CSV file when launching:

## GitLab Repository
Demo 1: https://csgit.ucalgary.ca/jacob.delve/groupproject
Demo 2: https://csgit.ucalgary.ca/jacob.delve/demo-2-tutorial-3
Final Project Submission:

### Contributions
**Jacob Delve**
- Implemented the abstract MediaItem class
- Fixed and updated the Song class
- Implemented the hashCode method in Song class
- Fixed bugs in MainController
- Added command line CSV loading on startup (loadFromFile method in MainController, updated Main.java)

**Sofia Laganas**
- Implemented Main Controller class
- Implemented main-view fxml
- Implemented Data class
- Minor fixes throughout all classes including comments, JavaDocs, and general code cleanup

**Fadil Gbonjubola**
- Implemented GenreEnum test class
- Implemented negative test cases for DataTest class
- General JavaDocs fixes across all files
- Fixed command line argument for pre-loading CSV file at startup

**Ali Jaffary**
- Implemented FileHandler Test class
- Implemented postive test cases for DataTest class
- Implemented JavaDocs across files
- Updated Main class

