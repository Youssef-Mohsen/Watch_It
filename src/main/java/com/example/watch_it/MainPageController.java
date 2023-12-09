package com.example.watch_it;

import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;


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

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
public class MainPageController {
    private Stage stage;
    private Timeline autoScrollTimeline;
    private Timeline autoScrollTimeline2;
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
    private Label Recent;
    @FXML
    private Label topMovies;
    @FXML
    private Label watchRecords;
    @FXML
    private Button Search;
    @FXML
    private ScrollPane Scroll1;
    @FXML
    private ScrollPane Scroll2;


    private final ArrayList<Movie> moviesTop = new ArrayList<>();
    private final ArrayList<Movie> moviesRecent = new ArrayList<>();
    @FXML
    HBox allMovies;
    @FXML
    HBox allMovies2;


    private void setObject(Movie movie, String titleMovie, String pathMovie) {
        movie.setTitle(titleMovie);
        movie.setPoster_path(pathMovie);
    }

    private void getData() {
        String pathSpider = "C:\\Users\\Youssef Mohsen\\IdeaProjects\\Watch_It\\src\\main\\resources\\com\\example\\watch_it\\assets\\Amazing_SpiderMan.png";
        String pathGravity = "C:\\Users\\Youssef Mohsen\\IdeaProjects\\Watch_It\\src\\main\\resources\\com\\example\\watch_it\\assets\\DefaultImage.jpg";
        Movie movie = new Movie(),
                movie2 = new Movie(),
                movie3 = new Movie();

        setObject(movie, "Spider Man", pathSpider);
        setObject(movie2, "Spider Man2", pathGravity);
        setObject(movie3, "Gravity", pathSpider);

        moviesTop.add(movie);
        moviesTop.add(movie2);
        moviesTop.add(movie3);
        moviesTop.add(movie2);
        moviesTop.add(movie);
        moviesTop.add(movie2);
        /////////////////////
        moviesRecent.add(movie);
        moviesRecent.add(movie2);
        moviesRecent.add(movie);
        moviesRecent.add(movie);
        moviesRecent.add(movie);
        moviesRecent.add(movie);
    }

    @FXML
    private void initialize() {
        getData();
        for (Movie movie : moviesTop) {
            addToGUI(movie);
        }
        for (Movie movie : moviesRecent) {
            addToGUI2(movie);
        }
        watchRecords.setOnMouseClicked(mouseEvent -> onMouseClicked());
        onMouseEntered();
        onMouseExit();
        setupAutoScroll();

    }

    private void addToGUI(Movie movie) {
        VBox movieContainer = new VBox(10);
        movieContainer.setPrefWidth(300);
        movieContainer.setPrefHeight(200);
        Image image = new Image(movie.getPoster_path());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(237);
        imageView.setFitWidth(220);
        Label label = new Label(movie.getTitle());
        label.setStyle("-fx-text-size: 20; -fx-text-fill: white;");
        label.setOnMouseEntered(event -> label.setStyle("-fx-text-size: 20; -fx-text-fill: #FFC107;"));
        label.setOnMouseExited(event -> label.setStyle("-fx-text-size: 20; -fx-text-fill: white;"));
        movieContainer.setAlignment(Pos.CENTER);
        movieContainer.getChildren().addAll(imageView, label);

        movieContainer.setOnMouseClicked(event -> onMouseClickedVBox(image));
        allMovies.getChildren().addAll(movieContainer);

    }

    private void addToGUI2(Movie movie) {
        VBox movieContainer = new VBox(10);
        movieContainer.setPrefWidth(300);
        movieContainer.setPrefHeight(200);
        Image image = new Image(movie.getPoster_path());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(237);
        imageView.setFitWidth(220);
        Label label = new Label(movie.getTitle());
        label.setStyle("-fx-text-size: 20; -fx-text-fill: white;");
        label.setOnMouseEntered(event -> label.setStyle("-fx-text-size: 20; -fx-text-fill: #FFC107;"));
        label.setOnMouseExited(event -> label.setStyle("-fx-text-size: 20; -fx-text-fill: white;"));
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
        controller.refreshLabels("Watch Movie Amazing SpiderMan(2023)", "Amazing SpiderMan",
                "Amazing SpiderMan Translated", "Action", "Amazing SpiderMan film.",
                "120 minutes");
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

        controller.refreshScreen("Watch Movie Amazing SpiderMan(2023)", "Amazing SpiderMan",
                "Amazing SpiderMan Translated", "Action", "Amazing SpiderMan film.",
                "120 minutes", image);
        stage.setScene(scene);
        stage.show();

    }

