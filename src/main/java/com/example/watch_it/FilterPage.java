package com.example.watch_it;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class FilterPage {
    private Stage stage;
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
    @FXML
    public void initialize (){
        switch (MainPageController.moviePage) {
            case 1:
                for (int i = 0; i < Movie.action.size(); i++) {
                    addMovies(Movie.action.get(i),i);
                }
                break;
            case 2:
                for (int i = 0; i < Movie.comedy.size(); i++) {
                    addMovies(Movie.comedy.get(i),i);
                }
                break;
            case 3:
                for (int i = 0; i < Movie.drama.size(); i++) {
                    addMovies(Movie.drama.get(i),i);
                }
                break;
            case 4:
                for (int i = 0; i < Movie.horror.size(); i++) {
                    addMovies(Movie.horror.get(i),i);
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
        movieContainer.setOnMouseClicked(event -> {
            try {
                onMouseClickedVBox(event,movie);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

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
    public void onMouseClickedVBox(MouseEvent act, Movie movie) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("movie-view.fxml"));
        Parent root = loader.load();
        stage = (Stage)((Node)act.getSource()).getScene().getWindow();
        MovieController controller=loader.getController();
        controller.setStage(stage);
        controller.setMovie(movie);
        controller.watchMovie(movie);
        controller.initialize();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        //movie.getRelease_date().getYear()
        //movie.getGenres().toString()
        Image image = new Image(getClass().getResourceAsStream(movie.getPoster_path()));
        controller.refreshScreen("Watch Movie "+ movie.getTitle() + "("+movie.getRelease_date().getYear()+")", movie.getTitle(),
                movie.getTitle()+" Translated",movie.getGenres(), movie.getDescription(),
                movie.getRunning_time(), image,movie.getDirectorName(),movie.getCast().toString());
        stage.setScene(scene);
        stage.show();

    }
    public void setStage(Stage stage){
        this.stage=stage;
    }
}
