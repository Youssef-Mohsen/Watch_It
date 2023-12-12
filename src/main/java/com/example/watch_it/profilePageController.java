package com.example.watch_it;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    private Label usename;
    @FXML
    private Label email;

    @FXML
    private Label firstname;

    @FXML
    private Label id;

    @FXML
    private Label plan;

    @FXML
    private Label secondname;
    @FXML
    private Button Back;
    @FXML
    private ImageView profilePictue;
    String UserName = usename.getText();
    ButtonType buttonTypeYes = new ButtonType("Yes");
    ButtonType buttonTypeNo = new ButtonType("No");
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    @FXML
    private ChoiceBox<String> Select_List;


    //choose 3nd el onMouseClicked f akher checkbox fl fxml
    /*@FXML
    public void choose(MouseEvent event , ArrayList<User> users) throws IOException {
        String choice = Select_List.getSelectionModel().getSelectedItem();
        if(choice.equals("Log_Out"))
        {
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == buttonTypeYes)
            {
                //To Switch to the page first(from the task of Tasneem).
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("First.fxml")));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
        else if(choice.equals("Delete_Account"))
        {
            Delete_User(users , UserName);
        }
        else if(choice.equals("Edit_Profile"))
        {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Update_Profile.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else if(choice.equals("Delete_Subscription"))
        {
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == buttonTypeYes)
            {
                //call method delete subscription from class admin.
            }
        }
        //Delete_Recorded_Movies
        else
        {
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == buttonTypeYes)
            {
                //call method delete recorded movie from class admin.
            }
        }
    }*/
    // action from main page and username which entered in sign in page (user)
    /*public void Set_Data_User(ActionEvent act ,ArrayList<User> users , String username )
    {
        for(User user:users)
        {
            if(user.getUser_Name.equals(username))
            {
                firstname.setText(user.getFirst_Name);
                secondname.setText(user.getLast_Name);
                id.setText(user.getUser_ID);
                plan.setText(user.getSubscription.getPlan);
                email.setText(user.getEmail);
            }
        }
    }*/
    /*public void Delete_User(ArrayList<User> users , String user_name)
    {
        int index = 0 ;
        for(User user : users) {
            if (user.User_Name.equals(user_name)) {
                index = users.indexOf(user);
                users.remove(user);
            }
        }
        for(int i = index ; i <= users.size() ; i++)
        {
            users.get(i).setUser_ID(i++);
        }
    }*/
    @FXML
    public void move(ActionEvent act)throws IOException
    {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Recorded_Movies.fxml")));
        stage = (Stage)((Node)act.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        Select_List.getItems().addAll("Edit_Profile" , "Log_Out" , "Delete_Account" , "Delete_Subscription" , "Delete_Recorded_Movies");
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
        FXMLLoader loader=new FXMLLoader(getClass().getResource("main-page.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        profilePageController controller=loader.getController();
        controller.setStage(stage);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void setStage(Stage stage){
        this.stage = stage;
    }
}
