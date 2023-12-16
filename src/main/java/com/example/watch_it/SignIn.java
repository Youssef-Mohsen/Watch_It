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
import java.util.ArrayList;
import java.util.Objects;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class SignIn
{
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    @FXML
    private Button Back;
    @FXML
    private PasswordField Password;

    @FXML
    private Button SignIn;

    @FXML
    private TextField TextPassword;

    @FXML
    private TextField UserName;
    private Alert showAlert;

    @FXML
    private CheckBox checkbox;
    private AlertType alertType;
    private int try_Password = 1;
    private int try_Username = 1;
    public static User user5;
    @FXML

    private void showAlert(String message) {
        showAlert(message, AlertType.INFORMATION);
    }

    @FXML
    private void showErrorAlert(String title, String message) {
        showAlert(title, message, Alert.AlertType.ERROR);
    }
    @FXML
    private void showAlert(String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Login Result");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    private void showAlert(String title, String message, AlertType alertType) {
        Alert alert = new Alert(alertType,message,ButtonType.OK);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    /**Sign IN**/
    @FXML
    public void SignIN(ActionEvent act) throws IOException {
        String username = UserName.getText();
        String password = Password.getText();

        // To Check all data entered.
        if (username.isEmpty() || password.isEmpty()) {
            showErrorAlert("Sign IN Failed", "Please Enter All Your Data");
            return;
        }

        // To Check the username exist in data.
        if (User.Userexist(username)) {
            User user = User.GetUser(username);

            assert user != null;
            if (password.equals(user.getPassword())) {
                GoToMainPage(act, user);
            }
            else {
                if (try_Password == 3) {
                    showErrorAlert("Invalid Password", "Password Is InCorrect , Change Password!");
                    gotoforgorpassword(act);
                    return;
                }
                showErrorAlert("Sign IN Failed", "Please check if your Password is correct");
                Password.clear();
                try_Password++;
            }
        }
        else {
            if (try_Username == 3) {
                showErrorAlert("Invalid UserName", "UserName does not exist , Create account!");
                gotosignup(act);
                return;
            }
            showErrorAlert("Incorrect UserName", "UserName Is InCorrect , Try again!");
            UserName.clear();
            try_Username++;
        }
    }

    @FXML
    public void gotoforgorpassword(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("forgot-pass.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void gotosignup(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sign-up.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void gotofirst(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("first-page.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void GoToMainPage(ActionEvent event, User user) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-page.fxml"));
        Parent root = loader.load();
        MainPageController controller = loader.getController();
        controller.setStage(stage);
        controller.setUser(user);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Movie");
        stage.setResizable(false);
        stage.setX(-7);
        stage.setY(0);
        user5=user;
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void The_Password_Visibility(ActionEvent act)
    {
        if(checkbox.isSelected())
        {
            TextPassword.setText(Password.getText());
            TextPassword.setVisible(true);
            Password.setVisible(false);
        }
        else
        {
            Password.setText(TextPassword.getText());
            TextPassword.setVisible(false);
            Password.setVisible(true);
        }
    }
    public void onMouseEntered() {
        Back.setOnMouseEntered(event -> Back.setStyle("-fx-background-color: #FFC107; -fx-background-radius: 25; -fx-border-color: white; -fx-border-radius: 25;"));
    }

    public void onMouseExit() {
        Back.setOnMouseExited(event -> Back.setStyle("-fx-background-color: black; -fx-background-radius: 25; -fx-border-color: white; -fx-border-radius: 25;"));
    }
    public void setStage(Stage stage){
        this.stage=stage;
    }
}