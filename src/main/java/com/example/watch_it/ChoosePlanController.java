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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
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
    private String newPlan;
    private User user;
    private Subscription.Plans newSubscription;
    public static boolean subscriptionEnded = false;
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
    private void Pay(ActionEvent event) throws IOException {
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
            return;
        }

        if (selectedButton.equals(basic)) {
            newSubscription = Subscription.Plans.BASIC;
            newPlan = "basic";
        } else if (selectedButton.equals(standard)) {
            newSubscription = Subscription.Plans.STANDARD;
            newPlan = "standard";
        } else if (selectedButton.equals(premium)) {
            newSubscription = Subscription.Plans.PREMIUM;
            newPlan = "premium";
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("");
        alert.setHeaderText(null);

        if (SignIn.user5.getSubscription().getUpdatePlan()) {
            SignIn.user5.setPlan(newPlan);
            SignIn.user5.Updata_Subscription_Plan(newSubscription);
            SignIn.user5.getSubscription().updateSubscription(newSubscription);
            SignIn.user5.getSubscription().subscriptionNotValid = false;
            SignIn.user5.getSubscription().updatePlan = false;
            alert.setContentText("Subscription Updated");
            SignIn.user5.getSubscription().updatePlan = false;
            SignIn.user5.getSubscription().setUpdatePlan(false) ;
            toMoviesrecord(event);
        }
        else {
            if (subscriptionEnded) {
                SignIn.user5.Updata_Subscription_Plan(newSubscription);
                SignIn.user5.setPlan(newPlan);
                SignIn.user5.getSubscription().setStartDate(LocalDate.now());
                subscriptionEnded = false;
                alert.setContentText("Subscription Updated, Login to your Account");
            }
            else {
                user.Subscribe(user.getUser_ID(), newSubscription);
                alert.setContentText("Sign Up Completed, Login to your Account");
                user.Updata_Subscription_Plan(newSubscription);
                user.setPlan(newPlan);
                User.allusers.add(user);
            }
            alert.showAndWait();
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
    private void goBack (ActionEvent event) throws IOException {
        if (SignIn.user5.getSubscription().getUpdatePlan()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("main-page.fxml"));
            root = loader.load();
            MainPageController controller = loader.getController();
            controller.setStage(stage);
        }
        else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sign-up.fxml"));
            root = loader.load();
            SignUp controller = loader.getController();
            controller.setStage(stage);
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Movie");
        stage.setResizable(false);
        stage.setX(-7);
        stage.setY(0);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void toMoviesrecord(ActionEvent event)throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("profile-page.fxml"));
        Parent root = loader.load();
        profilePageController controller = loader.getController();
        controller.toMoviesrecord();
        controller.setUser(SignIn.user5);
        controller.setdata();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setX(-7);
        stage.setY(0);
        stage.setScene(scene);
        stage.show();
    }

    public void setStage (Stage stage) {
        this.stage = stage;
    }
}