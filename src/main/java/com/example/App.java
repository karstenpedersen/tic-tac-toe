package com.example;

import java.io.IOException;

import com.example.logic.GameState;
import com.example.logic.Player;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static boolean darktheme = false;
    private static GameState state = GameState.PLAYING;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 600, 600);
        scene.getStylesheets().add(App.class.getResource("styles/lightTheme.css").toExternalForm());
        scene.getStylesheets().add(App.class.getResource("styles/playerX.css").toExternalForm());
        scene.getStylesheets().add(App.class.getResource("styles/style.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Tic Tac Toe");
        stage.show();
    }

    /**
     * Load FXML file.
     *
     * @param fxml name of fxml file.
     * @return loaded fxml.
     * @throws IOException throws IOException if fxml is invalid.
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    /**
     * Toggle between light and dark stylesheets.
     */
    public static void toggleTheme() {
        darktheme = !darktheme;

        if (darktheme)
            scene.getStylesheets().set(0, App.class.getResource("styles/darkTheme.css").toExternalForm());
        else
            scene.getStylesheets().set(0, App.class.getResource("styles/lightTheme.css").toExternalForm());
    }

    public static void setPlayerCss(Player player) {
        switch (player) {
            case X:
                scene.getStylesheets().set(1, App.class.getResource("styles/playerX.css").toExternalForm());
                break;
            case O:
                scene.getStylesheets().set(1, App.class.getResource("styles/playerO.css").toExternalForm());
                break;
        }
    }

    /**
     * Reset scene.
     */
    public static void reset() {
        try {
            scene.setRoot(loadFXML("primary"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * Exit application.
     */
    public static void exit() {
        Platform.exit();
    }

    // Getters
    public static GameState state() {
        return App.state;
    }

    // Setters
    public static void setState(GameState state) {
        App.state = state;
    }

}