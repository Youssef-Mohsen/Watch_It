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

public class UpdatePageController {
    private Stage stage;
    private Scene scene;

    private Parent root;
    @FXML
    private CheckBox Checkbox;
    @FXML
    private TextField ConfirmTextPassword;
    @FXML
    private TextField TextPassword;
    @FXML
    private TextField Email;
    @FXML
    private TextField FirstName;

    @FXML
    private PasswordField Password;
    @FXML
    private PasswordField Confirmpassword;

    @FXML
    private TextField Plan;

    @FXML
    private TextField SecondName;
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
    public void  Update_Profile(ActionEvent act)
    {
        String firstname = FirstName.getText();
        String secondname = SecondName.getText();
        String email =  Email.getText();
        String plan = Plan.getText();
        String password = Password.getText();
        String confirmpassword = Confirmpassword.getText();
        while(!password.equals(confirmpassword))
        {
            showErrorAlert("Invalid", "Confirm Password Does not match, Try again!");
            Confirmpassword.clear();
        }
        showAlert("Update_profile is completed Successful!");
    }
    public void Back_To_Profile(ActionEvent act) throws IOException
    {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Profile.fxml")));
        stage = (Stage)((Node)act.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void The_Password_Visibility(ActionEvent act)
    {
        if(Checkbox.isSelected())
        {
            TextPassword.setText(Password.getText());
            ConfirmTextPassword.setText(Confirmpassword.getText());
            TextPassword.setVisible(true);
            Password.setVisible(false);
            ConfirmTextPassword.setVisible(true);
            Confirmpassword.setVisible(false);
        }
        else
        {
            Password.setText(TextPassword.getText());
            Confirmpassword.setText(ConfirmTextPassword.getText());
            TextPassword.setVisible(false);
            Password.setVisible(true);
            ConfirmTextPassword.setVisible(false);
            Confirmpassword.setVisible(true);
        }
    }
}
