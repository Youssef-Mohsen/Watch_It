package com.example.watch_it;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
    private String firstname;
    private String lastname;
    @FXML
    private Label fullname;
    @FXML
    private Label plan;
    @FXML
    private Button Back;
    @FXML
    private ImageView profilePictue;
    @FXML
    private AnchorPane mainpane;
    @FXML
    private TextField newEmail;
    @FXML
    private TextField newFirstname;
    @FXML
    private TextField newLastname;
    @FXML
    private Button updateButton;
    private boolean isAdmin;
    private User user;
    String UserName ;
    public static boolean updatePlan = false;
    @FXML
    private HBox watchedHbox;
    @FXML
    private HBox toWatchHbox;
    ButtonType buttonTypeYes = new ButtonType("Yes");
    ButtonType buttonTypeNo = new ButtonType("No");
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    @FXML
    private ChoiceBox<String> Select_List;
    @FXML
    private BorderPane mainPane;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        Select_List.getItems().addAll("Edit Profile" , "Log Out" , "Delete Account", "Delete Movies List");
        Select_List.setValue("Select");
        for (UserWatchRecord movie : SignIn.user5.Watched_Movies) {
            addWatchedList(movie);
        }
        for (Movie movie : SignIn.user5.Movies_For_Later) {
            addToWatchedList(movie);
        }
        onMouseExit();
        onMouseEntered();
        if (updatePlan) {
            try {
                toPlans();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @FXML
    public void choose(MouseEvent event) throws IOException {

        Select_List.setOnMouseClicked(mouseEvent -> {
            try {
                Select(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    public void Select (MouseEvent event) throws IOException {
        String choice = Select_List.getSelectionModel().getSelectedItem();
        if(choice.equals("Log Out"))
        {
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == buttonTypeYes)
            {
                FXMLLoader loader=new FXMLLoader(getClass().getResource("first-page.fxml"));
                root = loader.load();
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                First controller=loader.getController();
                controller.setStage(stage);
                Scene scene = new Scene(root);
                stage.setResizable(false);
                stage.setX(-7);
                stage.setY(0);
                stage.setScene(scene);
                stage.show();

            }
        }
        else if(choice.equals("Delete Account"))
        {
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
            Optional<ButtonType> result = alert.showAndWait();
            if ((result.isPresent() && result.get() == buttonTypeYes)) {
                User.allusers.remove(SignIn.user5);
                FXMLLoader loader=new FXMLLoader(getClass().getResource("first-page.fxml"));
                root = loader.load();
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                First controller=loader.getController();
                controller.setStage(stage);
                Scene scene = new Scene(root);
                stage.setResizable(false);
                stage.setX(-7);
                stage.setY(0);
                stage.setScene(scene);
                stage.show();
            }
        }
        else if(choice.equals("Edit Profile"))
        {
            newEmail.setVisible(true);
            newEmail.setPromptText(this.email.getText());
            newFirstname.setVisible(true);
            newFirstname.setPromptText(this.firstname);
            newLastname.setVisible(true);
            newLastname.setPromptText(this.lastname);
            updateButton.setVisible(true);
            String email = newEmail.getText();
            String fname = newFirstname.getText();
            String lname = newLastname.getText();
            if (!email.isEmpty()) {
                SignIn.user5.setEmail(email);
                this.email.setText(email);
            }
            if (!(fname.isEmpty() || lname.isEmpty())) {
                SignIn.user5.setFirst_Name(fname);
                SignIn.user5.setLast_Name(lname);
                fullname.setText(fname + " " + lname);
            }
            else if(choice.equals(" Delete Movies List")){
                if (fname.isEmpty() && !lname.isEmpty()) {
                    SignIn.user5.setLast_Name(lname);
                    fullname.setText(this.firstname + " " + lname);
                }
                else if (!fname.isEmpty() && lname.isEmpty()) {
                    SignIn.user5.setFirst_Name(fname);
                    fullname.setText(fname + " " + this.lastname);
                }
            }
            updateButton.setOnAction(event1 -> {
                Alert alert_ = new Alert(Alert.AlertType.CONFIRMATION);
                alert_.setContentText("Profile Updated!");
                alert_.showAndWait();
                try {
                    Select( event);
                    updateButton.setVisible(false);
                    newLastname.setVisible(false);
                    newFirstname.setVisible(false);
                    newEmail.setVisible(false);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        //Delete_Recorded_Movies
        else
        {
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == buttonTypeYes)
            {
                SignIn.user5.Movies_For_Later.clear();
                toWatchHbox.getChildren().clear();
            }
        }
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
            controller.setUser(SignIn.user5);
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setX(-7);
        stage.setY(0);
        stage.setScene(scene);
        stage.show();
    }
    private void addWatchedList(UserWatchRecord movie1){
        VBox movieContainer = new VBox(10);
        movieContainer.setPrefWidth(300);
        movieContainer.setPrefHeight(200);
        Image image = new Image(getClass().getResourceAsStream(movie1.getMovie().getPoster_path()));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(237);
        imageView.setFitWidth(220);
        Label label = new Label(movie1.getMovie().getTitle());
        label.setStyle("-fx-text-size: 20; -fx-text-fill: white;");
        label.setOnMouseEntered(event -> label.setStyle("-fx-text-size: 20; -fx-text-fill: #FFC107;"));
        label.setOnMouseExited(event -> label.setStyle("-fx-text-size: 20; -fx-text-fill: white;"));
        Label label1 =new Label("Rate: "+movie1.getRating());
        label1.setStyle("-fx-text-size: 20; -fx-text-fill: white;");
        label1.setOnMouseEntered(event -> label1.setStyle("-fx-text-size: 20; -fx-text-fill: #FFC107;"));
        label1.setOnMouseExited(event -> label1.setStyle("-fx-text-size: 20; -fx-text-fill: white;"));
        Image image1 = new Image(getClass().getResourceAsStream("assets/fullStar.png"));
        ImageView imageView1 = new ImageView(image1);
        imageView1.setFitHeight(18);
        imageView1.setFitWidth(20);
        HBox box =new HBox(label1,imageView1,label);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(6);
        movieContainer.setAlignment(Pos.CENTER);
        movieContainer.getChildren().addAll(imageView, box);
        movieContainer.setOnMouseClicked(event -> {
            try {
                onMouseClickedVBox(event,movie1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        watchedHbox.getChildren().addAll(movieContainer);
    }
    private void addToWatchedList(Movie movie1){
        VBox movieContainer = new VBox(10);
        movieContainer.setPrefWidth(300);
        movieContainer.setPrefHeight(200);
        Image image = new Image(getClass().getResourceAsStream(movie1.getPoster_path()));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(237);
        imageView.setFitWidth(220);
        Label label = new Label(movie1.getTitle());
        label.setStyle("-fx-text-size: 20; -fx-text-fill: white;");
        label.setOnMouseEntered(event -> label.setStyle("-fx-text-size: 20; -fx-text-fill: #FFC107;"));
        label.setOnMouseExited(event -> label.setStyle("-fx-text-size: 20; -fx-text-fill: white;"));
        movieContainer.setAlignment(Pos.CENTER);
        movieContainer.getChildren().addAll(imageView,label);
        movieContainer.setOnMouseClicked(event -> {
            try {
                onMouseClickedVBox(event,movie1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        toWatchHbox.getChildren().addAll(movieContainer);
    }
    public void onMouseClickedVBox(MouseEvent act, Movie movie1) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("movie-view.fxml"));
        root = loader.load();
        stage = (Stage)((Node)act.getSource()).getScene().getWindow();
        MovieController controller=loader.getController();
        controller.setStage(stage);
        controller.setMovie(movie1);
        controller.watchMovie(movie1);
        controller.disableButtons();
        controller.setUser(SignIn.user5);
        controller.page5=-1;

        scene = new Scene(root);
        stage.setScene(scene);

        Image image = new Image(getClass().getResourceAsStream(movie1.getPoster_path()));
        controller.refreshScreen("Watch Movie "+ movie1.getTitle() + "("+movie1.getRelease_date().getYear()+")", movie1.getTitle(),
                movie1.getTitle()+" Translated",movie1.getGenres(), movie1.getDescription(),
                movie1.getRunning_time(), image,movie1.getDirector().getFirst_Name()+" "+movie1.getDirector().getSecond_Name(),movie1.getCastNames());
        stage.setScene(scene);
        stage.show();

    }
    public void onMouseClickedVBox(MouseEvent act, UserWatchRecord movie1) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("movie-view.fxml"));
        root = loader.load();
        stage = (Stage)((Node)act.getSource()).getScene().getWindow();
        MovieController controller=loader.getController();
        controller.setStage(stage);
        controller.setMovie(movie1);
        controller.watchMovie(movie1.getMovie());
        controller.setUser(SignIn.user5);
        controller.disableButtons();
        controller.disableWatch();
        controller.page5=-1;
        for(UserWatchRecord userWatchRecord1:SignIn.user5.Watched_Movies){
            if(userWatchRecord1.getMovie().equals(movie1.getMovie())){
                controller.setStars(userWatchRecord1);
            }
        }
        scene = new Scene(root);
        stage.setScene(scene);

        Image image = new Image(getClass().getResourceAsStream(movie1.getMovie().getPoster_path()));
        controller.refreshScreen("Watch Movie "+ movie1.getMovie().getTitle() + "("+movie1.getMovie().getRelease_date().getYear()+")", movie1.getMovie().getTitle(),
                movie1.getMovie().getTitle()+" Translated",movie1.getMovie().getGenres(), movie1.getMovie().getDescription(),
                movie1.getMovie().getRunning_time(), image,movie1.getMovie().getDirector().getFirst_Name()+" "+movie1.getMovie().getDirector().getSecond_Name(),movie1.getMovie().getCastNames());
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
        profilePictue.setFitHeight(100);
        profilePictue.setFitWidth(100);
        username.setText(SignIn.user5.getUser_Name());
        email.setText(SignIn.user5.getEmail());
        plan.setText("Current Plan :" + " " +  SignIn.user5.getPlan().toUpperCase());
        fullname.setText(SignIn.user5.getFirst_Name() + " " + SignIn.user5.getLast_Name());
        firstname = SignIn.user5.getFirst_Name();
        lastname = SignIn.user5.getLast_Name();
    }
    public void setdatatoAdmin(User user){
        isAdmin = true;
        Image image = new Image(getClass().getResourceAsStream(user.getProfilePath()));
        profilePictue.setImage(image);
        profilePictue.setFitHeight(100);
        profilePictue.setFitWidth(100);
        username.setText(user.getUser_Name());
        email.setText(user.getEmail());
        plan.setText(user.getPlan());
        fullname.setText(user.getFirst_Name() + " " + user.getLast_Name());
        firstname = user.getFirst_Name();
        lastname = user.getLast_Name();
        setUser(SignIn.user5);
    }
    public void setUser(User user){
        this.user = user;

    }
    @FXML
    void toMoviesrecord(){
        switchPane(mainpane);
    }
    private void switchPane(AnchorPane anchorPane){
        mainPane.setCenter(anchorPane);
    }
    @FXML
    void toPlans()throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("recorded-movies.fxml"));
        Parent root = loader.load();
        switchPane((AnchorPane) root);
        ChoosePlanController controller = loader.getController();
        controller.setUser(SignIn.user5);
    }
    @FXML
    void toMovies()throws IOException{
        switchPane(mainpane);
    }
}