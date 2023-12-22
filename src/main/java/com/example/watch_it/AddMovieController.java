package com.example.watch_it;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class AddMovieController {

    @FXML
    public ScrollPane secondPane;

    @FXML
    TextField BudgetField;

    @FXML
    TextField CountryField;

    @FXML
    TextField IMDBScoreField;

    @FXML
    TextField LanguageField;

    @FXML
    TextField RunningTimeField;

    @FXML
    TextField castField;

    @FXML
    TextField directorField;

    @FXML
    TextField discreptionField;

    @FXML
    TextField genreField;

    @FXML
    TextField nameField;

    @FXML
    TextField posterField;

    @FXML
    TextField realeaseDateField;

    @FXML
    TextField revenueField;

    @FXML
    private Button addButton;
    static ArrayList<String> newActor = new ArrayList<>();
    static  Movie AddedMovie;
    Stage stage;
    Parent root;
    @FXML
    public void onMouseEntered(){
        addButton.setOnMouseEntered(event -> addButton.setStyle("-fx-background-color: #FFC107; -fx-background-radius: 25; -fx-border-color: white; -fx-border-radius: 25;"));
    }
    @FXML
    public void onMouseExit(){
        addButton.setOnMouseExited(event -> addButton.setStyle("-fx-background-color: black; -fx-background-radius: 25; -fx-border-color: white; -fx-border-radius: 25;"));
    }
    @FXML
    private void addMovie (ActionEvent event) throws IOException {
        String name = nameField.getText();
        String budget = BudgetField.getText();
        String revenue = revenueField.getText();
        String releasedate = realeaseDateField.getText();
        String poster = posterField.getText();
        String genres = genreField.getText();
        String country = CountryField.getText();
        String discreption = discreptionField.getText();
        String director = directorField.getText();
        String runningtime = RunningTimeField.getText();
        String cast = castField.getText();
        String language = LanguageField.getText();
        String imdbscore = IMDBScoreField.getText();

        if (name.isEmpty() || budget.isEmpty() || revenue.isEmpty() || releasedate.isEmpty() ||
                poster.isEmpty() || genres.isEmpty() || country.isEmpty() || discreption.isEmpty() ||
                director.isEmpty() || runningtime.isEmpty() || cast.isEmpty() || language.isEmpty() || imdbscore.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Enter All Data");
            alert.showAndWait();
            return;
        }

        String[] actor = cast.split(",");
        ArrayList<String> castarray = new ArrayList<>(Arrays.asList(actor));
        System.out.println(castarray);

        String[] genre = genres.split(",");
        ArrayList<String> genrearray = new ArrayList<>(Arrays.asList(genre));

        Movie movie = new Movie(name, LocalDate.parse(releasedate), runningtime, genrearray, language, country, poster, budget, revenue, Float.parseFloat(imdbscore), discreption);
        AddedMovie = movie;
        for (String newactor : castarray) {
            String[] a = newactor.split(" ");

            if (Cast.search(a[0], a[1]) == null)
                newActor.add(newactor);
            System.out.println(newactor + " new in add");
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("");
        alert.setHeaderText(null);

        if (newActor.isEmpty()) {
            alert.setContentText("Movie Added");
            alert.showAndWait();
        }else{
            alert.setContentText("Movie Added, please continue to add cast data");
            alert.showAndWait();
            AllMoviesController.enteredData = true;
          //  toAddCast(event);
        }

    }
    void toAddCast (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("all-movies.fxml"));
        root = loader.load();
        AllMoviesController controller = loader.getController();
        controller.toAddCast(event);

    }

}