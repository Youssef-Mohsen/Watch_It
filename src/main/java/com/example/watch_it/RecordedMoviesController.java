package com.example.watch_it;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class RecordedMoviesController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private User user;
    private ArrayList<Movie> toWatchMovies;
    private ArrayList<UserWatchRecord> WatchedMovies;
    @FXML
    private Button Back;
    @FXML
    private HBox watchedHbox;
    @FXML
    private HBox toWatchHbox;

   /* @FXML
    public void initialize(){

    }*/
    private void addWatchedList(){

    }
    private void addToWatchedList(){

    }
    @FXML
    public void backScenes(ActionEvent act) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("profile-page.fxml")));
        stage = (Stage) ((Node) act.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void onMouseEntered(){
        Back.setOnMouseEntered(event -> Back.setStyle("-fx-background-color: #FFC107; -fx-background-radius: 25; -fx-border-color: white; -fx-border-radius: 25;"));
    }
    @FXML
    private void onMouseExit(){
        Back.setOnMouseExited(event -> Back.setStyle("-fx-background-color: black; -fx-background-radius: 25; -fx-border-color: white; -fx-border-radius: 25;"));
    }
    public void setStage(Stage stage){
        this.stage = stage;
    }
    public void setUser(User user){
        this.user = user;
        setdata();
    }
    private void setdata(){

    }
}
