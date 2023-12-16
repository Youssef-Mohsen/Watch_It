package com.example.watch_it;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

import javafx.fxml.FXML;

public  class MainPageController {
    private Parent root;
    private Scene scene;
    private Stage stage;
    private Timeline autoScrollTimeline;
    private Timeline autoScrollTimeline2;
    private Timeline autoScrollTimeline3;
    @FXML
    private BorderPane mainPane;
    @FXML
    private Label Action;
    @FXML
    private Label Comedy;
    @FXML
    private Label Main;
    @FXML
    private Label Drama;
    @FXML
    private Label Horror;
    @FXML
    private Label All;
    @FXML
    private ImageView profile;
    @FXML
    private Label Recent;
    @FXML
    private Label topMovies;
    @FXML
    private Label TopRated;
    @FXML
    private Label watchRecords;
    @FXML
    private Button Search;
    @FXML
    private ScrollPane Scroll1;
    @FXML
    private ScrollPane Scroll2;
    @FXML
    private ScrollPane Scroll3;
    @FXML
    private Pane AllMovies;
    @FXML
    private MenuButton menuButton;
    public static String menuValue;
    @FXML
    private TextField searchItem;
    private Movie movie;
    @FXML
    private  Label counter;
    private User user;
    public static Movie movie5;
    static int moviePage = 0;
    private final ArrayList<Movie> moviesTop = new ArrayList<>();
    private final ArrayList<Movie> moviesRecent = new ArrayList<>();
    private final ArrayList<Movie> moviesTopRated = new ArrayList<>();
    UserWatchRecord userWatchRecord;
    @FXML
    HBox TopMovies;
    @FXML
    HBox RecentMovies;
    @FXML
    HBox TopRatedMovies;
    @FXML
    private Button Back;



    private void getData() {
        for(Movie movie : Movie.MostViewedMovies(Movie.allmovies)){
            moviesTop.add(movie);
        }
        Collections.sort(Movie.allmovies, Comparator.comparingInt(Movie::getYear).reversed());
        for(Movie movie:Movie.allmovies)
        {
            moviesRecent.add(movie);
        }
        Collections.sort(Movie.allmovies, Comparator.comparingDouble(Movie::getUserRate).reversed());
        for(Movie movie:Movie.allmovies)
        {
            moviesTopRated.add(movie);
        }
    }

    @FXML
    public  void initialize() {
        getData();
        for (Movie movie : moviesTop) {
            addToGUI(movie);
        }
        for (Movie movie : moviesRecent) {
            if(movie.getRelease_date().getYear()>2018) {
                addToGUI2(movie);
            }
        }
        for (Movie movie : moviesTopRated) {
            addToGUI3(movie);
        }
        setUpFilter();
        onMouseEntered();
        onMouseExit();
        setupAutoScroll();
       /* profile.setOnMouseClicked(event -> profileOnMouseClicked());*/
        labelsOnMouseClicked();
        if(!WatchRecord.watchedMovies.isEmpty()) {
            counter.setText(WatchRecord.watchedMovies.size() + ")");
        }
        else {
            counter.setText("0"+")");
        }
        onSearch();
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
        TopMovies.getChildren().addAll(movieContainer);
        watchRecords.setOnMouseClicked(event -> onMouseClicked(movie));
    }

