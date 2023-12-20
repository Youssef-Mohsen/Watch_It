package com.example.watch_it;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class First {
    private Stage stage;
    private Scene scene;

    private Parent root;
    @FXML
    private Button exitButton;

    @FXML
    public void initialize(){
        onMouseEntered();
        onMouseExit();
    }
    @FXML
    public void UserSignIn(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("sign-in.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        SignIn controller=loader.getController();
        controller.setStage(stage);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void AdminSignIn(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("admin-page.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
}
    public void setStage(Stage stage){
        this.stage=stage;
}
    @FXML
    private void onMouseEntered(){
        exitButton.setOnMouseEntered(event -> exitButton.setStyle("-fx-background-color: #FFC107; -fx-background-radius: 25; -fx-border-color: white; -fx-border-radius: 25;"));
    }
    @FXML
    private void onMouseExit(){
        exitButton.setOnMouseExited(event -> exitButton.setStyle("-fx-background-color: black; -fx-background-radius: 25; -fx-border-color: white; -fx-border-radius: 25;"));
    }
    @FXML
    private void Exit(){
        Admin.writeOnFile(new File("data.txt"));
        Admin.writeMovies(new File("movies-data.txt"));
        Platform.exit();
    }

}