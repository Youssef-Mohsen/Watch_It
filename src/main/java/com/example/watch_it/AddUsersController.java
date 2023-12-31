package com.example.watch_it;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddUsersController {
    @FXML
    private TextField fnameField;
    @FXML
    private TextField lnameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField planField;
    @FXML
    private TextField usernameField;
    private String profilepath;
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
    @FXML
    private Button addButton;
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
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
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void showErrorAlert(String message) {
        showAlert(message);
    }
    private boolean emailValidation(String email){
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    @FXML
    private void Adduser() {

        String username = usernameField.getText();
        String fname = fnameField.getText();
        String lname = lnameField.getText();
        String password = passwordField.getText();
        String email = emailField.getText();
        String plan = planField.getText();
        RadioButton selectedbutton = (RadioButton) group.getSelectedToggle();

        if (User.Userexist(username))  {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Username Already Exists");
            alert.showAndWait();
        }

        else if (username.isEmpty() || fname.isEmpty() || lname.isEmpty() || password.isEmpty() || email.isEmpty() || plan.isEmpty() || selectedbutton == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Enter All Data");
            alert.showAndWait();
        }
        else if(!emailValidation(emailField.getText())){
            showErrorAlert("Wrong Email Format");
        }
        else if(usernameField.getText().length()<4 || usernameField.getText().length()<4){
            showErrorAlert("Must Insert At Least 4 Characters In Username or Password");
        }

        else {
            Subscription.Plans Plan;
            if (plan.equalsIgnoreCase("basic"))
                Plan = Subscription.Plans.BASIC;
            else if (plan.equalsIgnoreCase("standard"))
                Plan = Subscription.Plans.STANDARD;
            else if (plan.equalsIgnoreCase("premium"))
                Plan = Subscription.Plans.PREMIUM;
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Plan Doesn't Exist");
                alert.showAndWait();
                return;
            }

            if (selectedbutton.equals(button1))
                profilepath = "assets/batbot-01.png";
            else if (selectedbutton.equals(button2))
                profilepath = "assets/batbot-02.png";
            else if (selectedbutton.equals(button3))
                profilepath = "assets/batbot-03.png";
            else if (selectedbutton.equals(button4))
                profilepath = "assets/batbot-04.png";
            else if (selectedbutton.equals(button5))
                profilepath = "assets/batbot-05.png";
            else if (selectedbutton.equals(button6))
                profilepath = "assets/batbot-06.png";


            User user = new User(username, lname, fname, email, password, profilepath, plan);
            user.Subscribe(user.getUser_ID(), Plan);
            User.allusers.add(user);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("User Added");
            alert.showAndWait();
        }
    }
    @FXML
    public  void onMouseEntered(){
        addButton.setOnMouseEntered(event -> addButton.setStyle("-fx-background-color: #FFC107; -fx-background-radius: 25; -fx-border-color: white; -fx-border-radius: 25;"));
    }
    @FXML
    public  void onMouseExit(){
        addButton.setOnMouseExited(event -> addButton.setStyle("-fx-background-color: black; -fx-background-radius: 25; -fx-border-color: white; -fx-border-radius: 25;"));
    }

}