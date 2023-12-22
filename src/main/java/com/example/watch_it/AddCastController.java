package com.example.watch_it;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.util.ArrayList;

public class AddCastController {
    @FXML
    private VBox mainvbox1;
    @FXML
    private VBox mainvbox2;
    private VBox dataBox;
    private HBox labels;
    private VBox vbox1;
    private VBox vbox2;
    private Label actorName;
    private Label nationalityLabel;
    private Label genderLabel;
    private Label ageLabel;
    private TextField nationalityText;
    private TextField genderText;
    private TextField ageText;
    private Separator separator;
    private int castNumber;
    public static boolean inAddCast = false;
    ArrayList<String> castName = new ArrayList();

    public void setdata(ArrayList<String> newActor) {
        System.out.println(AddMovieController.AddedMovie.getTitle());
        System.out.println(AddMovieController.AddedMovie.getDescription());
        System.out.println(newActor);
        this.castName = newActor;
        this.castNumber = castName.size();
        initialize();
    }
    void initialize(){
        inAddCast = true;
        for (int i = 1; i <= castNumber; i++) {
            System.out.println("in");
            actorName = new Label(castName.get(i-1));
            actorName.setTextFill(Color.WHITE);
            actorName.setFont(new Font("Arial Black", 28.0));

            separator = new Separator();
            separator.setOpacity(0.35);

            nationalityLabel = new Label("Nationality:  ");
            nationalityLabel.setTextFill(Color.GRAY);
            nationalityLabel.setFont(new Font(20.0));
            nationalityText = new TextField();
            nationalityText.setPrefHeight(15);
            nationalityText.setPrefWidth(220.0);
            nationalityText.setStyle("-fx-background-color: black; -fx-background-radius: 10; -fx-border-color: grey; -fx-border-radius: 10;");
            nationalityText.setFont(new Font(20.0));


            genderLabel = new Label("Gender:  ");
            genderLabel.setTextFill(Color.GRAY);
            genderLabel.setFont(new Font(20.0));
            genderText = new TextField();
            genderText.setPrefHeight(15);
            genderText.setPrefWidth(220.0);
            genderText.setStyle("-fx-background-color: black; -fx-background-radius: 10; -fx-border-color: grey; -fx-border-radius: 10;");
            genderText.setFont(new Font(20.0));


            ageLabel = new Label("Age:  ");
            ageLabel.setTextFill(Color.GRAY);
            ageLabel.setFont(new Font(20.0));
            ageText = new TextField();
            ageText.setPrefHeight(15);
            ageText.setPrefWidth(220.0);
            ageText.setStyle("-fx-background-color: black; -fx-background-radius: 10; -fx-border-color: grey; -fx-border-radius: 10;");
            ageText.setFont(new Font(20.0));

            vbox1 = new VBox();
            vbox1.getChildren().addAll(nationalityLabel,genderLabel,ageLabel);
            vbox1.setSpacing(40);

            vbox2 = new VBox();
            vbox2.getChildren().addAll(nationalityText,genderText,ageText);
            vbox2.setSpacing(20);

            labels = new HBox();
            labels.getChildren().addAll(vbox1, vbox2);

            dataBox = new VBox();
            dataBox.setSpacing(20);
            dataBox.getChildren().addAll(actorName, separator, labels);
            dataBox.setMargin(separator, new Insets(0, 200, 0, 0));

            if (i % 2 == 0)
                mainvbox2.getChildren().add(dataBox);
            else
                mainvbox1.getChildren().add(dataBox);
        }
    }
}