package com.example.watch_it;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

import java.util.ArrayList;

public class searchViewController {
    @FXML
    private HBox Movies;
    @FXML
    private Label Name_Label;
    @FXML
    private Label Age_Label;
    @FXML
    private Label Gender_Label;
    @FXML
    private Label Nation_Label;
    @FXML
    private Label Not_Found_Label;
    @FXML
    private Label data_Label;
    private Stage stage;
    private Parent root;
    public static final ArrayList<Movie> searchMovies=new ArrayList<Movie>();
    @FXML
    public void initialize(){
        for (Movie movie:searchMovies) {
            add_movie(movie);
        }
        Movies.setSpacing(20);
    }

    private void add_movie(Movie movie){
        Image image = new Image(getClass().getResourceAsStream(movie.getPoster_path()));
        ImageView imageView=new ImageView(image);
        imageView.setFitHeight(237);
        imageView.setFitWidth(220);
        Label name=new Label(movie.getTitle());
        name.setFont(Font.font(16));
        name.setStyle("-fx-text-size: 20; -fx-text-fill: white;");
        VBox show=new VBox(10);
        show.getChildren().addAll(imageView,name);
        show.setAlignment(Pos.CENTER);
        Movies.getChildren().add(show);
    }
    protected void Search(String search_name,String type){
        String[] arr = search_name.split(" ");
        String first = arr[0], second = arr[1];
        if(type.equals("Director")) {
            Director d = Director.Search_director(first, second);
            data_Label.setText("Director");
            if (d == null)
                Not_Found_Label.setText("Not Found !!!");
            else {
                Name_Label.setText(d.getFirst_Name() + " " + d.getSecond_Name());
                Age_Label.setText(Integer.toHexString(d.getAge()));
                Gender_Label.setText(d.getGender());
                Nation_Label.setText(d.getNationality());
            }
        }
        else if(type.equals("Cast")) {
            Cast c = Cast.Search_Cast(first, second);
            data_Label.setText("Cast");
            if(c==null)
                Not_Found_Label.setText("Not Found !!!");
            else{
                Name_Label.setText(c.getFirst_Name() + " " + c.getSecond_Name());
                Age_Label.setText(Integer.toHexString(c.getAge()));
                Gender_Label.setText(c.getGender());
                Nation_Label.setText(c.getNationality());
            }
        }
    }
    @FXML
    private void backScenes(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-page.fxml"));
        root = loader.load();
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

    public void setStage(Stage stage){
        this.stage = stage;
    }
}
