package com.example.watch_it;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    /*@FXML
    public void  ChangePassword()
    {
        String username = ForgotUserName.getText();
        while(Admin.existsInFile(username))
        {
            showErrorAlert("Incorrect UserName", "UserName Is InCorrect , Try again!");
            ForgotUserName.clear();
        }
        String email = ForgotEmail.getText();
        while(Admin.getSpecificCellForUser(username,Admin.EmailIndex).equals(email))
        {
            showErrorAlert("Incorrect Email", "Email Is InCorrect , Try again!");
            ForgotEmail.clear();
        }
        String password = ForgotPassword.getText();
        String confirmpassword = ForgotConfirmPassword.getText();
        while(!password.equals(confirmpassword))
        {
            showErrorAlert("Invalid", "Confirm Password Does not match, Try again!");
            ForgotConfirmPassword.clear();
        }
        showAlert("Password has been changed Successful!");
    }*/
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
}