    public void onMouseEntered() {
        onMouseEnteredLabels(Action);
        onMouseEnteredLabels(Comedy);
        onMouseEnteredLabels(Drama);
        onMouseEnteredLabels(Horror);
        All.setOnMouseEntered(event -> All.setStyle("-fx-background-color: #FFC107;" +
                " -fx-background-radius: 25; " +
                "-fx-border-color: white;" +
                " -fx-border-radius: 25;"));
        Search.setOnMouseEntered(event -> Search.setStyle("-fx-background-color: #FFC107;" +
                " -fx-background-radius: 25;" +
                " -fx-border-color: white;" +
                " -fx-border-radius: 25;"));
        Recent.setOnMouseEntered(event -> underlineLabel(Recent, true));
        topMovies.setOnMouseEntered(event -> underlineLabel(topMovies, true));
        watchRecords.setOnMouseEntered(event -> { System.out.println("Mouse entered!");
            watchRecords.setStyle("-fx-background-color: #FFC107; -fx-background-radius: 10;");
            underlineLabel(watchRecords, true);
        });

    }

    public void onMouseExit() {
        // Handle mouse exit event
        onMouseExitLabels(Action);
        onMouseExitLabels(Comedy);
        onMouseExitLabels(Drama);
        onMouseExitLabels(Horror);
        All.setOnMouseExited(event -> All.setStyle("-fx-background-color: black;" +
                " -fx-background-radius: 25;" +
                " -fx-border-color: white;" +
                " -fx-border-radius: 25;"));
        Search.setOnMouseExited(event -> Search.setStyle("-fx-background-color: black;" +
                " -fx-background-radius: 25;" +
                " -fx-border-color: white;" +
                " -fx-border-radius: 25;"));
        Recent.setOnMouseExited(event -> underlineLabel(Recent, false));
        topMovies.setOnMouseExited(event -> underlineLabel(topMovies, false));
        watchRecords.setOnMouseExited(mouseEvent -> { System.out.println("Mouse exit!");
            watchRecords.setStyle("-fx-background-color: #001f2f; -fx-background-radius: 10;");
            underlineLabel(watchRecords, false);
        });

    }

    private void underlineLabel(Label label, boolean underline) {
        label.setUnderline(underline);
    }

    private void onMouseEnteredLabels(Label label) {
        label.setOnMouseEntered(event -> label.setStyle("-fx-background-radius: 25;" +
                " -fx-background-color:  #FFC107;"));
    }

    private void onMouseExitLabels(Label label) {
        label.setOnMouseExited(event -> label.setStyle("-fx-background-radius: 25;" +
                " -fx-background-color:  #565661;"));
    }
    private void setupAutoScroll() {
        double scrollDuration = 8; // Adjust the duration of the auto-scrolling

        // Create a timeline for the auto-scrolling animation
        autoScrollTimeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(Scroll1.hvalueProperty(), 0)),
                new KeyFrame(Duration.seconds(scrollDuration), new KeyValue(Scroll1.hvalueProperty(), 1))
        );
        autoScrollTimeline2 = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(Scroll2.hvalueProperty(), 0)),
                new KeyFrame(Duration.seconds(scrollDuration), new KeyValue(Scroll2.hvalueProperty(), 1))
        );

        // Set the cycle count to indefinite for continuous scrolling
        autoScrollTimeline.setCycleCount(Timeline.INDEFINITE);
        startAutoScroll();
        Scroll1.setOnMouseEntered(event ->  stopAutoScroll());
        Scroll1.setOnMouseExited(event -> startAutoScroll());
        ///////////////////////////////////////////////////////
        // Set the cycle count to indefinite for continuous scrolling
        autoScrollTimeline2.setCycleCount(Timeline.INDEFINITE);
        startAutoScroll2();
        Scroll2.setOnMouseEntered(event ->  stopAutoScroll2());
        Scroll2.setOnMouseExited(event -> startAutoScroll2());
    }
    @FXML
    private void startAutoScroll() {
        autoScrollTimeline.play();
    }

    @FXML
    private void stopAutoScroll() {
        // Stop auto-scrolling when user interacts with the ScrollPane
        autoScrollTimeline.pause();
    }
    ///////////////////////////////

    ///////////////////////////////
    @FXML
    private void startAutoScroll2() {
        autoScrollTimeline2.play();
    }

    @FXML
    private void stopAutoScroll2() {
        // Stop auto-scrolling when user interacts with the ScrollPane
        autoScrollTimeline2.pause();
    }
    ///////////////////////////


    ///////////////////////////

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
