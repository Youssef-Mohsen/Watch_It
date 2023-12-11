package com.example.watch_it;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

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
    private Label Movies_Label;
    @FXML
    private Label Not_Found_Label;
    @FXML
    private Label data_Label;
    private Stage stage;
    @FXML
    public void initialize(String movie_name){
        for (Movie m:Admin.movies_obj) {
            if(m.getTitle().equals(movie_name)){
                add_movie(m);
            }
        }
    }
    // for test
//    public  void getdata(){
//        move m=new move();
//        m.name="frozen";
//        m.url="C:\\Users\\Start\\IdeaProjects\\demo1\\src\\main\\java\\image\\download.jpeg";
//        mo.add(m);
//        m=new move();
//        m.name="spider man";
//        m.url="C:\\Users\\Start\\IdeaProjects\\demo1\\src\\main\\java\\image\\format-arw-PXjQaGxi4JA-unsplash.jpg";
//        mo.add(m);
//        m=new move();
//        m.name="spider man";
//        m.url="C:\\Users\\Start\\IdeaProjects\\demo1\\src\\main\\java\\image\\images.jpeg";
//        mo.add(m);
//    }
    private void add_movie(Movie movie){
        Image image=new Image(movie.getPoster_path());
        ImageView imageView=new ImageView(image);
        imageView.setFitHeight(150);
        imageView.setFitWidth(200);
        Label name=new Label(movie.getTitle());
        name.setFont(Font.font(20));
        VBox show=new VBox(10);
        show.getChildren().addAll(imageView,name);
        show.setAlignment(Pos.CENTER);
        Movies.getChildren().add(show);
    }
    protected void Search(String search_name){
        String[] arr = search_name.split(" ");
        String first = arr[0], second = arr[1];
        Director d = Director.Search_director(first, second);
        data_Label.setText("Director");
//        For cast
//        data_Label.setText("Cast");
//        Cast c=Cast.Search_Cast(first,second,director);
//        if(c==null)
//            Not_Found_Label.setText("Not Found !!!");
//        else{
//            Name_Label.setText(d.getFirst_Name() + " " + d.getSecond_Name());
//            Age_Label.setText(Integer.toHexString(d.getAge()));
//            Gender_Label.setText(d.getGender());
//            Nation_Label.setText(d.getNationality());
//        }
        if (d == null)
            Not_Found_Label.setText("Not Found !!!");
        else {
            Name_Label.setText(d.getFirst_Name() + " " + d.getSecond_Name());
            Age_Label.setText(Integer.toHexString(d.getAge()));
            Gender_Label.setText(d.getGender());
            Nation_Label.setText(d.getNationality());
            for (String n:d.getMovies()) {
                initialize(n);
            }
        }
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }
}
