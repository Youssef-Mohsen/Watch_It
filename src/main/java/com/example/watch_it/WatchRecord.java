package com.example.watch_it;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class WatchRecord {
    private Parent root;
    private Stage stage;
    private Movie movie;
    public boolean aBoolean;
    public static final ArrayList<Movie> watchedMovies = new ArrayList<>();
    @FXML
    private ScrollPane Scroll;
    @FXML
    private HBox WatchedMovies;
    @FXML
    private Button Back;



    public void initializeItems() {
        getData();
        for (Movie movie : watchedMovies) {
            addToGUI(movie);
        }
    }

    private void getData() {
        if(movie!=null) {
            watchedMovies.add(movie);
        }
    }
    private void addToGUI(Movie movie) {
        VBox movieContainer = new VBox(10);
        movieContainer.setPrefWidth(300);
        movieContainer.setPrefHeight(200);
        Image image = new Image(getClass().getResourceAsStream(movie.getPoster_path()));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(237);
        imageView.setFitWidth(220);
        Label label = new Label(movie.getTitle());
        label.setStyle("-fx-text-size: 20; -fx-text-fill: white;");
        label.setOnMouseEntered(event -> label.setStyle("-fx-text-size: 20; -fx-text-fill: #FFC107;"));
        label.setOnMouseExited(event -> label.setStyle("-fx-text-size: 20; -fx-text-fill: white;"));
        movieContainer.setAlignment(Pos.CENTER);
        movieContainer.getChildren().addAll(imageView, label);
        movieContainer.setOnMouseClicked(event -> {
            try {
                onMouseClickedVBox(event,movie);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        WatchedMovies.getChildren().addAll(movieContainer);

    }
    public void onMouseClickedVBox(MouseEvent act, Movie movie) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("movie-view.fxml"));
        Parent root = loader.load();
        stage = (Stage)((Node)act.getSource()).getScene().getWindow();
        MovieController controller=loader.getController();
        controller.setStage(stage);
        controller.disableButtons();
        controller.disableWatch();
        controller.aBoolean=true;
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Image image = new Image(getClass().getResourceAsStream(movie.getPoster_path()));
        controller.refreshScreen("Watch Movie "+ movie.getTitle() + "("+movie.getRelease_date().getYear()+")", movie.getTitle(),
                movie.getTitle()+" Translated",movie.getGenres(), movie.getDescription(),
                movie.getRunning_time(), image,movie.getDirectorName(),movie.getCast().toString());
        stage.setScene(scene);
        stage.show();

    }
    public void backScenes(ActionEvent event) throws IOException {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("main-page.fxml"));
            root = loader.load();
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
    public void onMouseEntered(){
        Back.setOnMouseEntered(event -> Back.setStyle("-fx-background-color: #FFC107;" +
                " -fx-background-radius: 25; " +
                "-fx-border-color: white;" +
                " -fx-border-radius: 25;"));
    }
    public void onMouseExit(){
        Back.setOnMouseExited(event -> Back.setStyle("-fx-background-color: black;" +
                " -fx-background-radius: 25;" +
                " -fx-border-color: white;" +
                " -fx-border-radius: 25;"));
    }
    public void setMovieDetails(Movie movie){
        if(this.movie!=null) {
            this.movie = movie;
            System.out.println(this.movie.getTitle());
            System.out.println(this.movie.getPoster_path());
        }
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
