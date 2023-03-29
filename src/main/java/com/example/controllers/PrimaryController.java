package com.example.controllers;

import com.example.App;
import com.example.logic.Board;
import com.example.logic.Player;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class PrimaryController {

    private Board board;
    @FXML
    private GridPane boardGridPane;
    @FXML
    private Label infoLabel, gameEndLabel;
    @FXML
    private VBox modalVBox;

    @FXML
    public void initialize() {
        modalVBox.setVisible(false);
        board = new Board();
        updateTurnInfo();

        // Create buttons
        for (int i = 0; i < board.size() * board.size(); i++)
            createButton(i);
    }

    /**
     * Update infoLabel to display who's turn it is.
     */
    private void updateTurnInfo() {
        // Display which players turn it is
        infoLabel.setText(board.nextPlayer().toString() + "'s' turn");
    }

    /**
     * Create button i on the board.
     *
     * @param i button index.
     */
    private void createButton(int i) {
        Button button = new Button();
        button.setId(Integer.toString(i + 1));
        button.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);

        // Add odd css class to odd buttons
        if (i % 2 != 0)
            button.getStyleClass().add("odd");

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String player = board.nextPlayer().toString();
                button.setText(player);
                board.play(Integer.parseInt(button.getId()));
                button.setDisable(true);
                button.getStyleClass().add(player);

                if (board.tied()) {
                    // Display tie
                    gameEndLabel.setText("TIED");
                    modalVBox.setVisible(true);
                } else if (board.won()) {
                    // Get winner
                    Player winner = board.nextPlayer() == Player.X ? Player.O : Player.X;

                    // Disable all buttons
                    boardGridPane.getChildren().forEach((c) -> c.setDisable(true));

                    // Display end of game modal
                    modalVBox.setVisible(true);
                    gameEndLabel.setText(winner.toString() + " WON");
                } else {
                    updateTurnInfo();
                }
            }
        });

        // Add button to board
        boardGridPane.add(button, i % 3, i / 3);
    }

    @FXML
    private void toggleTheme() {
        App.toggleTheme();
    }

    @FXML
    private void restartGame() {
        App.reset();
    }

    @FXML
    private void exitGame() {
        Platform.exit();
    }

}
