package com.example.controllers;

import com.example.App;
import com.example.logic.Board;
import com.example.logic.GameState;
import com.example.logic.Player;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class BoardController {

    private Board board;
    private PrimaryController parent;
    @FXML
    private GridPane boardGridPane;

    @FXML
    public void initialize() {
        board = new Board();

        // Create buttons
        for (int i = 0; i < board.size() * board.size(); i++)
            createButton(i);
    }

    private void createButton(int index) {
        BoardCellController buttonController = new BoardCellController(this, index + 1);
        boardGridPane.add(buttonController, index % 3, index / 3);
    }

    public void disableButtons() {
        // Disable all buttons
        boardGridPane.getChildren().forEach((c) -> c.setDisable(true));
    }

    public Player nextPlayer() {
        return board.nextPlayer();
    }

    public void play(int index) {
        board.play(index);

        if (board.tied()) {
            App.setState(GameState.TIED);
            parent.stateTied();
        } else if (board.won()) {
            App.setState(GameState.WON);
            parent.stateWon();
        } else {
            parent.update();
        }
    }

    // Setters
    public void setParent(PrimaryController parent) {
        this.parent = parent;
    }

}
