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
    private VBox mainVbox1;
    @FXML
    private VBox mainVbox2;
    private int castNumber;
    public static boolean inAddCast = false;
    ArrayList<String> castName = new ArrayList<>();

    public void setData(ArrayList<String> newActor) {
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
            Label actorName = new Label(castName.get(i - 1));
            actorName.setTextFill(Color.WHITE);
            actorName.setFont(new Font("Arial Black", 28.0));

            Separator separator = new Separator();
            separator.setOpacity(0.35);

            Label nationalityLabel = new Label("Nationality:  ");
            nationalityLabel.setTextFill(Color.GRAY);
            nationalityLabel.setFont(new Font(20.0));
            TextField nationalityText = new TextField();
            nationalityText.setPrefHeight(15);
            nationalityText.setPrefWidth(220.0);
            nationalityText.setStyle("-fx-background-color: black; -fx-background-radius: 10; -fx-border-color: grey; -fx-border-radius: 10;");
            nationalityText.setFont(new Font(20.0));


            Label genderLabel = new Label("Gender:  ");
            genderLabel.setTextFill(Color.GRAY);
            genderLabel.setFont(new Font(20.0));
            TextField genderText = new TextField();
            genderText.setPrefHeight(15);
            genderText.setPrefWidth(220.0);
            genderText.setStyle("-fx-background-color: black; -fx-background-radius: 10; -fx-border-color: grey; -fx-border-radius: 10;");
            genderText.setFont(new Font(20.0));


            Label ageLabel = new Label("Age:  ");
            ageLabel.setTextFill(Color.GRAY);
            ageLabel.setFont(new Font(20.0));
            TextField ageText = new TextField();
            ageText.setPrefHeight(15);
            ageText.setPrefWidth(220.0);
            ageText.setStyle("-fx-background-color: black; -fx-background-radius: 10; -fx-border-color: grey; -fx-border-radius: 10;");
            ageText.setFont(new Font(20.0));

            VBox vbox1 = new VBox();
            vbox1.getChildren().addAll(nationalityLabel, genderLabel, ageLabel);
            vbox1.setSpacing(40);

            VBox vbox2 = new VBox();
            vbox2.getChildren().addAll(nationalityText, genderText, ageText);
            vbox2.setSpacing(20);

            HBox labels = new HBox();
            labels.getChildren().addAll(vbox1, vbox2);

            VBox dataBox = new VBox();
            dataBox.setSpacing(20);
            dataBox.getChildren().addAll(actorName, separator, labels);
            VBox.setMargin(separator, new Insets(0, 200, 0, 0));

            if (i % 2 == 0)
                mainVbox2.getChildren().add(dataBox);
            else
                mainVbox1.getChildren().add(dataBox);
        }
    }
}