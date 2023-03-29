package com.example.controllers;

import java.io.IOException;

import com.example.App;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

public class BoardCellController extends Button {

    private int index;
    private BoardController boardController;

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
        setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        setId(Integer.toString(index));

        // Add odd css class to odd buttons
        if (index % 2 != 0)
            getStyleClass().add("odd");

        // hoverProperty().addListener((ov, oldValue, newValue) -> {
        // if (newValue) {
        // setText(boardController.board().nextPlayer().toString());
        // } else {
        // setText("");
        // }
        // });

        setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String player = boardController.nextPlayer().toString();
                setText(player);
                setDisable(true);
                getStyleClass().add(player);

                boardController.play(Integer.parseInt(getId()));
            }
        });
    }

}
