package com.example.watch_it;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URL;
import java.time.Month;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class AllMoviesController {

    @FXML
    private Label month;
    @FXML
    private VBox leftCol;
    @FXML
    private  VBox rightCol;
    @FXML
    private  VBox middleCol1;
    @FXML
    private  VBox middleCol2;
    @FXML
    private BorderPane mainPane;
    @FXML
    private ScrollPane mainScroll;
    @FXML
    TextField searchField;
    @FXML
    private Button Back;
    @FXML
    private Button addCastButton;
    private Stage stage;
    private Scene scene;
    private Parent root;
    static boolean enteredData = false;
    static boolean addMovie = false;
    @FXML
    public void initialize() {
        if(enteredData)
            addCastButton.setDisable(false);
        for (int i=0 ; i<Movie.allmovies.size(); i++){
            addMovies(Movie.allmovies.get(i), i);
        }
        Month month1 =Admin.monthWithMostRevenue();
        month.setText("Month with most revenue: "+month1.name());
    }

    @FXML
    public void toAddMovies(ActionEvent event) throws IOException {

        Parent fxml = FXMLLoader.load(getClass().getResource("add-movies.fxml"));
        mainPane.setCenter(fxml);
        addCastButton.setVisible(true);
        month.setVisible(false);
    }

    @FXML void toMovies(ActionEvent event){
        switchPane(mainScroll);
        addCastButton.setVisible(false);
        month.setVisible(false);
    }

    @FXML void toAllUsers(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("all-users.fxml"));
        switchPane((ScrollPane) fxml);
        addCastButton.setVisible(false);
        month.setVisible(false);

    }
    @FXML void toAddUser(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("add-users.fxml"));
        switchPane((ScrollPane) fxml);
        addCastButton.setVisible(false);
        month.setVisible(false);
    }
    @FXML
    void toPlans(ActionEvent event)throws IOException{
        Parent fxml = FXMLLoader.load(getClass().getResource("Plans.fxml"));
        switchPane((ScrollPane) fxml);
        addCastButton.setVisible(false);
        month.setVisible(true);
    }
    public void onMouseClickedMovie(MouseEvent act, Movie movie) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("movie-view.fxml"));
        root = loader.load();
        stage = (Stage)((Node)act.getSource()).getScene().getWindow();
        MovieController controller=loader.getController();
        controller.setStage(stage);
        controller.Admin(movie);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Image image = new Image(getClass().getResourceAsStream(movie.getPoster_path()));
        controller.refreshScreen("Watch Movie "+ movie.getTitle() + "("+movie.getRelease_date().getYear()+")", movie.getTitle(),
                movie.getTitle()+" Translated",movie.getGenres(), movie.getDescription(),
                movie.getRunning_time(), image,movie.getDirector().getFirst_Name()+" "+movie.getDirector().getSecond_Name(),movie.getCastNames());
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    public void backScenes(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("first-page.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void onMouseEntered(){
        Back.setOnMouseEntered(event -> Back.setStyle("-fx-background-color: #FFC107; -fx-background-radius: 10; -fx-border-color: white;"));
    }
    @FXML
    void onMouseExit(){
        Back.setOnMouseExited(event -> Back.setStyle("-fx-background-color: black; -fx-background-radius: 10; -fx-border-color: white;"));
    }
    private void addMovies(Movie movie, int i){
        VBox movieContainer = new VBox(10);
        movieContainer.setStyle("-fx-background-radius:40;");

        Button movieName = new Button(movie.getTitle());
        movieName.setStyle("-fx-background-color: transparent; -fx-color:white; -fx-font-color:white; -fx-text-fill: #C2C2C2; -fx-background-radius:50;-fx-font-size: 20;\n" +
                "    -fx-text-fill: #C2C2C2;");

        Image image = new Image(getClass().getResourceAsStream(movie.getPoster_path()));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(260);
        imageView.setFitHeight(320);

        //movieContainer.setStyle("-fx-background-color:#262626;  -fx-background-radius: 20;");
        movieContainer.setPrefWidth(320);
        movieContainer.setAlignment(Pos.CENTER);
        movieContainer.getChildren().addAll(imageView, movieName);
        movieContainer.setPadding(new Insets(10 ,0 , 10 , 5));

        //VBox.setMargin(movieContainer,new Insets(20 ,5 , 0 , 5));
        int check = i %4;
        if( check == 0)
            leftCol.getChildren().addAll(movieContainer);
        else if (check == 1) {
            middleCol1.getChildren().addAll(movieContainer);
        }
        else if (check == 2) {
            middleCol2.getChildren().addAll(movieContainer);
        }
        else
            rightCol.getChildren().addAll(movieContainer);

        movieContainer.setOnMouseClicked(event -> {
            try {
                onMouseClickedMovie(event, movie);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    @FXML
    void toAddCast(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("add-cast.fxml"));
        root = loader.load();
        AddCastController controller = loader.getController();
        controller.setdata(AddMovieController.newActor);
        if(enteredData)
        {
            addCastButton.setVisible(false);
            switchPane((ScrollPane) root);
            addCastButton.setText("Add");
        }

    }
    public void switchPane(ScrollPane scrollPane){
        mainPane.setCenter(scrollPane);
    }
    @FXML
    private void Search(){
        String name = searchField.getText();
        leftCol.getChildren().clear();
        middleCol1.getChildren().clear();
        middleCol2.getChildren().clear();
        rightCol.getChildren().clear();
        for (Movie movie : Movie.allmovies) {
            if (name.equalsIgnoreCase(movie.getTitle())) {
                addMovies(movie, 0);
            }
        }
        if (name.isEmpty()) {
            initialize();
        }
    }
    public void setStage(Stage stage){
        this.stage = stage;
    }
}