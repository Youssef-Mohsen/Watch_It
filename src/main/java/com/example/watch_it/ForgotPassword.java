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

public class ForgotPassword {
    private Stage stage;
    private Scene scene;

    private Parent root;

    @FXML
    private TextField CTextPassword;

    @FXML
    private CheckBox CheckPassword;

    @FXML
    private PasswordField ForgotConfirmPassword;

    @FXML
    private TextField ForgotEmail;
    @FXML
    private Button Back;

    @FXML
    private PasswordField ForgotPassword;

    @FXML
    private TextField ForgotTextPassword;

    @FXML
    private TextField ForgotUserName;
    @FXML

    private void showAlert(String message) {
        showAlert(message, Alert.AlertType.INFORMATION);
    }

    @FXML
    private void showErrorAlert(String title, String message) {
        showAlert(title, message, Alert.AlertType.ERROR);
    }
    @FXML
    private void showAlert(String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Login Result");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    public void  ChangePassword(ActionEvent event) throws IOException {
        String username = ForgotUserName.getText();
        String email = ForgotEmail.getText();
        String password = ForgotPassword.getText();
        String confirmpassword = ForgotConfirmPassword.getText();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmpassword.isEmpty()) {
            showErrorAlert("Invalid Data", "Enter All Your Data");
        }
        else if (!User.Userexist(username)) {
            showErrorAlert("Invalid Data", "Username Is Incorrect, Try again!");
            ForgotUserName.clear();
        }
        else {
            if (!(User.GetUser(username).getEmail().equals(email))) {
                showErrorAlert("Invalid Data", "Email Is Incorrect, Try again!");
                ForgotEmail.clear();
                return;
            }
            if (!password.equals(confirmpassword)) {
                showErrorAlert("Invalid Data", "Confirm Password Does not match, Try again!");
                ForgotConfirmPassword.clear();
                return;
            }
            showAlert("Password has been changed Successful!");
            GoToSignIn(event);
        }
    }
    @FXML
    public void GoToSignIn(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sign-in.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void The_Password_Visibility(ActionEvent act)
    {
        if(CheckPassword.isSelected())
        {
            ForgotTextPassword.setText(ForgotPassword.getText());
            CTextPassword.setText(ForgotConfirmPassword.getText());
            ForgotTextPassword.setVisible(true);
            ForgotPassword.setVisible(false);
            CTextPassword.setVisible(true);
            ForgotConfirmPassword.setVisible(false);
        }
        else
        {
            ForgotPassword.setText(ForgotTextPassword.getText());
            ForgotTextPassword.setVisible(false);
            ForgotPassword.setVisible(true);
            ForgotConfirmPassword.setText(CTextPassword.getText());
            CTextPassword.setVisible(false);
            ForgotConfirmPassword.setVisible(true);
        }
    }
    public void onMouseEntered() {
        Back.setOnMouseEntered(event -> Back.setStyle("-fx-background-color: #FFC107; -fx-background-radius: 25; -fx-border-color: white; -fx-border-radius: 25;"));
    }

    public void onMouseExit() {
        Back.setOnMouseExited(event -> Back.setStyle("-fx-background-color: black; -fx-background-radius: 25; -fx-border-color: white; -fx-border-radius: 25;"));
    }
}