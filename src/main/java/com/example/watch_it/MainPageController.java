package com.example.watch_it;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainPageController {
    private Stage stage;

    @FXML
    private Label Action;
    @FXML
    private Label Comedy;
    @FXML
    private Label Drama;
    @FXML
    private Label Horror;
    @FXML
    private Label All;

    @FXML
    private ImageView firstPhoto;

    public void onMouseClicked() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("movie-view.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MovieController controller = loader.getController();
        controller.setStage(stage);
        Scene scene = new Scene(root);
        stage.setTitle("Movie");
        stage.setResizable(false);
        stage.setX(-7);
        stage.setY(0);
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/" + "Amazing_SpiderMan.png")));
        controller.refreshLabels("Watch Movie", "Amazing SpiderMan", "Amazing SpiderMan Translated"
                , "Action", "Amazing SpiderMan Film.", "120 minutes", image);


        Action.setOnMouseClicked(event -> {
            stage.setScene(scene);
            stage.show();
        });
        firstPhoto.setOnMouseClicked(event -> {
            stage.setScene(scene);
            stage.show();
        });
    }

    public void onMouseEntered() {
        Action.setOnMouseEntered(event -> Action.setStyle("-fx-background-radius: 25; -fx-background-color:  #FFC107;"));
        Comedy.setOnMouseEntered(event -> Comedy.setStyle("-fx-background-radius: 25; -fx-background-color:  #FFC107;"));
        Drama.setOnMouseEntered(event -> Drama.setStyle("-fx-background-radius: 25; -fx-background-color:  #FFC107;"));
        Horror.setOnMouseEntered(event -> Horror.setStyle("-fx-background-radius: 25; -fx-background-color:  #FFC107;"));
        All.setOnMouseEntered(event -> All.setStyle("-fx-background-color: #FFC107; -fx-background-radius: 25; -fx-border-color: white; -fx-border-radius: 25;"));
    }

    public void onMouseExit() {
        // Handle mouse exit event
        Action.setOnMouseExited(event -> Action.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;"));
        Comedy.setOnMouseExited(event -> Comedy.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;"));
        Drama.setOnMouseExited(event -> Drama.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;"));
        Horror.setOnMouseExited(event -> Horror.setStyle("-fx-background-radius: 25; -fx-background-color:  #565661;"));
        All.setOnMouseExited(event -> All.setStyle("-fx-background-color: black; -fx-background-radius: 25; -fx-border-color: white; -fx-border-radius: 25;"));
    }
    ////////////////////////////


    ///////////////////////////

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
