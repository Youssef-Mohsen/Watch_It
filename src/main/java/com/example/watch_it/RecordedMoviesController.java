package com.example.watch_it;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class RecordedMoviesController {
    private Stage stage;
    private Scene scene;

    private Parent root;
    public void Back_To_Profile(ActionEvent act) throws IOException
    {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Profile.fxml")));
        stage = (Stage)((Node)act.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
