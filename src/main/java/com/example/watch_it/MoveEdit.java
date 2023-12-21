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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
public class MoveEdit {
    @FXML
    private Button Back;
    @FXML
    private Label titleMovie;
    @FXML
    private TextField GenreEdit;
    @FXML
    private TextField DescriptionEdit;
    @FXML
    private TextField ImdbEdit;
    @FXML
    private TextField RevenueEdit;
    @FXML
    private ImageView ImageEdit;
    private Stage stage;
    private Movie movie;
    private Parent root;
    private Scene scene;


    public void setUpPromptText(Movie movie){
        this.movie = movie;
        String genres = "";
        for(String genre: movie.getGenres()){
            genres = genres.concat(genre+ " ");
        }
        titleMovie.setText(movie.getTitle());
        RevenueEdit.setPromptText(movie.getRevenue());
        GenreEdit.setPromptText(genres);
        DescriptionEdit.setPromptText(movie.getDescription());
        Image image = new Image(getClass().getResourceAsStream(movie.getPoster_path()));
        ImageEdit.setImage(image);
        ImdbEdit.setPromptText(String.valueOf(movie.getImdb_score()));
    }
    @FXML
    public void Update(ActionEvent event) throws IOException {
        String revenue = RevenueEdit.getText();
        String genres = GenreEdit.getText();
        String descritption = DescriptionEdit.getText();
        String imdb = ImdbEdit.getText();

        if (!revenue.isEmpty())
            movie.setRevenue(revenue);
        if (!genres.isEmpty()) {
            String[] genre = genres.split(",");
            ArrayList<String> genrearray = new ArrayList<>(Arrays.asList(genre));
            movie.setGenre(genrearray);
        }
        if (!descritption.isEmpty())
            movie.setDescription(descritption);
        if (!imdb.isEmpty())
            movie.setImdb_score(Float.parseFloat(imdb));

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("");
        alert.setHeaderText(null);
        alert.setContentText("Movie Updated");
        alert.showAndWait();
        backScenes(event);


    }
    public void onMouseEntered() {
        Back.setOnMouseEntered(event -> Back.setStyle("-fx-background-color: #FFC107; -fx-background-radius: 25; -fx-border-color: white; -fx-border-radius: 25;"));
    }

    public void onMouseExit() {
        Back.setOnMouseExited(event -> Back.setStyle("-fx-background-color: black; -fx-background-radius: 25; -fx-border-color: white; -fx-border-radius: 25;"));
    }
    @FXML
    public void backScenes(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("movie-view.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        MovieController controller=loader.getController();
        controller.setStage(stage);
        controller.Admin(movie);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Image image = new Image(getClass().getResourceAsStream(movie.getPoster_path()));
        controller.refreshScreen("Watch Movie "+ movie.getTitle() + "("+movie.getRelease_date().getYear()+")", movie.getTitle(),
                movie.getTitle()+" Translated",movie.getGenres(), movie.getDescription(),
                movie.getRunning_time(), image,movie.getDirector().getFirst_Name()+" "+movie.getDirector().getSecond_Name(),movie.getCastNames());
        stage.setScene(scene);
        stage.show();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}