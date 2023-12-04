package com.example.watch_it;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class HelloController {

    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    private Label titleMovie;
    @FXML
    private Label movieName;
    @FXML
    private Label Film;
    @FXML
    private Label Genre;
    @FXML
    private Label duration;
    @FXML
    private Label description;
    @FXML
    private ImageView imagePreview;
    @FXML
    private TextField nameTextField;
    @FXML
    private Label answer;

    // Other fields and methods...

    // Method to update the text of the first label

    public void refreshLabels(String filmTitle, String newMovieName, String newFilm, String filmGenre,
                              String filmDescription, String filmDuration, Image image) {
        titleMovie.setText(filmTitle);
        movieName.setText(newMovieName);
        Film.setText(newFilm);
        Genre.setText(filmGenre);
        description.setText(filmDescription);
        duration.setText(filmDuration);
        imagePreview.setImage(image);

    }

    public void displayAnswer(String theAnswer) {
        answer.setText(theAnswer);
    }

    public void switchWindow2(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("another-window.fxml"));
        root = loader.load();
        HelloController controller = loader.getController();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Movie");
        stage.setResizable(false);
        stage.setX(-7);
        stage.setY(0);


        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/" + "DefaultImage.jpg")));
        controller.refreshLabels("Watch Movie", "Gravity", "Gravity Translated"
                , "Drama", "Gravity Film.", "125 minutes", image);
        stage.setScene(scene);
        stage.show();
    }

    public void switchWindow1(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("movie-view.fxml"));
        root = loader.load();
        HelloController controller = loader.getController();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        String theAnswer = nameTextField.getText();
        controller.displayAnswer(theAnswer);
        stage.setTitle("Movie");
        stage.setResizable(false);
        stage.setX(-7);
        stage.setY(0);


        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/" + "Amazing_SpiderMan.png")));
        controller.refreshLabels("Watch Movie", "Amazing SpiderMan", "Amazing SpiderMan Translated"
                , "Action", "Amazing SpiderMan Film.", "120 minutes", image);
        stage.setScene(scene);
        stage.show();
    }
}