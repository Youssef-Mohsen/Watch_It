package com.example.watch_it;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class FilterPage {

    @FXML
    private HBox Hbox;
    @FXML
    private VBox leftColumn;
    @FXML
    private VBox middleColumn1;
    @FXML
    private VBox middleColumn2;
    @FXML
    private VBox rightColumn;

    private  void get_data(){
        ArrayList<Movie> movies = new ArrayList<Movie>();
        Movie movie3 = new Movie();
        movie3.setTitle("Joker");
        movie3.setPoster_path("assets/joker.jpeg");
        Movie movie4 = new Movie();
        movie4.setTitle("Ready Or Not");
        movie4.setPoster_path("assets/ready or not.jpeg");
        movies.add(movie3);
        movies.add(movie3);
        movies.add(movie4);
        switch (MainPageController.moviePage){
            case 1:
                MainPageController.action.addAll(movies);
                break;
            case 2:
                MainPageController.comedy.addAll(movies);
                MainPageController.comedy.addAll(movies);
                break;
            case 3:
                MainPageController.drama.addAll(movies);
                MainPageController.drama.addAll(movies);
                MainPageController.drama.addAll(movies);
                break;
            case 4:
                MainPageController.horror.addAll(movies);
                MainPageController.horror.addAll(movies);
                MainPageController.horror.addAll(movies);
                MainPageController.horror.addAll(movies);
                break;
            default:

        }
    }

    @FXML
    public void initialize (){
        get_data();
        switch (MainPageController.moviePage) {
            case 1:
                for (int i = 0; i < MainPageController.action.size(); i++) {
                    addMovies(MainPageController.action.get(i),i);
                }
                break;
            case 2:
                for (int i = 0; i < MainPageController.comedy.size(); i++) {
                    addMovies(MainPageController.comedy.get(i),i);
                }
                break;
            case 3:
                for (int i = 0; i < MainPageController.drama.size(); i++) {
                    addMovies(MainPageController.drama.get(i),i);
                }
                break;
            case 4:
                for (int i = 0; i < MainPageController.horror.size(); i++) {
                    addMovies(MainPageController.horror.get(i),i);
                }
                break;
            default:
        }
    }
    private  void addMovies(Movie movie, int i){
        VBox movieContainer = new VBox(10);
        movieContainer.setStyle("-fx-background-radius:40;");

        Button movieName = new Button(movie.getTitle());
        movieName.setStyle("-fx-background-color: transparent; -fx-color:white; -fx-font-color:white; -fx-text-fill: #C2C2C2; -fx-background-radius:50;-fx-font-size: 20;\n" +
                "    -fx-text-fill: #C2C2C2;");

        Image image = new Image(getClass().getResourceAsStream(movie.getPoster_path()));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(260);
        imageView.setFitHeight(320);

        //movieContainer.setStyle("-fx-background-color:#262626;  -fx-background-radius: 20;");
        movieContainer.setPrefWidth(320);
        movieContainer.setAlignment(Pos.CENTER);
        movieContainer.getChildren().addAll(imageView, movieName);
        movieContainer.setPadding(new Insets(10 ,0 , 10 , 5));

        //VBox.setMargin(movieContainer,new Insets(20 ,5 , 0 , 5));
        int check = i %4;
        if( check == 0)
            leftColumn.getChildren().addAll(movieContainer);
        else if (check == 1) {
            middleColumn1.getChildren().addAll(movieContainer);
        }
        else if (check == 2) {
            middleColumn2.getChildren().addAll(movieContainer);
        }
        else
            rightColumn.getChildren().addAll(movieContainer);
    }

}
