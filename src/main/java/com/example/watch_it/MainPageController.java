package com.example.watch_it;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

public class MainPageController {
    private Stage stage;

    @FXML
    private Label Action;
    @FXML
    private Label Comedy;
    @FXML
    private Label Drama;
    @FXML
    private Label Horror;
    @FXML
    private Label All;
    @FXML
    private Button Search;

    private final ArrayList<Movie> movies = new ArrayList<>();
    @FXML
    HBox allMovies;
    @FXML
    HBox allMovies2;


    private void getData(){
        Movie movie = new Movie(),movie2=new Movie(),movie3=new Movie();
        movie.setTitle("Spider Man");
        movie.setPoster_path("C:\\Users\\Youssef Mohsen\\IdeaProjects\\Watch_It\\src\\main\\resources\\com\\example\\watch_it\\assets\\Amazing_SpiderMan.png");

        movie2.setTitle("Spider Man2");
        movie2.setPoster_path("C:\\Users\\Youssef Mohsen\\IdeaProjects\\Watch_It\\src\\main\\resources\\com\\example\\watch_it\\assets\\Amazing_SpiderMan.png");

        movie3.setTitle("Gravity");
        movie3.setPoster_path("C:\\Users\\Youssef Mohsen\\IdeaProjects\\Watch_It\\src\\main\\resources\\com\\example\\watch_it\\assets\\DefaultImage.jpg");
        movies.add(movie);
        movies.add(movie2);
        movies.add(movie3);
        movies.add(movie);
        movies.add(movie);
        movies.add(movie);

    }

    @FXML
    private void initialize(){
        getData();
        for(Movie movie:movies){
            addToGUI(movie);
            addToGUI2(movie);
        }
    }

    private void addToGUI(Movie movie){

        VBox movieContainer = new VBox(10);
        movieContainer.setPrefWidth(200);
        movieContainer.setPrefHeight(200);
        Image image = new Image(movie.getPoster_path());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(237);
        imageView.setFitWidth(200);
        Label label = new Label(movie.getTitle());
        label.setStyle("-fx-text-size: 20; -fx-text-fill: white;");
        movieContainer.setAlignment(Pos.CENTER);
        movieContainer.getChildren().addAll(imageView, label);
        movieContainer.setOnMouseClicked(event -> onMouseClickedVBox(image));
        allMovies.getChildren().addAll(movieContainer);

    }
    private void addToGUI2(Movie movie){

        VBox movieContainer = new VBox(10);
        movieContainer.setPrefWidth(200);
        movieContainer.setPrefHeight(200);
        Image image = new Image(movie.getPoster_path());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(237);
        imageView.setFitWidth(200);
        Label label = new Label(movie.getTitle());
        label.setStyle("-fx-text-size: 20; -fx-text-fill: white;");
        movieContainer.setAlignment(Pos.CENTER);
        movieContainer.getChildren().addAll(imageView, label);
        movieContainer.setOnMouseClicked(event -> onMouseClickedVBox(image));
        allMovies2.getChildren().addAll(movieContainer);

    }

    public void onMouseClicked() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("movie-view.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MovieController controller = loader.getController();
        controller.setStage(stage);
        Scene scene = new Scene(root);
        stage.setTitle("Movie");
        stage.setResizable(false);
        stage.setX(-7);
        stage.setY(0);
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("@assets/Amazing_SpiderMan.png")));
        controller.refreshLabels("Watch Movie Amazing SpiderMan(2023)","Amazing SpiderMan",
                "Amazing SpiderMan Translated","Action","Amazing SpiderMan film.",
                "120 minutes",image);
        stage.setScene(scene);
        stage.show();

    }
    public void onMouseClickedVBox(Image image) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("movie-view.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MovieController controller = loader.getController();
        controller.setStage(stage);
        Scene scene = new Scene(root);
        stage.setTitle("Movie");
        stage.setResizable(false);
        stage.setX(-7);
        stage.setY(0);

        controller.refreshLabels("Watch Movie Amazing SpiderMan(2023)", "Amazing SpiderMan",
                "Amazing SpiderMan Translated", "Action", "Amazing SpiderMan film.",
                "120 minutes", image);
        stage.setScene(scene);
        stage.show();

    }

    public void onMouseEntered() {
        Action.setOnMouseEntered(event -> Action.setStyle("-fx-background-radius: 25; -fx-background-color:  #FFC107;"));
        Comedy.setOnMouseEntered(event -> Comedy.setStyle("-fx-background-radius: 25; -fx-background-color:  #FFC107;"));
        Drama.setOnMouseEntered(event -> Drama.setStyle("-fx-background-radius: 25; -fx-background-color:  #FFC107;"));
        Horror.setOnMouseEntered(event -> Horror.setStyle("-fx-background-radius: 25; -fx-background-color:  #FFC107;"));
        All.setOnMouseEntered(event -> All.setStyle("-fx-background-color: #FFC107; -fx-background-radius: 25; -fx-border-color: white; -fx-border-radius: 25;"));
        Search.setOnMouseEntered(event -> Search.setStyle("-fx-background-color: #FFC107; -fx-background-radius: 25; -fx-border-color: white; -fx-border-radius: 25;"));
    }

    public void onMouseExit() {
        // Handle mouse exit event
        Action.setOnMouseExited(event -> Action.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;"));
        Comedy.setOnMouseExited(event -> Comedy.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;"));
        Drama.setOnMouseExited(event -> Drama.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;"));
        Horror.setOnMouseExited(event -> Horror.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;"));
        All.setOnMouseExited(event -> All.setStyle("-fx-background-color: black; -fx-background-radius: 25; -fx-border-color: white; -fx-border-radius: 25;"));
        Search.setOnMouseExited(event -> Search.setStyle("-fx-background-color: black; -fx-background-radius: 25; -fx-border-color: white; -fx-border-radius: 25;"));
    }
    ////////////////////////////


    ///////////////////////////

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
