package com.example.controllers;

import com.example.logic.Board;
import com.example.logic.Player;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class PrimaryController {

    private Board board;
    @FXML
    private GridPane boardGridPane;
    @FXML
    private Label infoLabel;

    @FXML
    public void initialize() {
        setup();
    }

    private void setup() {
        board = new Board();
        updateTurnInfo();

        // Create buttons
        for (int i = 0; i < board.size() * board.size(); i++)
            createButton(i);
    }

    private void updateTurnInfo() {
        // Display which players turn it is
        infoLabel.setText(board.nextPlayer().toString() + "'s' turn");
    }

    private void createButton(int i) {
        Button button = new Button();
        String id = Integer.toString(i + 1);
        button.setText(id);
        button.setId(id);
        button.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
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
                    infoLabel.setText("Tied");
                } else if (board.won()) {
                    // Get winner
                    Player winner = board.nextPlayer() == Player.X ? Player.O : Player.X;

                    // Display winner
                    // TODO - Create pop up
                    infoLabel.setText(winner.toString() + " won");

                    // Disable all buttons
                    boardGridPane.getChildren().forEach((c) -> c.setDisable(true));
                } else {
                    updateTurnInfo();
                }
            }
        });

        // Add button to board
        boardGridPane.add(button, i % 3, i / 3);
    }

}
