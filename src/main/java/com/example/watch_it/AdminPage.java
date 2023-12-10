package com.example.watch_it;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    private PasswordField password;

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
}