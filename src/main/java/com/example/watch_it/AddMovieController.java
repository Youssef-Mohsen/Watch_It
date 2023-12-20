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
    private TextField discreptionField;

    @FXML
    private TextField genreField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField posterField;

    @FXML
    private TextField realeaseDateField;

    @FXML
    private TextField revenueField;
    @FXML
    private Button addButton;
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
    private void addMovie(ActionEvent event) throws IOException {
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

        String[] genre = genres.split(",");
        ArrayList<String> genrearray = new ArrayList<>(Arrays.asList(genre));

       /* Movie movie = new Movie(name, LocalDate.parse(releasedate), runningtime, genrearray, language, country, poster, budget, revenue, Integer.parseInt(imdbscore), discreption);
        Movie.allmovies.add(movie);
       */

        ArrayList<String> newActor = new ArrayList<>();
        for (String newactor : castarray) {
            String[] a = newactor.split(" ");

            if (Cast.search(a[0], a[1]) == null)
                newActor.add(newactor);
        }


        if (newActor.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("Movie Added");
            alert.showAndWait();
            return;
        }

        toAddCast(event, newActor);
    }

    void toAddCast (ActionEvent event, ArrayList<String> newActor) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("all-movies.fxml"));
        root = loader.load();
        AllMoviesController controller = loader.getController();
        controller.toAddCast(event, newActor);
    }

}