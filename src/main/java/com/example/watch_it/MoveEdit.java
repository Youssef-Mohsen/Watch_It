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
import java.util.Objects;
public class MoveEdit {
    @FXML
    private TextField TitleEdit;
    @FXML
    private TextField FilmEdit;
    @FXML
    private TextField FilmNameEdit;
    @FXML
    private TextField DurationEdit;
    @FXML
    private TextField GenreEdit;
    @FXML
    private TextField DescriptionEdit;
    @FXML
    private ImageView ImageEdit;



    public void setUpPromptText(String titlePrompt,String filmNamePrompt,String filmPrompt,
                                String durationPrompt,String genrePrompt,String descriptionPrompt,Image image){
        TitleEdit.setPromptText(titlePrompt);
        FilmNameEdit.setPromptText(filmNamePrompt);
        FilmEdit.setPromptText(filmPrompt);
        DurationEdit.setPromptText(durationPrompt);
        GenreEdit.setPromptText(genrePrompt);
        DescriptionEdit.setPromptText(descriptionPrompt);
        ImageEdit.setImage(image);
    }
    public void updateText(Label label1,Label label2,Label label3,
                           Label label4,Label label5,Label label6){
        label1.setText(TitleEdit.getText());
        label2.setText(FilmNameEdit.getText());
        label3.setText(FilmEdit.getText());
        label4.setText(DurationEdit.getText());
        label5.setText(GenreEdit.getText());
        label6.setText(DescriptionEdit.getText());
    }
    public void update(){

    }


}
