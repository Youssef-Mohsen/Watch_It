package com.example.watch_it;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.net.URL;
import java.util.ResourceBundle;


public class AllUsersController implements Initializable {

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

    @FXML
    Image image1 = new Image(getClass().getResourceAsStream("assets/batbot-01.png"));
    @FXML
    Image image2 = new Image(getClass().getResourceAsStream("assets/batbot-02.png"));
    @FXML
    Image image3 = new Image(getClass().getResourceAsStream("assets/batbot-03.png"));
    @FXML
    Image image4 = new Image(getClass().getResourceAsStream("assets/batbot-04.png"));

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
            addUserDetails(i, user.getUser_Name(), user.getFirst_Name(), user.getLast_Name(), user.getEmail(),user.getPassword());
        }
    }

    private void addUserDetails(int i,String username, String fname, String lname,String email, String password) {
        Button view = new Button("View");
        view.setAlignment(javafx.geometry.Pos.CENTER);
        view.setStyle("-fx-background-color: black; -fx-background-radius: 25; -fx-border-color: white; -fx-border-radius: 25;");
        view.setTextFill(Color.WHITE);
        Font font = new Font("System Bold", 15.0);
        view.setFont(font);

        Label nameLabel = new Label(username);
        nameLabel.setTextFill(Color.WHITE);
        nameLabel.setFont(Font.font("System Bold", 30.0));

        ImageView batbot1 = new ImageView(image1);
        batbot1.setFitHeight(120);
        batbot1.setFitWidth(118);
        ImageView batbot2 = new ImageView(image2);
        batbot2.setFitHeight(120);
        batbot2.setFitWidth(118);
        ImageView batbot3 = new ImageView(image3);
        batbot3.setFitHeight(120);
        batbot3.setFitWidth(118);
        ImageView batbot4 = new ImageView(image4);
        batbot4.setFitHeight(120);
        batbot4.setFitWidth(118);


        VBox userDetailsBox = new VBox();
        userDetailsBox.setSpacing(5);

        if (i % 4 == 1) {
            userDetailsBox.getChildren().addAll(batbot1,nameLabel,  view) ;
            userDetailsBox.setAlignment(Pos.CENTER);
            userbox1.getChildren().add(userDetailsBox);
            userbox1.setSpacing(50);
            VBox.setMargin(userbox1, new Insets(0, 0, 30, 0));
        }
        else if (i % 4 == 2) {
            userDetailsBox.getChildren().addAll(batbot2, nameLabel, view);
            userDetailsBox.setAlignment(Pos.CENTER);
            userbox2.getChildren().add(userDetailsBox);
            userbox2.setSpacing(50);
            VBox.setMargin(userbox2, new Insets(0, 0, 30, 0));
        }
        if (i % 4 == 3) {
            userDetailsBox.getChildren().addAll( batbot3,nameLabel, view);
            userDetailsBox.setAlignment(Pos.CENTER);
            userbox3.getChildren().add(userDetailsBox);
            userbox3.setSpacing(50);
            VBox.setMargin(userbox3, new Insets(0, 0, 30, 0));
        }
        if (i % 4 == 0) {
            userDetailsBox.getChildren().addAll( batbot4,nameLabel, view);
            userDetailsBox.setAlignment(Pos.CENTER);
            userbox4.getChildren().add(userDetailsBox);
            userbox4.setSpacing(50);
            VBox.setMargin(userbox4, new Insets(0, 0, 30, 0));
        }

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
                addUserDetails(1, user.getUser_Name(), user.getFirst_Name(), user.getLast_Name(), user.getEmail(),user.getPassword());
            }
        }
        if (username.isEmpty()){
            getAllUsers();
        }
    }
}

