package com.example.watch_it;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class AddMovieController {

    @FXML
    public static ScrollPane secondPane;
    @FXML
    private TextField castNumber;
    @FXML
    private Button addButton;
    @FXML
    private VBox castNames;
    private Stage stage;
    @FXML
    public void onMouseEntered(){
        addButton.setOnMouseEntered(event -> addButton.setStyle("-fx-background-color: #FFC107; -fx-background-radius: 25; -fx-border-color: white; -fx-border-radius: 25;"));
    }
    @FXML
    public void onMouseExit(){
        addButton.setOnMouseExited(event -> addButton.setStyle("-fx-background-color: black; -fx-background-radius: 25; -fx-border-color: white; -fx-border-radius: 25;"));
    }
    @FXML
    private void enterCastNumber(){
        try{
            int numberOfCast = Integer.parseInt(castNumber.getText());
            castNames.setVisible(true);
            addCastName(numberOfCast);
        }catch (NumberFormatException e){
            e.printStackTrace();

        }


    }
    private void addCastName(int i){
        for(int no = 0; no<i; no++){
            TextField textField = new TextField();
            textField.setPromptText("name of cast " + (no+1));
            textField.setAlignment(Pos.CENTER);
            textField.setStyle("-fx-background-radius: 50;");
            castNames.getChildren().addAll(textField);

        }
    }
    public void setStage(Stage stage){
        this.stage = stage;
    }
}
