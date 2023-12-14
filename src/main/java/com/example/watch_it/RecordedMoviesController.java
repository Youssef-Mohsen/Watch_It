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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class RecordedMoviesController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private User user;
    public static final ArrayList<Movie> toWatchMovies= new ArrayList<>();
    private ArrayList<UserWatchRecord> WatchedMovies;
    @FXML
    private Button Back;
    @FXML
    private HBox watchedHbox;
    @FXML
    private HBox toWatchHbox;

    @FXML
    public void initialize(){
        for (Movie movie : WatchRecord.watchedMovies) {
            addWatchedList(movie);
        }
        for (Movie movie : toWatchMovies) {
            addToWatchedList(movie);
        }
        onMouseExit();
        onMouseEntered();
    }

    private void addWatchedList(Movie movie1){
        VBox movieContainer = new VBox(10);
        movieContainer.setPrefWidth(300);
        movieContainer.setPrefHeight(200);
        Image image = new Image(getClass().getResourceAsStream(movie1.getPoster_path()));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(237);
        imageView.setFitWidth(220);
        Label label = new Label(movie1.getTitle());
        label.setStyle("-fx-text-size: 20; -fx-text-fill: white;");
        label.setOnMouseEntered(event -> label.setStyle("-fx-text-size: 20; -fx-text-fill: #FFC107;"));
        label.setOnMouseExited(event -> label.setStyle("-fx-text-size: 20; -fx-text-fill: white;"));
        movieContainer.setAlignment(Pos.CENTER);
        movieContainer.getChildren().addAll(imageView, label);
        movieContainer.setOnMouseClicked(event -> {
            try {
                onMouseClickedVBox(event,movie1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        watchedHbox.getChildren().addAll(movieContainer);
    }
    private void addToWatchedList(Movie movie1){
        VBox movieContainer = new VBox(10);
        movieContainer.setPrefWidth(300);
        movieContainer.setPrefHeight(200);
        Image image = new Image(getClass().getResourceAsStream(movie1.getPoster_path()));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(237);
        imageView.setFitWidth(220);
        Label label = new Label(movie1.getTitle());
        label.setStyle("-fx-text-size: 20; -fx-text-fill: white;");
        label.setOnMouseEntered(event -> label.setStyle("-fx-text-size: 20; -fx-text-fill: #FFC107;"));
        label.setOnMouseExited(event -> label.setStyle("-fx-text-size: 20; -fx-text-fill: white;"));
        Label label1 =new Label("Rate: "+movie1.userRate);
        label1.setStyle("-fx-text-size: 20; -fx-text-fill: white;");
        label1.setOnMouseEntered(event -> label1.setStyle("-fx-text-size: 20; -fx-text-fill: #FFC107;"));
        label1.setOnMouseExited(event -> label1.setStyle("-fx-text-size: 20; -fx-text-fill: white;"));
        Image image1 = new Image(getClass().getResourceAsStream("assets/fullStar.png"));
        ImageView imageView1 = new ImageView(image1);
        imageView1.setFitHeight(18);
        imageView1.setFitWidth(20);
        HBox box =new HBox(label1,imageView1,label);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(6);
        movieContainer.setAlignment(Pos.CENTER);
        movieContainer.getChildren().addAll(imageView,box);
        movieContainer.setOnMouseClicked(event -> {
            try {
                onMouseClickedVBox(event,movie1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        toWatchHbox.getChildren().addAll(movieContainer);
    }
    public void onMouseClickedVBox(MouseEvent act, Movie movie1) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("movie-view.fxml"));
        Parent root = loader.load();
        stage = (Stage)((Node)act.getSource()).getScene().getWindow();
        MovieController controller=loader.getController();
        controller.setStage(stage);
        controller.watchMovie(movie1);
        controller.page5=-1;
        controller.disableButtons();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        Image image = new Image(getClass().getResourceAsStream(movie1.getPoster_path()));
        controller.refreshScreen("Watch Movie "+ movie1.getTitle() + "("+movie1.getRelease_date().getYear()+")", movie1.getTitle(),
                movie1.getTitle()+" Translated",movie1.getGenres(), movie1.getDescription(),
                movie1.getRunning_time(), image,MainPageController.movie5.getDirectorName(),MainPageController.movie5.getCastNames());
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    public void backScenes(ActionEvent act) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("profile-page.fxml"));
        root = loader.load();
        stage = (Stage)((Node)act.getSource()).getScene().getWindow();
        profilePageController controller=loader.getController();
        controller.setStage(stage);
        controller.setdata();
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
   /* public void setMovieWatched(Movie movie){
        if(this.movieW!=null) {
            this.movie = movie;
        }
    }
    public void setMovieToWatched(Movie movie){
        if(this.movie2!=null) {
            this.movie2 = movie;
        }*/
    }

