package com.example.watch_it;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AdminPage
{
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private CheckBox checkbox;
    @FXML
    private Button SignIn;

    @FXML
    private PasswordField password;
    @FXML
    private Button Back;
    @FXML
    private TextField UserName;

    @FXML
    private TextField showedpassword;
    @FXML
    public void Back_TO_First(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("first-page.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void The_Password_Visibility(ActionEvent act)
    {
        if(checkbox.isSelected())
        {
            showedpassword.setText(password.getText());
            showedpassword.setVisible(true);
            password.setVisible(false);
        }
        else
        {
            password.setText(showedpassword.getText());
            showedpassword.setVisible(false);
            password.setVisible(true);
        }
    }
    public void onMouseEntered() {
        Back.setOnMouseEntered(event -> Back.setStyle("-fx-background-color: #FFC107; -fx-background-radius: 25; -fx-border-color: white; -fx-border-radius: 25;"));
    }

    public void onMouseExit() {
        Back.setOnMouseExited(event -> Back.setStyle("-fx-background-color: black; -fx-background-radius: 25; -fx-border-color: white; -fx-border-radius: 25;"));
    }
    @FXML
    private void signIn(ActionEvent event) throws IOException {
        boolean adminExists = false;
        String username = UserName.getText();
        String passcode = password.getText();
        String[] data = new String[10];
        for(int i=0; i<Admin.admins.size(); i++){
            data = Admin.admins.get(i).split(",");
            if(data[Admin.USERNAMEINDEX].equals(username)){
                adminExists = true;
                break;
            }
        }
        if(adminExists){
            if(data[Admin.PASSWORDINDEX].equals(passcode)){
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("all-movies.fxml")));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR,"password is incorrect", ButtonType.OK );
                alert.setTitle("");
                alert.initOwner((SignIn.getScene().getWindow()));
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR,"username is incorrect", ButtonType.OK );
            alert.setTitle("");
            alert.initOwner((SignIn.getScene().getWindow()));
            alert.showAndWait();
        }

    }
}