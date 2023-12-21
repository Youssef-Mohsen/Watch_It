package com.example.watch_it;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class AllUsersController implements Initializable {
    private Parent root;
    private Scene scene;
    private Stage stage;
    @FXML
    private VBox userbox1;
    @FXML
    private VBox userbox2;
    @FXML
    private VBox userbox3;
    @FXML
    private VBox userbox4;
    @FXML
    private TextField searchBar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //getUsers();

        getAllUsers();
    }
    private void  getAllUsers(){
        int i = 0;
        // Add labels for users from the HelloApplication.users list
        for (User user : User.allusers){
            i++;
            addUserDetails(i, user);
        }
    }

    private void addUserDetails(int i,User user) {
        Button view = new Button("View");
        view.setAlignment(javafx.geometry.Pos.CENTER);
        view.setStyle("-fx-background-color: black; -fx-background-radius: 25; -fx-border-color: white; -fx-border-radius: 25;");
        view.setTextFill(Color.WHITE);
        Font font = new Font("System Bold", 15.0);
        view.setFont(font);

        Label nameLabel = new Label(user.getUser_Name());
        nameLabel.setTextFill(Color.WHITE);
        nameLabel.setFont(Font.font("System Bold", 30.0));
        Image image = new Image(getClass().getResourceAsStream(user.getProfilePath()));
        ImageView batbot = new ImageView(image);
        batbot.setFitHeight(120);
        batbot.setFitWidth(118);

        VBox userDetailsBox = new VBox();

        userDetailsBox.getChildren().addAll(batbot,nameLabel,  view) ;
        userDetailsBox.setSpacing(5);
        userDetailsBox.setAlignment(Pos.CENTER);
        if (i % 4 == 1) {
            userbox1.getChildren().add(userDetailsBox);
            userbox1.setSpacing(50);
            VBox.setMargin(userbox1, new Insets(0, 0, 30, 0));
        }
        else if (i % 4 == 2) {
            userbox2.getChildren().add(userDetailsBox);
            userbox2.setSpacing(50);
            VBox.setMargin(userbox2, new Insets(0, 0, 30, 0));
        }
        if (i % 4 == 3) {
            userbox3.getChildren().add(userDetailsBox);
            userbox3.setSpacing(50);
            VBox.setMargin(userbox3, new Insets(0, 0, 30, 0));
        }
        if (i % 4 == 0) {
            userbox4.getChildren().add(userDetailsBox);
            userbox4.setSpacing(50);
            VBox.setMargin(userbox4, new Insets(0, 0, 30, 0));
        }
        view.setOnMouseClicked(event -> {
            try {
                GoToMainProfile(event, user);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    public void GoToMainProfile(MouseEvent event, User user) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("profile-page.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        profilePageController controller=loader.getController();
        controller.setStage(stage);
        controller.setUser(user);
        controller.setdatatoAdmin(user);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void Search() {
        System.out.println("in");
        String username = searchBar.getText();
        userbox1.getChildren().clear();
        userbox2.getChildren().clear();
        userbox3.getChildren().clear();
        userbox4.getChildren().clear();
        for (User user : User.allusers) {
            if (username.equalsIgnoreCase(user.getUser_Name())) {
                addUserDetails(1, user);
            }
        }
        if (username.isEmpty()){
            getAllUsers();
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}