package com.example.watch_it;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class profilePageController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label username;
    @FXML
    private Label email;

    @FXML
    private Label firstname;
    @FXML
    private Label plan;

    @FXML
    private Label secondname;
    @FXML
    private Button Back;
    @FXML
    private ImageView profilePictue;
    private boolean isAdmin;
    private User user;
    String UserName ;
    ButtonType buttonTypeYes = new ButtonType("Yes");
    ButtonType buttonTypeNo = new ButtonType("No");
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    @FXML
    private ChoiceBox<String> Select_List;

    //choose 3nd el onMouseClicked f akher checkbox fl fxml
    @FXML
    public void choose(MouseEvent event) throws IOException {
        String choice = Select_List.getSelectionModel().getSelectedItem();
        if(choice!=null) {
            if (choice.equals("Log Out")) {
                alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == buttonTypeYes) {
                    //To Switch to the page first(from the task of Tasneem).
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("first-page.fxml")));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
            } else if (choice.equals("Delete Account")) {
                System.out.println("in");
                User.Delete_User(UserName);
                //until we manage writing as obj
                //not working-----------
                for (String users : Admin.users) {
                    String[] data = users.split(",");
                    if (data[Admin.USERNAMEINDEX].equals(UserName)) {
                        Admin.users.remove(users);
                    }
                }

            }
            //tmam
            else if (choice.equals("Edit Profile")) {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("update-page.fxml")));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            //Delete_Recorded_Movies
            else {
                alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == buttonTypeYes) {
                    user.setWatchedMovies(null);
                }
            }
        }
    }

    // action from main page and username which entered in sign in page (user)
    public void Set_Data_User(ActionEvent act , String username )
    {
        for(User user: User.allusers)
        {
            if(user.getUser_Name().equals(username))
            {
                firstname.setText(user.getFirst_Name());
                secondname.setText(user.getLast_Name());
                // id.setText(user.getUser_ID());   id???
                plan.setText(user.getPlan());
                email.setText(user.getEmail());
            }
        }
    }
    public void Delete_User(String user_name)
    {
        int index = 0 ;
        for(User user : User.allusers) {
            if (user.getUser_Name().equals(user_name)) {
                index = User.allusers.indexOf(user);
                User.allusers.remove(user);
            }
        }
        for(int i = index ; i <= User.allusers.size() ; i++)
        {
            User.allusers.get(i).setUser_ID(i++);
        }
    }

    @FXML
    public void move(ActionEvent act)throws IOException
    {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("recorded-movies.fxml")));
        stage = (Stage)((Node)act.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        Select_List.getItems().addAll("Edit Profile" , "Log Out" , "Delete Account" , "Delete Watched Movies");

    }
    @FXML
    private void onMouseEntered(){
        Back.setOnMouseEntered(event -> Back.setStyle("-fx-background-color: #FFC107; -fx-background-radius: 25; -fx-border-color: white; -fx-border-radius: 25;"));
    }
    @FXML
    private void onMouseExit(){
        Back.setOnMouseExited(event -> Back.setStyle("-fx-background-color: black; -fx-background-radius: 25; -fx-border-color: white; -fx-border-radius: 25;"));
    }
    @FXML
    private void  backScenes(ActionEvent event) throws IOException {
        if (isAdmin) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("all-movies.fxml"));
            root = loader.load();
            AllMoviesController controller = loader.getController();
            controller.setStage(stage);
            controller.toAllUsers(event);
        }
        else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("main-page.fxml"));
            root = loader.load();
            MainPageController controller = loader.getController();
            controller.setStage(stage);
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setX(-7);
        stage.setY(0);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void goToMovieList (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("recorded-movies.fxml"));
        root = loader.load();
        RecordedMoviesController controller = loader.getController();
        controller.setStage(stage);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setX(-7);
        stage.setY(0);
        stage.setScene(scene);
        stage.show();
    }
    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void setdata(){
        isAdmin = false;
        Image image = new Image(getClass().getResourceAsStream(SignIn.user5.getProfilePath()));
        profilePictue.setImage(image);
        profilePictue.setFitHeight(200);
        profilePictue.setFitWidth(200);
        username.setText(SignIn.user5.getUser_Name());
        email.setText(SignIn.user5.getEmail());
        plan.setText(SignIn.user5.getPlan());
        secondname.setText(SignIn.user5.getLast_Name());
        firstname.setText(SignIn.user5.getFirst_Name());
    }
    public void setdatatoAdmin(User user){
        isAdmin = true;
        Image image = new Image(getClass().getResourceAsStream(user.getProfilePath()));
        profilePictue.setImage(image);
        profilePictue.setFitHeight(200);
        profilePictue.setFitWidth(200);
        username.setText(user.getUser_Name());
        email.setText(user.getEmail());
        plan.setText(user.getPlan());
        secondname.setText(user.getLast_Name());
        firstname.setText(user.getFirst_Name());
    }
    public void setUser(User user){
        this.user = user;
    }
}