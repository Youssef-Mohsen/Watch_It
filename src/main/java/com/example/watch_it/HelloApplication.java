package com.example.watch_it;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        File file = new File("data.txt");
        File movieFile = new File("movies-data.txt");
        Admin.readFile(file);;
        Admin.readMovies(movieFile);
        User.allusers.addAll(Admin.getAllUsers());
        Director.allDirectors.addAll(Admin.getAllDirectors());
        Cast.allCast.addAll(Admin.getAllCast());
        Admin.allAdmins.addAll(Admin.getAllAdmins());
        Movie.allmovies.addAll(Admin.getMoviesObjs());
        Movie.getDiffGenres();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("first-page.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        First controller = loader.getController();
        controller.setStage(stage);
        Scene scene = new Scene(root);
        stage.setTitle("Movie");
        stage.setResizable(false);
        stage.setX(-7);
        stage.setY(0);
        stage.setScene(scene);
        stage.setOnCloseRequest(windowEvent -> {
            Admin.writeOnFile(file);
        });
        stage.show();

    }
    public static void main(String[] args) {
        launch();
    }
}