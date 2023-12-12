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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


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
    @FXML
    private Button Watch;
    @FXML
    private Button watchLater;
    @FXML
    private ImageView star1;

    @FXML
    private ImageView star2;

    @FXML
    private ImageView star3;

    @FXML
    private ImageView star4;

    @FXML
    private ImageView star5;

    private ImageView[] stars;

    private Movie movie;
    private  boolean clicked =true;
    // Other fields and methods...

    // Method to update the text of the first label


    public void initialize() {
        stars = new ImageView[]{star1, star2, star3, star4, star5};
        // Set empty star images
        for (ImageView star : stars) {
            star.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/emptyStar.png"))));
        }
        watchMovie(movie);
        onMouseEntered();
        onMouseExit();
    }
    @FXML
    private boolean handleStarClick(MouseEvent event) {
        ImageView clickedStar = (ImageView) event.getSource();
        int rating = Integer.parseInt(clickedStar.getId().substring(4)); // Extract the rating from the star's ID
        for (int i = 0; i < rating; i++) {
            int finalI1 = i;
            stars[i].setOnMouseClicked(event1 -> {
                ImageView clickedStar2 = (ImageView) event1.getSource();
                int rating2 = Integer.parseInt(clickedStar2.getId().substring(4)); // Extract the rating from the star's ID
                System.out.println("Rated: " + rating2 + " stars");
                stars[finalI1].setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/fullStar.png"))));
                for (int j = 0; j < rating2+(5-rating2); j++){
                    int finalJ = j;
                    stars[j].setOnMouseExited(event2 ->
                            stars[finalJ].setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/fullStar.png")))));
                    }
                for (int h = rating2+(4-rating2); h >= rating2; h--){
                    int finalJ = h;
                    stars[h].setOnMouseExited(event2 ->
                            stars[finalJ].setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/emptyStar.png")))));
                }
                int Max_Rating=Math.max(rating2,rating);
                System.out.println(Max_Rating);
            });

        }
        // You can perform additional actions here, such as saving the rating to a database.
        return false;
    }

    @FXML
    private void handleStarHover(MouseEvent event) {
        ImageView hoveredStar = (ImageView) event.getSource();
        int rating = Integer.parseInt(hoveredStar.getId().substring(4)); // Extract the rating from the star's ID
        // Highlight stars

            for (int i = 0; i < rating; i++) {
                stars[i].setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/fullStar.png"))));
            }

    }

    @FXML
    private void resetStars(MouseEvent event) {
        if(!handleStarClick(event)) {
            for (ImageView star : stars) {
                star.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/emptyStar.png"))));
            }
        }
    }
    public void refreshScreen(String filmTitle, String newMovieName, String newFilm, String filmGenre,
                              String filmDescription, String filmDuration, Image image) {
        titleMovie.setText(filmTitle);
        movieName.setText(newMovieName);
        Film.setText(newFilm);
        Genre.setText(filmGenre);
        description.setText(filmDescription);
        duration.setText(filmDuration);
        imagePreview.setImage(image);

    }

    public void refreshLabels(String filmTitle, String newMovieName, String newFilm, String filmGenre,
                              String filmDescription, String filmDuration) {
        titleMovie.setText(filmTitle);
        movieName.setText(newMovieName);
        Film.setText(newFilm);
        Genre.setText(filmGenre);
        description.setText(filmDescription);
        duration.setText(filmDuration);
    }

    public void watchMovie(Movie movie){

            Watch.setOnMouseClicked(event -> {
                WatchRecord.watchedMovies.add(movie);
            });

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

    public void onMouseEntered() {
        Back.setOnMouseEntered(event -> Back.setStyle("-fx-background-color: #FFC107; -fx-background-radius: 25; -fx-border-color: white; -fx-border-radius: 25;"));
        Watch.setOnMouseEntered(event -> Watch.setStyle("-fx-background-color: #FFC107; -fx-background-radius: 25; -fx-border-color: white; -fx-border-radius: 25;"));
        watchLater.setOnMouseEntered(event -> watchLater.setStyle("-fx-background-color: #FFC107; -fx-background-radius: 25; -fx-border-color: white; -fx-border-radius: 25;"));
    }

    public void onMouseExit() {
        Back.setOnMouseExited(event -> Back.setStyle("-fx-background-color: black; -fx-background-radius: 25; -fx-border-color: white; -fx-border-radius: 25;"));
        Watch.setOnMouseExited(event -> Watch.setStyle("-fx-background-color: black; -fx-background-radius: 25; -fx-border-color: white; -fx-border-radius: 25;"));
        watchLater.setOnMouseExited(event -> watchLater.setStyle("-fx-background-color: black; -fx-background-radius: 25; -fx-border-color: white; -fx-border-radius: 25;"));
    }
    //////////////////////////

    //////////////////////////
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void setMovie(Movie movie){
        this.movie=movie;
    }

}