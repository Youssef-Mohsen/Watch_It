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
        Director.allDirectors.addAll(Admin.getAllDirectors());
        User.allusers.addAll(Admin.getAllUsers());
        Cast.allCast.addAll(Admin.getAllCast());
        Movie.allmovies.addAll(Admin.getMoviesObjs());
        Movie.getDiffGenres();
        ArrayList<String> n = new ArrayList<String>();
        n.add("hi");
        n.add("hi");
        n.add("hi");
        n.add("hi");
        String test ="";
        for(int i=0; i<n.size(); i++){
            if(i<n.size()-1){
                test = test.concat(n.get(i).concat(","));
            }
            else
                test= test.concat(n.get(i));
        }

        User user = User.GetUser("nada");

        Admin.getUserMovieLists(Admin.existsInFile(user.getUser_Name()),user.watchedMovies, user.toWatchMovies);

        for(String movie: user.watchedMovies){
            System.out.println(movie + "/wa/");
        }

        for(String movie: user.toWatchMovies){
            System.out.println(movie + "/to/");
        }
       // Admin.getUserMovieLists_obj(user);
      /*  for(Movie movie: user.Movies_For_Later){
            System.out.println(movie.getTitle());
        }
        for(UserWatchRecord movie: user.Watched_Movies){
                    System.out.println(movie.movie.getTitle()+ "  " + movie.getRating() );
        }*/
        Admin.getUserMovieLists(user);

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
          //  System.out.println(User.allusers.get(0).toString());
        });
        stage.show();

    }

    public static void main(String[] args) {

        launch();
    }
}