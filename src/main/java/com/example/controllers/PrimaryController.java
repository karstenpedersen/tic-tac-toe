package com.example.controllers;

import com.example.App;
import com.example.logic.GameState;
import com.example.logic.Player;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PrimaryController {

    @FXML
    private Label infoLabel, gameEndLabel;
    @FXML
    private VBox modalVBox;
    @FXML
    private BoardController boardController;

    @FXML
    public void initialize() {
        modalVBox.setVisible(false);
        boardController.setParent(this);
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
        App.exit();
    }

    public void setState(GameState state) {
        switch (state) {
            case PLAYING:
                update();
                break;
            case WON:
                stateWon();
                break;
            case TIED:
                stateTied();
                break;
        }
    }

    public void stateWon() {
        // Get winner
        Player winner = boardController.nextPlayer() == Player.X ? Player.O : Player.X;

        boardController.disableButtons();

        // Display end of game modal
        modalVBox.setVisible(true);
        gameEndLabel.setText(winner.toString() + " WON");
    }

    public void stateTied() {
        // Display tie
        gameEndLabel.setText("TIED");
        modalVBox.setVisible(true);
    }

    public void update() {
        // Display which players turn it is
        infoLabel.setText(boardController.nextPlayer().toString() + "'s turn");

        // Update button hover color
        App.setPlayerCss(boardController.nextPlayer());
    }

}
