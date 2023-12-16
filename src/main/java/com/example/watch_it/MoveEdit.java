package com.example.watch_it;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
public class MoveEdit {
    @FXML
    private Label titleMovie;
    @FXML
    private TextField FilmEdit;
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
        // ImdbEdit.setPromptText(movie.getImdb_score());
    }
    public void Update(){
        String revenue = RevenueEdit.getText();
        String genres = GenreEdit.getText();
        String descritption = DescriptionEdit.getText();
        // get imdb
        if (!revenue.isEmpty())
            movie.setRevenue(revenue);
        if (!genres.isEmpty())
            //  movie.setGenre(genres);
            if (!descritption.isEmpty())
                movie.setDescription(descritption);


    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}