package com.example.watch_it;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Collection;

public class AddMovieController {

    @FXML
    public static ScrollPane secondPane;

    @FXML
    private TextField BudgetField;

    @FXML
    private TextField CountryField;

    @FXML
    private TextField IMDBScoreField;

    @FXML
    private TextField LanguageField;

    @FXML
    private TextField RunningTimeField;

    @FXML
    private TextField castField;

    @FXML
    private TextField directorField;

    @FXML
    private TextField DescriptionField;

    @FXML
    private TextField genreField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField posterField;

    @FXML
    private TextField releaseDateField;

    @FXML
    private TextField revenueField;
    @FXML
    private Button addButton;
    @FXML
    public void onMouseEntered(){
        addButton.setOnMouseEntered(event -> addButton.setStyle("-fx-background-color: #FFC107; -fx-background-radius: 25; -fx-border-color: white; -fx-border-radius: 25;"));
    }
    @FXML
    public void onMouseExit(){
        addButton.setOnMouseExited(event -> addButton.setStyle("-fx-background-color: black; -fx-background-radius: 25; -fx-border-color: white; -fx-border-radius: 25;"));
    }
    @FXML
    private void addMovie(ActionEvent event) throws IOException {
        String budget = BudgetField.getText();
        String name = nameField.getText();
        String revenue = revenueField.getText();
        String releaseDate = releaseDateField.getText();
        String posterPath = posterField.getText();
        String director = directorField.getText();
        String description = DescriptionField.getText();
        String runningTime = RunningTimeField.getText();
        String imdbScore = IMDBScoreField.getText();
        String language = LanguageField.getText();
        String country = CountryField.getText();
        String cast = castField.getText();
        String genre = genreField.getText();

        //checking if the name of the movie already exists
        //


        if (budget.isEmpty() || name.isEmpty() || revenue.isEmpty() || releaseDate.isEmpty() || posterPath.isEmpty() || director.isEmpty() || description.isEmpty() || runningTime.isEmpty() || imdbScore.isEmpty() || language.isEmpty() || country.isEmpty() || cast.isEmpty() || genre.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Enter All Data", ButtonType.OK );
            alert.setTitle("");

            alert.initOwner((addButton.getScene().getWindow()));
            alert.showAndWait();
        }

    }
}
