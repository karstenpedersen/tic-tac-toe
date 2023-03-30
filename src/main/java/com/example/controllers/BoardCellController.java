package com.example.controllers;

import java.io.IOException;

import com.example.App;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

public class BoardCellController extends Button {

    private int index;
    private BoardController boardController;
    private ChangeListener<Boolean> hoverListener;

    public BoardCellController(BoardController boardController, int index) {
        this.index = index;
        this.boardController = boardController;

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("boardCell.fxml"));
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void initialize() {
        setId(Integer.toString(index));
        setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);

        // Add odd css class to odd buttons
        if (index % 2 != 0)
            getStyleClass().add("odd");

        // Hover listener
        hoverListener = (ov, oldValue, newValue) -> {
            if (newValue) {
                setText(boardController.nextPlayer().toString());
            } else {
                setText("");
            }
        };
        hoverProperty().addListener(hoverListener);

        // Set button action
        setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hoverProperty().removeListener(hoverListener);
                String player = boardController.nextPlayer().toString();
                setText(player);
                setDisable(true);
                getStyleClass().add(player);

                boardController.play(Integer.parseInt(getId()));
            }
        });
    }

}
