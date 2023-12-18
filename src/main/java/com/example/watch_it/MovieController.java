package com.example.watch_it;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    private Label Director;
    @FXML
    private Label Cast;
    @FXML
    private ImageView imagePreview;
    @FXML
    private Button Back;
    @FXML
    public Button Watch;
    @FXML
    public Button watchLater;
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
    private UserWatchRecord movie_watched;
    public int page5=0;
    private User user;
    double Max_Rating;
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    // Other fields and methods...

    // Method to update the text of the first label

    @FXML
    private void initialize() {
        stars = new ImageView[]{star1, star2, star3, star4, star5};
        // Set empty star images
        for (ImageView star : stars) {
            star.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/emptyStar.png"))));
        }

        watchMovie(movie);
        onMouseEntered();
        onMouseExit();
        alert.setTitle("");
        alert.setHeaderText(null);
        alert.setContentText("You Reached Your Limit");
    }
    public void disableButtons(){
        watchLater.setDisable(true);
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
                stars[finalI1].setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/fullStar.png"))));
                for (int j = 0; j < rating2 + (4 - rating2); j++) {
                    int finalJ = j;
                    stars[j].setOnMouseExited(event2 ->
                            stars[finalJ].setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/fullStar.png")))));
                }
                for (int h = rating2 + (4 - rating2); h > rating2; h--) {
                    int finalJ = h;
                    stars[h].setOnMouseExited(event2 ->
                            stars[finalJ].setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/emptyStar.png")))));
                }
                Max_Rating=rating2;
                movie.userRate=rating2;
                movie.setImdb_score(movie.getImdb_score()+((float) movie.userRate /Admin.getAllUsers().size()));
                for (int p = movie.userRate+(4-movie.userRate); p >= movie.userRate; p--){
                    int finalJ = p;
                    stars[finalJ].setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/emptyStar.png"))));
                    stars[p].setOnMouseExited(event2 ->
                            stars[finalJ].setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/emptyStar.png")))));
                }
                for (int j = 0; j <  movie.userRate; j++) {
                    int finalJ = j;
                    stars[j].setOnMouseExited(event2 ->
                            stars[finalJ].setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/fullStar.png")))));
                }
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
    private void resetStars() {
        for (ImageView star : stars) {
            star.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/emptyStar.png"))));
        }

    }
    public void refreshScreen(String filmTitle, String newMovieName, String newFilm, ArrayList<String> filmGenre,
                              String filmDescription, String filmDuration, Image image,String director,ArrayList<String> cast) {
        String genres = "";
        for(String genre: filmGenre){
            genres = genres.concat(genre+ " ");
        }
        String casts = "";
        for (String cast1 : cast) {
            casts = casts.concat(cast1 + ",");
        }
        titleMovie.setText(filmTitle);
        movieName.setText(newMovieName);
        Film.setText(newFilm);
        Genre.setText(genres);
        description.setText(filmDescription);
        duration.setText(filmDuration);
        imagePreview.setImage(image);
        Director.setText(director);
        Cast.setText(casts);
    }

    public void disableWatch(){
        Watch.setOnMouseClicked(event -> Watch.setDisable(true));
    }
    public void watchMovie(Movie movie){
        if(WatchRecord.watchedMovies.contains(movie)){
            watchLater.setDisable(true);
        }
        if(RecordedMoviesController.toWatchMovies.contains(movie)){
            watchLater.setDisable(true);
        }
        Watch.setOnMouseClicked(event -> {
            watchLater.setDisable(true);
            user.getSubscription().setMoviesWatched(user.getSubscription().getMoviesWatched()+1);
            if (!WatchRecord.watchedMovies.contains(movie)) {
                if(user.getPlan().equals("basic") && WatchRecord.watchedMovies.size()<5) {
                    WatchRecord.watchedMovies.add(movie);
                    if (RecordedMoviesController.toWatchMovies.contains(movie)) {
                        RecordedMoviesController.toWatchMovies.remove(movie);
                    }
                }
                else if(user.getPlan().equals("standard") && WatchRecord.watchedMovies.size()<10){

                    WatchRecord.watchedMovies.add(movie);
                    if (RecordedMoviesController.toWatchMovies.contains(movie)) {
                        RecordedMoviesController.toWatchMovies.remove(movie);
                    }

                }
                else if(user.getPlan().equals("premium") && WatchRecord.watchedMovies.size()<30){

                    WatchRecord.watchedMovies.add(movie);
                    if (RecordedMoviesController.toWatchMovies.contains(movie)) {
                        RecordedMoviesController.toWatchMovies.remove(movie);
                    }

                }
                else {
                    alert.showAndWait();
                }
            }

            Watch.setDisable(true);
        });
        watchLater.setOnMouseClicked(event -> {
            MainPageController.movie5=movie;
            if(RecordedMoviesController.toWatchMovies.contains(movie)) {
                System.out.println("That Movie here");
            }
            else {
                RecordedMoviesController.toWatchMovies.add(movie);
            }
            watchLater.setDisable(true);
        });

    }
    public void watchMovie(UserWatchRecord movie){
        if(SignIn.user5.Watched_Movies.contains(movie)){
            watchLater.setDisable(true);
        }
        if(SignIn.user5.Movies_For_Later.contains(movie.getMovie())){
            watchLater.setDisable(true);
        }
        Watch.setOnMouseClicked(event -> {
            watchLater.setDisable(true);
            movie.setRating(Max_Rating);
            SignIn.user5.getSubscription().setMoviesWatched(SignIn.user5.getSubscription().getMoviesWatched()+1);
            if (!SignIn.user5.Watched_Movies.contains(movie)) {
                if( SignIn.user5.getPlan().equals("basic") && SignIn.user5.Watched_Movies.size()<5) {
                    SignIn.user5.Watched_Movies.add(movie);
                    if (SignIn.user5.Movies_For_Later.contains(movie.getMovie())) {
                        SignIn.user5.Movies_For_Later.remove(movie.getMovie());
                    }
                }
                else if( SignIn.user5.getPlan().equals("standard") &&SignIn.user5.Watched_Movies.size()<10){
                    SignIn.user5.Watched_Movies.add(movie);
                    if ( SignIn.user5.Movies_For_Later.contains(movie.getMovie())) {
                        SignIn.user5.Movies_For_Later.remove(movie.getMovie());
                    }

                }
                else if( SignIn.user5.getPlan().equals("premium") && SignIn.user5.Watched_Movies.size()<30){

                    SignIn.user5.Watched_Movies.add(movie);
                    if ( SignIn.user5.Movies_For_Later.contains(movie.getMovie())) {
                        SignIn.user5.Movies_For_Later.remove(movie.getMovie());
                    }
                }
                else {
                    alert.showAndWait();
                }
            }
            Watch.setDisable(true);
        });
        watchLater.setOnMouseClicked(event -> {
            MainPageController.movie5_watched=movie;
            if(!SignIn.user5.Movies_For_Later.contains(movie.getMovie()))
                SignIn.user5.Movies_For_Later.add(movie.getMovie());
            watchLater.setDisable(true);
        });

    }


    public void backScenes(ActionEvent event) throws IOException {
        Parent root = null;
        if(page5==0) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("main-page.fxml"));
            root = loader.load();
            MainPageController controller = loader.getController();
            controller.setStage(stage);
            controller.setUser(SignIn.user5);
        }
        else if(page5==1){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("watch-record.fxml"));
            root = loader.load();
            WatchRecord controller = loader.getController();
            controller.setStage(stage);
            controller.initializeItems();
        }
        else {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("profile-page.fxml"));
            root = loader.load();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            profilePageController controller=loader.getController();
            controller.setStage(stage);
            controller.setdata();
            controller.setUser(SignIn.user5);
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Movie");
        stage.setResizable(false);
        stage.setX(-7);
        stage.setY(0);
        stage.setScene(scene);
        stage.show();
    }
    public void setStars(){

        if(movie!=null || WatchRecord.watchedMovies.contains(movie)) {
            for (int i = 0; i < movie.userRate; i++) {
                stars[i].setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/fullStar.png"))));
            }
            for (int j = 0; j < movie.userRate; j++){
                int finalJ = j;
                stars[j].setOnMouseExited(event2 ->
                        stars[finalJ].setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/fullStar.png")))));
            }
            for (int h = movie.userRate; h > movie.userRate+(5-movie.userRate); h++){
                int finalJ = h;
                stars[h].setOnMouseExited(event2 ->
                        stars[finalJ].setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/emptyStar.png")))));
            }

        }
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
    public void setMovie(UserWatchRecord movie){
        this.movie_watched=movie;
    }
    public void setUser(User user){
        this.user = user;
    }

}