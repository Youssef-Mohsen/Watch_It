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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


public class MovieController {
    private Stage stage;
    @FXML
    private Label titleMovie;
    @FXML
    public Pane pane;
    @FXML
    private HBox starsRow;
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
    static UserWatchRecord movie_watched;
    public int page5=0;
    private User user;
    double Max_Rating;
    private boolean isAdmin = false;
    private Parent root;
    private Scene scene;

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
        onMouseEntered();
        onMouseExit();
        alert.setTitle("");
        alert.setHeaderText(null);
        alert.setContentText("You Reached Your Limit");
    }
    public void disableButtons(){
        watchLater.setDisable(true);
    }

    public void handleStarClick(MouseEvent event) {
        ImageView clickedStar = (ImageView) event.getSource();
        int rating = Integer.parseInt(clickedStar.getId().substring(4)); // Extract the rating from the star's ID
        for (int i = 0; i < rating; i++) {
            int finalI1 = i;
            stars[i].setOnMouseClicked(event1 -> {
                ImageView clickedStar2 = (ImageView) event1.getSource();
                int rating2 = Integer.parseInt(clickedStar2.getId().substring(4)); // Extract the rating from the star's ID
                stars[finalI1].setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/fullStar.png"))));
                Max_Rating=rating2;
                movie.UpdateRating((double)rating2);
                if(movie_watched != null) {
                    movie_watched.setRating(Max_Rating);
                    movie_watched.getMovie().UpdateRating(Max_Rating);
                }

              /*  for (double p = movie_watched.getRating()+(4- movie_watched.getRating()); p >=  movie_watched.getRating(); p--){
                    double finalJ = p;
                    stars[(int) finalJ].setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/emptyStar.png"))));
                    stars[(int) p].setOnMouseExited(event2 ->
                            stars[(int) finalJ].setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/emptyStar.png")))));
                }
                for (int j = 0; j <   movie_watched.getRating(); j++) {
                    int finalJ = j;
                    stars[j].setOnMouseExited(event2 ->
                            stars[finalJ].setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/fullStar.png")))));
                }*/
                for(int k=0;k< Max_Rating;k++){
                    stars[k].setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/fullStar.png"))));
                }
                for(double l = Max_Rating; l<5;l++){
                    stars[(int) l].setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/emptyStar.png"))));
                }
            });

        }
        // You can perform additional actions here, such as saving the rating to a database.
    }

    public void handleStarHover(MouseEvent event) {
        for(int i=0;i<5;i++) {
            stars[i].setOnMouseEntered(event1 -> {
                ImageView hoveredStar = (ImageView) event.getSource();
                int rating = Integer.parseInt(hoveredStar.getId().substring(4)); // Extract the rating from the star's ID
                // Highlight stars

                for (int j = 0; j < rating; j++) {
                    stars[j].setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/fullStar.png"))));
                }
            });
        }
    }

    @FXML
    public void resetStars() {
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
    public void watchMovie(Movie movie1){
        for (UserWatchRecord userWatchRecord:SignIn.user5.Watched_Movies){
            if(userWatchRecord.getMovie().equals(movie)){
                watchLater.setDisable(true);
            }
        }
        if(SignIn.user5.Movies_For_Later.contains(movie)){
            watchLater.setDisable(true);
        }
        Watch.setOnMouseClicked(event -> {
            boolean exists=false;
            watchLater.setDisable(true);
            Watch.setDisable(true);
            movie_watched = new UserWatchRecord(movie,Max_Rating);
            SignIn.user5.getSubscription().setMoviesWatched(SignIn.user5.getSubscription().getMoviesWatched()+1);
            for (UserWatchRecord userWatchRecord:SignIn.user5.Watched_Movies){
                if(userWatchRecord.getMovie().equals(movie)){
                    exists=true;
                }
            }
                    if(!exists) {
                        if (SignIn.user5.getPlan().equals("basic") && SignIn.user5.Watched_Movies.size() < 5) {
                            SignIn.user5.Watched_Movies.add(movie_watched);
                            SignIn.user5.Movies_For_Later.remove(movie);
                            movie.inc_views();
                        } else if (SignIn.user5.getPlan().equals("standard") && SignIn.user5.Watched_Movies.size() < 10) {
                            movie.inc_views();
                            SignIn.user5.Watched_Movies.add(movie_watched);
                            if (SignIn.user5.Movies_For_Later.contains(movie)) {
                                SignIn.user5.Movies_For_Later.remove(movie);

                            }

                        } else if (SignIn.user5.getPlan().equals("premium") && SignIn.user5.Watched_Movies.size() < 30) {
                            movie.inc_views();
                            SignIn.user5.Watched_Movies.add(movie_watched);
                            if (SignIn.user5.Movies_For_Later.contains(movie)) {
                                SignIn.user5.Movies_For_Later.remove(movie);

                            }
                        } else {
                            alert.showAndWait();
                        }
                    }
        });
        watchLater.setOnMouseClicked(event -> {
            MainPageController.movie5=movie;
            if(!SignIn.user5.Movies_For_Later.contains(movie)) {
                SignIn.user5.Movies_For_Later.add(movie);
            }
            watchLater.setDisable(true);
        });

    }

    public void Admin(Movie movie){
        isAdmin = true;
        watchLater.setVisible(false);
        Watch.setVisible(false);
        starsRow.setVisible(false);
        Button edit = new Button("Edit");
        edit.setStyle("-fx-background-color: black;-fx-background-radius: 25; -fx-border-color: white; -fx-border-radius: 25;");
        edit.setPrefWidth(73.0);
        edit.setPrefHeight(30.0);
        edit.setLayoutX(1443.0);
        edit.setLayoutY(770.0);
        edit.setTextFill(Color.WHITE);
        pane.getChildren().add(edit);
        edit.setOnMouseEntered(event -> edit.setStyle("-fx-background-color: #FFC107; -fx-background-radius: 25; -fx-border-color: white; -fx-border-radius: 25;"));
        edit.setOnMouseExited(event -> edit.setStyle("-fx-background-color: black; -fx-background-radius: 25; -fx-border-color: white; -fx-border-radius: 25;"));
        edit.setOnMouseClicked(event -> {
            try {
                ToEditMovie(event, movie);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    private void ToEditMovie(MouseEvent event, Movie movie) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("movie-edit.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        MoveEdit controller=loader.getController();
        controller.setStage(stage);
        controller.setUpPromptText(movie);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setScene(scene);
        stage.show();
    }
    public void backScenes(ActionEvent event) throws IOException {
        Parent root = null;

        if (isAdmin || page5 == 8) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("all-movies.fxml"));
            root = loader.load();
            AllMoviesController controller = loader.getController();
            controller.setStage(stage);
        }
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
    public void setStars(UserWatchRecord userWatchRecord){

            for (int i = 0; i < userWatchRecord.getRating(); i++) {
                stars[i].setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/fullStar.png"))));
            }
            for (int j = 0; j <  userWatchRecord.getRating(); j++){
                int finalJ = j;
                stars[j].setOnMouseExited(event2 ->
                        stars[finalJ].setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/fullStar.png")))));
            }
            for (double h =  userWatchRecord.getRating(); h >  userWatchRecord.getRating()+(5- userWatchRecord.getRating()); h++){
                double finalJ = h;
                stars[(int) h].setOnMouseExited(event2 ->
                        stars[(int) finalJ].setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/emptyStar.png")))));
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