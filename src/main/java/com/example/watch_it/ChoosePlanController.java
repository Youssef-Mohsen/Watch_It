package com.example.watch_it;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ChoosePlanController {
    @FXML
    private Button basic;
    @FXML
    private Button standard;
    @FXML
    private Button premium;
    @FXML
    private TextField cardNumber;
    @FXML
    private TextField cardHolderName;
    @FXML
    private TextField MMyy;
    @FXML
    private TextField Cvv;
    private Parent root;
    private Scene scene;
    static Button selectedButton = new Button();
    Stage stage;
    private User user;
    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    private void initialize(){
        basic.setStyle("-fx-border-color: #090909; -fx-background-color: #090909; -fx-background-radius: 30; -fx-border-radius: 30;");
        standard.setStyle("-fx-border-color: #090909; -fx-background-color: #090909; -fx-background-radius: 30; -fx-border-radius: 30;");
        premium.setStyle("-fx-border-color: #090909; -fx-background-color: #090909; -fx-background-radius: 30; -fx-border-radius: 30;");
    }
    @FXML
    private void Basic() {
        basic.setStyle("-fx-background-color: #090909; -fx-background-radius: 30; -fx-border-radius: 30; -fx-border-color: #FFC107;");
        standard.setStyle("-fx-background-color: #090909; -fx-background-radius: 30; -fx-border-radius: 30; -fx-border-color: #090909;");
        premium.setStyle("-fx-background-color: #090909; -fx-background-radius: 30; -fx-border-radius: 30; -fx-border-color: #090909;");
        selectedButton = basic;
    }
    @FXML
    private void Standard() {
        basic.setStyle("-fx-background-color: #090909; -fx-background-radius: 30; -fx-border-radius: 30; -fx-border-color: #090909;");
        standard.setStyle("-fx-background-color: #090909; -fx-background-radius: 30; -fx-border-radius: 30; -fx-border-color: #FFC107;");
        premium.setStyle("-fx-background-color: #090909; -fx-background-radius: 30; -fx-border-radius: 30; -fx-border-color: #090909;");
        selectedButton = standard;
    }
    @FXML
    private void Premium() {
        basic.setStyle("-fx-background-color: #090909; -fx-background-radius: 30; -fx-border-radius: 30; -fx-border-color: #090909;");
        standard.setStyle("-fx-background-color: #090909; -fx-background-radius: 30; -fx-border-radius: 30; -fx-border-color: #090909;");
        premium.setStyle("-fx-background-color: #090909; -fx-background-radius: 30; -fx-border-radius: 30; -fx-border-color: #FFC107;");
        selectedButton = premium;
    }
    @FXML
    private void Pay() {
        String cardnumber = cardNumber.getText();
        String cardholdername = cardHolderName.getText();
        String mmyy = MMyy.getText();
        String cvv = Cvv.getText();
        if (cardnumber.isEmpty() || cardholdername.isEmpty() || mmyy.isEmpty() || cvv.isEmpty() || selectedButton == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Enter All Data");
            alert.showAndWait();
        }
        else {
            User.allusers.add(user);
            if (selectedButton.equals(basic)) {
                user.Subscribe(user.getUser_ID(),  Subscription.Plans.BASIC);
                user.Updata_Subscription_Plan(Subscription.Plans.BASIC);
                user.setPlan("basic");
            }
            else if (selectedButton.equals(standard)){
                user.Subscribe(user.getUser_ID(),  Subscription.Plans.STANDARD);
                user.Updata_Subscription_Plan(Subscription.Plans.STANDARD);
                user.setPlan("standard");
            }
            else if (selectedButton.equals(premium)){
                user.Subscribe(user.getUser_ID(),  Subscription.Plans.PREMIUM);
                user.Updata_Subscription_Plan(Subscription.Plans.PREMIUM);
                user.setPlan("premium");
            }
            Admin.users.add(user.toString());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("Sign Up is completed Successful!");
            alert.showAndWait();
        }
    }
    @FXML
    private void goBack (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sign-up.fxml"));
        root = loader.load();
        SignUp controller = loader.getController();
        controller.setStage(stage);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Movie");
        stage.setResizable(false);
        stage.setX(-7);
        stage.setY(0);
        stage.setScene(scene);
        stage.show();
    }
    public void setStage (Stage stage) {
        this.stage = this.stage;
    }
}