package com.example.watch_it;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.util.ArrayList;

public class AddCastController {
    public VBox buttomright;
    public VBox topright;
    public VBox buttomleft;
    public VBox topleft;
    @FXML
    private VBox mainvbox1;
    @FXML
    private VBox mainvbox2;
    @FXML
    private Button addButton;
    private VBox dataBox;
    private HBox labels;
    private VBox vbox1;
    private VBox vbox2;
    @FXML
    private Label actorName = new Label();
    @FXML
    private TextField nationalityText = new TextField();
    @FXML
    private TextField genderText = new TextField();
    @FXML
    private TextField ageText = new TextField();
    @FXML
    private Label actorName2  = new Label();
    @FXML
    private TextField nationalityText2 = new TextField();
    @FXML
    private TextField genderText2 = new TextField();
    @FXML
    private TextField ageText2 = new TextField();
    @FXML
    private Label actorName3 = new Label();
    @FXML
    private TextField nationalityText3 = new TextField();
    @FXML
    private TextField genderText3 = new TextField();
    @FXML
    private TextField ageText3 = new TextField();
    @FXML
    private Label actorName4 = new Label();
    @FXML
    private TextField nationalityText4 = new TextField();
    @FXML
    private TextField genderText4 = new TextField();
    @FXML
    private TextField ageText4 = new TextField();
    private int castNumber;
    public static boolean addedCast = false;
    ArrayList<String> castName = new ArrayList();
    ArrayList<String> movies = new ArrayList<>();
    int g = 1;
    private String movieName;

    public void setdata(ArrayList<String> newActor) {
        this.castName = newActor;
        this.castNumber = castName.size();
        setvbox();
    }

    private void setvbox() {
        switch (castNumber) {
            case 1:
                if (!(AddMovieController.newdirector==null)) {
                    buttomright.setVisible(false);
                    buttomleft.setVisible(false);
                    actorName2.setText(AddMovieController.newdirector);
                }
                else
                    topright.setVisible(false);
                    buttomright.setVisible(false);
                    buttomleft.setVisible(false);
                actorName.setText(castName.get(0));
                break;

            case 2:
                if (!(AddMovieController.newdirector==null)) {
                    buttomright.setVisible(false);
                    actorName3.setText(AddMovieController.newdirector);
                }
                else {
                    buttomright.setVisible(false);
                    buttomleft.setVisible(false);
                }
                actorName.setText(castName.get(0));
                actorName2.setText(castName.get(1));
                break;

            default:
                if (!(AddMovieController.newdirector==null)) {
                    actorName4.setText(AddMovieController.newdirector);
                }
                else{
                    buttomright.setVisible(false);
                    actorName.setText(castName.get(0));
                    actorName2.setText(castName.get(1));
                    actorName3.setText(castName.get(2));
                }
        }

    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType,message,ButtonType.OK);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    @FXML
    public void addCast(ActionEvent event){
        if(ageText.getText() == null || genderText.getText()== null || nationalityText.getText() == null || ageText2.getText() == null || genderText2.getText() ==null || nationalityText2.getText() == null)
            showAlert("","enter all data", Alert.AlertType.ERROR);
        else if(ageText3.getText() == null || genderText3.getText()== null || nationalityText3.getText() == null || ageText4.getText() == null || genderText4.getText() ==null || nationalityText4.getText() == null)
            showAlert("","enter all data", Alert.AlertType.ERROR);
        else {
            addedCast = true;
        }

    }
    @FXML
    public void getCastData(){

        movieName = AddMovieController.AddedMovie.getTitle();
        ArrayList<Cast> allCast = new ArrayList<>();
        movies.add(movieName);

     //   System.out.println("----------------------------------");
        switch (castNumber) {
            case 3:
                String []name3 = castName.get(2).split(" ");
                Cast cast3 = new Cast();
                cast3.add(name3[0],name3[1],Integer.parseInt(ageText3.getText()),genderText3.getText(),nationalityText3.getText(),movies);
                allCast.add(cast3);

            case 2:
                String []name2 = castName.get(1).split(" ");
                Cast cast2 = new Cast();
                cast2.add(name2[0],name2[1],Integer.parseInt(ageText2.getText()),genderText2.getText(),nationalityText2.getText(),movies);
                allCast.add(cast2);
                System.out.println(genderText2.getText());
            default:
                String []name = castName.get(0).split(" ");
                Cast cast = new Cast();
                cast.add(name[0],name[1],Integer.parseInt(ageText.getText()),genderText.getText(),nationalityText.getText(),movies);
                allCast.add(cast);
                System.out.println(genderText.getText());
        }
        if (AddMovieController.newdirector==null)
            showAlert("","data added", Alert.AlertType.CONFIRMATION);
        else
            getDirectorData();
    }

