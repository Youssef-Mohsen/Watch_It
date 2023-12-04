package com.example.watch_it;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("movie-view.fxml"));
        Parent root = loader.load();
        HelloController controller = loader.getController();
        Scene scene = new Scene(root);
        stage.setTitle("Movie");
        stage.setResizable(false);
        stage.setX(-7);
        stage.setY(0);
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/" + "Amazing_SpiderMan.png")));
        controller.refreshLabels("Watch Movie", "Amazing SpiderMan", "Amazing SpiderMan Bad"
                , "Action", "Amazing SpiderMan Film.", "121 minutes", image);
        stage.setScene(scene);
        stage.show();
        System.out.println(stage.getWidth());
        System.out.println(stage.getHeight());


    }


    public static void main(String[] args) {
        launch();
    }
}