    private void addToGUI2(Movie movie) {
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
        RecentMovies.getChildren().addAll(movieContainer);

    }
    private void addToGUI3(Movie movie) {
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
        TopRatedMovies.getChildren().addAll(movieContainer);
    }
    public void setUpFilter(){
        Main.setStyle("-fx-background-color: #FFC107;" +
                " -fx-background-radius: 25; " +
                "-fx-border-color: white;" +
                " -fx-border-radius: 25;");
        Horror.setStyle("-fx-background-radius: 25;"+
                " -fx-background-color:  #565661;");
        All.setStyle("-fx-background-radius: 25;"+
                " -fx-background-color:  #565661;");
        Comedy.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;");
        Drama.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;");
        Action.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;");
        Horror.setOnMouseExited(event1 -> Horror.setStyle("-fx-background-radius: 25;-fx-background-color:  #565661;"));
        Comedy.setOnMouseExited(event1 -> Comedy.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;"));
        Drama.setOnMouseExited(event1 -> Drama.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;"));
        Action.setOnMouseExited(event1 -> Action.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;"));
        Main.setOnMouseExited(event1 -> Main.setStyle("-fx-background-color: #FFC107;" +
                " -fx-background-radius: 25;" +
                " -fx-border-color: white;" +
                " -fx-border-radius: 25;"));
    }
    public void onMouseClicked(Movie movie) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("watch-record.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        WatchRecord controller = loader.getController();
        controller.setStage(stage);
        controller.initializeItems();
        controller.aBoolean=true;
        Scene scene = new Scene(root);
        stage.setTitle("Movie");
        stage.setResizable(false);
        stage.setX(-7);
        stage.setY(0);
        stage.setScene(scene);
        stage.show();
    }
    public void labelsOnMouseClicked(){
        Action.setOnMouseClicked(event -> {
            Action.setStyle("-fx-background-radius: 25;-fx-background-color:  #FFC107;");
            Comedy.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;");
            Drama.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;");
            Horror.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;");
            All.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;");
            Main.setStyle("-fx-background-color: black;" +
                    " -fx-background-radius: 25; " +
                    "-fx-border-color: white;" +
                    " -fx-border-radius: 25;");
            All.setOnMouseExited(event1 -> All.setStyle("-fx-background-radius: 25;-fx-background-color:  #FFC107;"));
            Action.setOnMouseExited(event1 -> Action.setStyle("-fx-background-radius: 25;-fx-background-color:  #FFC107;"));
            Comedy.setOnMouseExited(event1 -> Comedy.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;"));
            Drama.setOnMouseExited(event1 -> Drama.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;"));
            Horror.setOnMouseExited(event1 -> Horror.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;"));
            Main.setOnMouseExited(event1 -> Main.setStyle("-fx-background-color: black;" +
                    " -fx-background-radius: 25;" +
                    " -fx-border-color: white;" +
                    " -fx-border-radius: 25;"));
            try {
                toActionMovies();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        Comedy.setOnMouseClicked(event -> {
            Comedy.setStyle("-fx-background-radius: 25;"+
                    " -fx-background-color:  #FFC107;");
            Action.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;");
            Drama.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;");
            Horror.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;");
            All.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;");
            Main.setStyle("-fx-background-color: black;" +
                    " -fx-background-radius: 25; " +
                    "-fx-border-color: white;" +
                    " -fx-border-radius: 25;");
            Comedy.setOnMouseExited(event1 -> Comedy.setStyle("-fx-background-radius: 25;-fx-background-color:  #FFC107;"));
            Action.setOnMouseExited(event1 -> Action.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;"));
            Drama.setOnMouseExited(event1 -> Drama.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;"));
            All.setOnMouseExited(event1 -> All.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;"));
            Horror.setOnMouseExited(event1 -> Horror.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;"));
            Main.setOnMouseExited(event1 -> Main.setStyle("-fx-background-color: black;" +
                    " -fx-background-radius: 25;" +
                    " -fx-border-color: white;" +
                    " -fx-border-radius: 25;"));
            try {
                toComedyMovies();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        Drama.setOnMouseClicked(event -> {
            Drama.setStyle("-fx-background-radius: 25;"+
                    " -fx-background-color:  #FFC107;");
            Comedy.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;");
            Action.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;");
            Horror.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;");
            All.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;");
            Main.setStyle("-fx-background-color: black;" +
                    " -fx-background-radius: 25; " +
                    "-fx-border-color: white;" +
                    " -fx-border-radius: 25;");
            Drama.setOnMouseExited(event1 -> Drama.setStyle("-fx-background-radius: 25;-fx-background-color:  #FFC107;"));
            Comedy.setOnMouseExited(event1 -> Comedy.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;"));
            Action.setOnMouseExited(event1 -> Action.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;"));
            Horror.setOnMouseExited(event1 -> Horror.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;"));
            All.setOnMouseExited(event1 -> All.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;"));
            Main.setOnMouseExited(event1 -> Main.setStyle("-fx-background-color: black;" +
                    " -fx-background-radius: 25;" +
                    " -fx-border-color: white;" +
                    " -fx-border-radius: 25;"));
            try {
                toDramaMovies();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        Horror.setOnMouseClicked(event -> {
            Horror.setStyle("-fx-background-radius: 25;"+
                    " -fx-background-color:  #FFC107;");
            Comedy.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;");
            Drama.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;");
            Action.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;");
            All.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;");
            Main.setStyle("-fx-background-color: black;" +
                    " -fx-background-radius: 25; " +
                    "-fx-border-color: white;" +
                    " -fx-border-radius: 25;");
            Horror.setOnMouseExited(event1 -> Horror.setStyle("-fx-background-radius: 25;-fx-background-color:  #FFC107;"));
            Comedy.setOnMouseExited(event1 -> Comedy.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;"));
            Drama.setOnMouseExited(event1 -> Drama.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;"));
            Action.setOnMouseExited(event1 -> Action.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;"));
            All.setOnMouseExited(event1 -> All.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;"));
            Main.setOnMouseExited(event1 -> Main.setStyle("-fx-background-color: black;" +
                    " -fx-background-radius: 25;" +
                    " -fx-border-color: white;" +
                    " -fx-border-radius: 25;"));
            try {
                toHorrorMovies();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });All.setOnMouseClicked(event -> {
            All.setStyle("-fx-background-radius: 25;"+
                    " -fx-background-color:  #FFC107;");
            Comedy.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;");
            Drama.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;");
            Action.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;");
           // All.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;");
            Main.setStyle("-fx-background-color: black;" +
                    " -fx-background-radius: 25; " +
                    "-fx-border-color: white;" +
                    " -fx-border-radius: 25;");
            Horror.setOnMouseExited(event1 -> Horror.setStyle("-fx-background-radius: 25;-fx-background-color:  #565661;"));
            Comedy.setOnMouseExited(event1 -> Comedy.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;"));
            Drama.setOnMouseExited(event1 -> Drama.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;"));
            Action.setOnMouseExited(event1 -> Action.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;"));
            All.setOnMouseExited(event1 -> All.setStyle("-fx-background-radius: 25; -fx-background-color:  #FFC107;"));
            Main.setOnMouseExited(event1 -> Main.setStyle("-fx-background-color: black;" +
                    " -fx-background-radius: 25;" +
                    " -fx-border-color: white;" +
                    " -fx-border-radius: 25;"));
            try {
                toAllMovies();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        Main.setOnMouseClicked(event -> {
            Main.setStyle("-fx-background-color: #FFC107;" +
                    " -fx-background-radius: 25; " +
                    "-fx-border-color: white;" +
                    " -fx-border-radius: 25;");
            Horror.setStyle("-fx-background-radius: 25;"+
                    " -fx-background-color:  #565661;");
            Comedy.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;");
            Drama.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;");
            Action.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;");
            All.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;");
            Horror.setOnMouseExited(event1 -> Horror.setStyle("-fx-background-radius: 25;-fx-background-color:  #565661;"));
            Comedy.setOnMouseExited(event1 -> Comedy.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;"));
            Drama.setOnMouseExited(event1 -> Drama.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;"));
            Action.setOnMouseExited(event1 -> Action.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;"));
            All.setOnMouseExited(event1 -> All.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;"));
            Main.setOnMouseExited(event1 -> Main.setStyle("-fx-background-color: #FFC107;" +
                    " -fx-background-radius: 25;" +
                    " -fx-border-color: white;" +
                    " -fx-border-radius: 25;"));
            mainPage();
        });
    }
    public void onMouseClickedVBox(MouseEvent act,Movie movie) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("movie-view.fxml"));
        root = loader.load();
        stage = (Stage)((Node)act.getSource()).getScene().getWindow();
        MovieController controller=loader.getController();
        controller.setStage(stage);
        controller.setMovie(movie);
        controller.watchMovie(movie);
        controller.page5=0;
        controller.setStars();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        movie5=movie;
        Image image = new Image(getClass().getResourceAsStream(movie.getPoster_path()));
        controller.refreshScreen("Watch Movie "+ movie.getTitle() + "("+movie.getRelease_date().getYear()+")", movie.getTitle(),
                movie.getTitle()+" Translated",movie.getGenres(), movie.getDescription(),
                movie.getRunning_time(), image,movie.getDirectorName(),movie.getCastNames());
        stage.setScene(scene);
        stage.show();

    }

    public void onMouseEntered() {
        onMouseEnteredLabels(Action);
        onMouseEnteredLabels(Comedy);
        onMouseEnteredLabels(Drama);
        onMouseEnteredLabels(All);
        onMouseEnteredLabels(Horror);
        Back.setOnMouseEntered(event -> Back.setStyle("-fx-background-color: #FFC107;" +
                " -fx-background-radius: 25; " +
                "-fx-border-color: white;" +
                " -fx-border-radius: 25;"));
        Main.setOnMouseEntered(event -> Main.setStyle("-fx-background-color: #FFC107;" +
                " -fx-background-radius: 25; " +
                "-fx-border-color: white;" +
                " -fx-border-radius: 25;"));
        Search.setOnMouseEntered(event -> Search.setStyle("-fx-background-color: #FFC107;" +
                " -fx-background-radius: 25;" +
                " -fx-border-color: white;" +
                " -fx-border-radius: 25;"));
        Recent.setOnMouseEntered(event -> underlineLabel(Recent, true));
        topMovies.setOnMouseEntered(event -> underlineLabel(topMovies, true));
        TopRated.setOnMouseEntered(event -> underlineLabel(TopRated, true));
        watchRecords.setOnMouseEntered(event -> {
            watchRecords.setStyle("-fx-background-color: #FFC107; -fx-background-radius: 10; -fx-text-fill: black;");
            counter.setStyle("-fx-background-color: #FFC107; -fx-background-radius: 10; -fx-text-fill: black;");
        });

    }

    public void onMouseExit() {
        // Handle mouse exit event
        onMouseExitLabels(Action);
        onMouseExitLabels(Comedy);
        onMouseExitLabels(Drama);
        onMouseExitLabels(Horror);
        onMouseExitLabels(All);
        Back.setOnMouseExited(event -> Back.setStyle("-fx-background-color: black;" +
                " -fx-background-radius: 25;" +
                " -fx-border-color: white;" +
                " -fx-border-radius: 25;"));
        Main.setOnMouseExited(event -> Main.setStyle("-fx-background-color: #FFC107;" +
                " -fx-background-radius: 25;" +
                " -fx-border-color: white;" +
                " -fx-border-radius: 25;"));
        Search.setOnMouseExited(event -> Search.setStyle("-fx-background-color: black;" +
                " -fx-background-radius: 25;" +
                " -fx-border-color: white;" +
                " -fx-border-radius: 25;"));
        Recent.setOnMouseExited(event -> underlineLabel(Recent, false));
        topMovies.setOnMouseExited(event -> underlineLabel(topMovies, false));
        TopRated.setOnMouseExited(event -> underlineLabel(TopRated, false));
        watchRecords.setOnMouseExited(event -> {
            watchRecords.setStyle("-fx-background-color: #001f2f; -fx-background-radius: 10;");
            counter.setStyle("-fx-background-color: #001f2f; -fx-background-radius: 10;");
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
        double scrollDuration = 60; // Adjust the duration of the auto-scrolling

        // Create a timeline for the auto-scrolling animation
        autoScrollTimeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(Scroll1.hvalueProperty(), 0)),
                new KeyFrame(Duration.seconds(scrollDuration), new KeyValue(Scroll1.hvalueProperty(), 1))
        );
        autoScrollTimeline2 = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(Scroll2.hvalueProperty(), 0)),
                new KeyFrame(Duration.seconds(scrollDuration), new KeyValue(Scroll2.hvalueProperty(), 1))
        );
        autoScrollTimeline3 = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(Scroll3.hvalueProperty(), 0)),
                new KeyFrame(Duration.seconds(scrollDuration), new KeyValue(Scroll3.hvalueProperty(), 1))
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
        //////////////////////////////////////////////////////
        autoScrollTimeline3.setCycleCount(Timeline.INDEFINITE);
        startAutoScroll3();
        Scroll3.setOnMouseEntered(event ->  stopAutoScroll3());
        Scroll3.setOnMouseExited(event -> startAutoScroll3());
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
    @FXML
    private void startAutoScroll3() {
        autoScrollTimeline3.play();
    }

    @FXML
    private void stopAutoScroll3() {
        // Stop auto-scrolling when user interacts with the ScrollPane
        autoScrollTimeline3.pause();
    }

    /////////////////////////////
    public void handleKeyPressed(KeyEvent event) {
        if (event.getCode().toString().equals("ESCAPE")) {
            Platform.exit();
        }
    }
    private void switchPane(Pane pane){
        mainPane.setCenter(pane);
    }
    ///////////////////////////

    private void mainPage(){
        switchPane(this.AllMovies);
    }

    private void toActionMovies() throws IOException {
        moviePage = 1;
        Parent fxml = FXMLLoader.load(getClass().getResource("filter-page.fxml"));
        switchPane((Pane)fxml);
    }

    private void toComedyMovies() throws IOException{
        moviePage = 2;
        Parent fxml = FXMLLoader.load(getClass().getResource("filter-page.fxml"));
        switchPane((Pane)fxml);
    }

    private void toDramaMovies() throws IOException{
        moviePage = 3;
        Parent fxml = FXMLLoader.load(getClass().getResource("filter-page.fxml"));
        switchPane((Pane)fxml);
    }

    private void toHorrorMovies() throws IOException{
        moviePage = 4;
        Parent fxml = FXMLLoader.load(getClass().getResource("filter-page.fxml"));
        switchPane((Pane)fxml);
    }
    private void toAllMovies() throws IOException{
        moviePage = 5;
        Parent fxml = FXMLLoader.load(getClass().getResource("filter-page.fxml"));
        switchPane((Pane)fxml);
    }
    @FXML
    private void handleMenuItemClick(ActionEvent event) {
        MenuItem selectedItem = (MenuItem) event.getSource();
        String selectedValue = selectedItem.getText();

        // Do something with the selected value, e.g., set it as the text of the MenuButton
        menuButton.setText(selectedValue);
        menuValue=menuButton.getText();
        // You can also perform other actions based on the selected value
        // For example, show/hide different UI elements or update other parts of your application
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    private void goToProgilePage (MouseEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("profile-page.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        profilePageController controller=loader.getController();
        controller.setStage(stage);
        controller.setdata();
        controller.setUser(user);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void backScenes(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("sign-in.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        SignIn controller=loader.getController();
        controller.setStage(stage);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void getSearchText(ActionEvent event) throws IOException {
    }
    public void setUser(User user){
        this.user = user;
       // Admin.getUserMovieLists(user);
    }
    private void onSearch(){
        Search.setOnMouseClicked(event -> {
            int found=0;
            String cast = "";
            String director="";
            searchViewController.searchMovies=new ArrayList<>();
            for(Movie movie1:Movie.allmovies){
                if(menuValue!=null) {
                    if (movie1.getTitle().equalsIgnoreCase(searchItem.getText()) && menuValue.equals("Movie")) {
                        movie = movie1;
                        found = 1;
                    }
                    else if(movie1.getDirectorName().equalsIgnoreCase(searchItem.getText()) && menuValue.equals("Director")){
                        director=movie1.getDirectorName();
                        searchViewController.searchMovies.add(movie1);
                        found=2;
                    }
                    else{
                        for (String cast1 : movie1.getCastNames()) {
                            if(searchItem.getText().equalsIgnoreCase(cast1) && menuValue.equals("Cast")){
                                cast=cast1;
                                found=3;
                                searchViewController.searchMovies.add(movie1);
                            }
                        }
                    }
                }
            }
            int finalFound=found;
            if(finalFound==1) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("movie-view.fxml"));
                try {
                    root = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                MovieController controller = loader.getController();
                controller.setStage(stage);
                controller.watchMovie(movie);
                controller.page5 = 0;
                scene = new Scene(root);
                Image image = new Image(getClass().getResourceAsStream(movie.getPoster_path()));
                controller.refreshScreen("Watch Movie "+ movie.getTitle() + "("+movie.getRelease_date().getYear()+")", movie.getTitle(),
                        movie.getTitle()+" Translated",movie.getGenres(), movie.getDescription(),
                        movie.getRunning_time(), image,movie.getDirectorName(),movie.getCastNames());
                stage.setScene(scene);
                stage.show();
            }
            else if(finalFound==2){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("search-view.fxml"));
                try {
                    root = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                searchViewController controller = loader.getController();
                controller.setStage(stage);
                controller.Search(director,menuValue);
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else if(finalFound==3){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("search-view.fxml"));
                try {
                    root = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                searchViewController controller = loader.getController();
                controller.setStage(stage);
                controller.Search(cast,menuValue);
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else {
                searchItem.clear();
            }
        });

    }
}