    private void getDirectorData() {

        Director newDirector = new Director();
        String []name = AddMovieController.newdirector.split(" ");

        switch (castNumber) {
            case 3:
                newDirector.add(name[0],name[1],Integer.parseInt(ageText4.getText()),genderText4.getText(),nationalityText4.getText(),movies);
                break;
            case 2:
                newDirector.add(name[0],name[1],Integer.parseInt(ageText3.getText()),genderText3.getText(),nationalityText3.getText(),movies);
                break;
            default:
                newDirector.add(name[0],name[1],Integer.parseInt(ageText2.getText()),genderText2.getText(),nationalityText2.getText(),movies);
        }
        AddMovieController.AddedMovie.setDirector(Admin.getDirector(AddMovieController.newdirector));
        Movie.allmovies.add(AddMovieController.AddedMovie);
        showAlert("","data added", Alert.AlertType.CONFIRMATION);
        System.out.println( AddMovieController.AddedMovie.getDirector().getFirst_Name());
    }

    /*public void getData(){
        System.out.println("in add data");
        String movieName =AddMovieController.AddedMovie.getTitle();
        ArrayList<Cast> allCast = new ArrayList<>();
        ArrayList<String> movies = new ArrayList<>();
        movies.add(movieName);
        System.out.println(g);
        //one cast to add
        if(g >= 2){
            String []name = castName.get(0).split(" ");
            Cast cast = new Cast();
            System.out.println(ageText.getText());
            ageText.setOnMouseClicked(event -> System.out.println("aaaaaaaa"));
            cast.add(name[0],name[1],Integer.parseInt(ageText.getText()),genderText.getText(),nationalityText.getText(),movies);
            allCast.add(cast);
        }
        ageText2.setOnMouseClicked(event -> System.out.println("bbbbbbbbbbbbb"));

        //two actors to add
        if(g >= 3){
            String []name = castName.get(1).split(" ");
            Cast cast = new Cast();
            System.out.println(ageText2.getText());
            cast.add(name[0],name[1],Integer.parseInt(ageText2.getText()),genderText2.getText(),nationalityText2.getText(),movies);
            allCast.add(cast);
        }
        //three actors to add
        if(g >= 4){
            String []name = castName.get(2).split(" ");
            Cast cast = new Cast();
            ageText2.setOnMouseClicked(event -> System.out.println("ccccccc"));
            System.out.println(ageText3.getText());
            cast.add(name[0],name[1],Integer.parseInt(ageText3.getText()),genderText3.getText(),nationalityText3.getText(),movies);
            allCast.add(cast);
        }
        //one director to add
        if(g >= 5 ){
            String []name = castName.get(3).split(" ");
            Cast cast = new Cast();
            ageText3.setOnMouseClicked(event -> System.out.println("dddddddddd"));
            System.out.println(ageText4.getText());
            cast.add(name[0],name[1],Integer.parseInt(ageText4.getText()),genderText4.getText(),nationalityText4.getText(),movies);
            allCast.add(cast);
        }
        showAlert("","Data Added", Alert.AlertType.CONFIRMATION);
        System.out.println("done");
        AddMovieController.AddedMovie.setCast(allCast);
        Movie.allmovies.add(AddMovieController.AddedMovie);
    }
*/
    @FXML
    void onMouseEntered(){
        addButton.setOnMouseEntered(event -> addButton.setStyle("-fx-background-color: #FFC107; -fx-background-radius: 10;"));
    }
    @FXML
    void onMouseExit(){
        addButton.setOnMouseExited(event -> addButton.setStyle("-fx-background-color: FFC107; -fx-background-radius: 10;"));
    }
}