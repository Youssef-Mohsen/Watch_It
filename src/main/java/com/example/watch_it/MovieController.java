package com.example.watch_it;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;



public class MovieController {

    private Stage stage;
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
    private Button Back;

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

    public void backScenes(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-page.fxml"));
        Parent root = loader.load();
        MainPageController controller = loader.getController();
        controller.setStage(stage);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Movie");
        stage.setResizable(false);
        stage.setX(-7);
        stage.setY(0);
        stage.setScene(scene);
        stage.show();
    }
    public  void onMouseEntered(){
        Back.setOnMouseEntered(event -> Back.setStyle("-fx-background-color: #FFC107; -fx-background-radius: 25; -fx-border-color: white; -fx-border-radius: 25;"));
    }
    public  void onMouseExit(){
        Back.setOnMouseExited(event -> Back.setStyle("-fx-background-color: black; -fx-background-radius: 25; -fx-border-color: white; -fx-border-radius: 25;"));
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }

}