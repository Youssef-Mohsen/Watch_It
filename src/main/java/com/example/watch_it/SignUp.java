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

public class SignUp {
    private Stage stage;
    private Scene scene;

    private Parent root;
    @FXML
    private TextField CTextPassword;

    @FXML
    private CheckBox CheckBox;

    @FXML
    private PasswordField CreateConfirmPassword;

    @FXML
    private PasswordField CreatePassword;

    @FXML
    private TextField CreateUserName;

    @FXML
    private TextField Email;

    @FXML
    private TextField FisrtName;

    @FXML
    private TextField LastName;

    @FXML
    private TextField TextPassword;
    @FXML
    private Button Back;
    @FXML
    RadioButton button1;
    @FXML
    RadioButton button2;
    @FXML
    RadioButton button3;
    @FXML
    RadioButton button4;
    @FXML
    RadioButton button5;
    @FXML
    RadioButton button6;
    ToggleGroup group = new ToggleGroup();
    @FXML
    private void initialize(){
        button1.setToggleGroup(group);
        button2.setToggleGroup(group);
        button3.setToggleGroup(group);
        button4.setToggleGroup(group);
        button5.setToggleGroup(group);
        button6.setToggleGroup(group);
    }
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
    public void GoToSignIn(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sign-in.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void GoToChoosePlan(ActionEvent event, User user) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("choose-plan.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ChoosePlanController controller = loader.getController();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        controller.setStage(stage);
        controller.setUser(user);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setX(-7);
        stage.setY(0);

    }
    @FXML
    public void SignUP(ActionEvent event) throws IOException {
        String username = CreateUserName.getText();
        String firstname = FisrtName.getText();
        String lastname = LastName.getText();
        String email = Email.getText();
        String password = CreatePassword.getText();
        String confirmpassword = CreateConfirmPassword.getText();
        RadioButton selectedbutton = (RadioButton) group.getSelectedToggle();

        if (username.isEmpty() || firstname.isEmpty() || lastname.isEmpty() || password.isEmpty() || email.isEmpty() || confirmpassword.isEmpty() || selectedbutton == null){
            showErrorAlert("Invalid", "Enter All Data");
        }
        else if (!password.equals(confirmpassword))
        {
            showErrorAlert("Invalid", "Confirm Password Does not match, Try again!");
            CreateConfirmPassword.clear();
        }
        else if (User.Userexist(username))
        {
            showErrorAlert("Invalid", "UserName Is already exist , Try again!");
            CreateUserName.clear();
        }
        else {
            User user = new User(User.allusers.size(),username, lastname, firstname, email, password);
            GoToChoosePlan(event, user);
        }
    }
    @FXML
    public void The_Password_Visibility(ActionEvent act)
    {
        if(CheckBox.isSelected())
        {
            TextPassword.setText(CreatePassword.getText());
            CTextPassword.setText(CreateConfirmPassword.getText());
            TextPassword.setVisible(true);
            CreatePassword.setVisible(false);
            CTextPassword.setVisible(true);
            CreateConfirmPassword.setVisible(false);
        }
        else
        {
            CreatePassword.setText(TextPassword.getText());
            CreateConfirmPassword.setText(CTextPassword.getText());
            TextPassword.setVisible(false);
            CreatePassword.setVisible(true);
            CTextPassword.setVisible(false);
            CreateConfirmPassword.setVisible(true);
        }
    }
    public void onMouseEntered() {
        Back.setOnMouseEntered(event -> Back.setStyle("-fx-background-color: #FFC107; -fx-background-radius: 25; -fx-border-color: white; -fx-border-radius: 25;"));
    }

    public void onMouseExit() {
        Back.setOnMouseExited(event -> Back.setStyle("-fx-background-color: black; -fx-background-radius: 25; -fx-border-color: white; -fx-border-radius: 25;"));
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}