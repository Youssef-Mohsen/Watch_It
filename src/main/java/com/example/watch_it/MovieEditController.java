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
public class MovieEditController     {
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


    public void setUpPromptText(Movie movie){
        String genres = "";
        for(String genre: movie.getGenres()){
            genres = genres.concat(genre+ " ");
        }
        titleMovie.setText(movie.getTitle());
        //   FilmEdit.setPromptText(Film.getText());
        // RevenueEdit.setPromptText();
        // ImdbEdit.setPromptText();
        GenreEdit.setPromptText(genres);
        DescriptionEdit.setPromptText(movie.getDescription());
        Image image = new Image(getClass().getResourceAsStream(movie.getPoster_path()));
        ImageEdit.setImage(image);
    }
    /* public void updateText(Label label1,Label label2,Label label3,
                            Label label4,Label label5,Label label6){
         label1.setText(TitleEdit.getText());
       //  label2.setText(RevenueEdit.getText());
         label3.setText(FilmEdit.getText());
      //   label4.setText(ImdbEdit.getText());
         label5.setText(GenreEdit.getText());
         label6.setText(DescriptionEdit.getText());
     }*/
    public void update(){

    }


    public void setStage(Stage stage) {
        this.stage = stage;
    }
}