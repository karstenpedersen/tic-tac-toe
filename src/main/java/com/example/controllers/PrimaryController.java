package com.example.controllers;

import com.example.App;
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
        update(boardController.nextPlayer());
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

    /**
     * Set state to won.
     *
     * @param winner player who won.
     */
    public void stateWon(Player winner) {
        // Display end of game modal
        modalVBox.setVisible(true);
        gameEndLabel.setText(winner.toString() + " WON");
    }

    /**
     * Set state to tied.
     */
    public void stateTied() {
        // Display tie
        gameEndLabel.setText("TIED");
        modalVBox.setVisible(true);
    }

    /**
     * Update info.
     *
     * @param nextPlayer next player to play.
     */
    public void update(Player nextPlayer) {
        // Display which players turn it is
        infoLabel.setText(nextPlayer.toString() + "'s turn");
    }

}
