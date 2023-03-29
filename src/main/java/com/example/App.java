package com.example;

import java.io.IOException;

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

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 500, 500);
        scene.getStylesheets().add(App.class.getResource("lightTheme.css").toExternalForm());
        scene.getStylesheets().add(App.class.getResource("style.css").toExternalForm());
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
            scene.getStylesheets().set(0, App.class.getResource("darkTheme.css").toExternalForm());
        else
            scene.getStylesheets().set(0, App.class.getResource("lightTheme.css").toExternalForm());
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

}