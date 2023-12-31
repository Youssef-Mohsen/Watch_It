package com.example.watch_it;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.time.Month;


public class PlansController {
    //----------------NOTE----------------------
    //add the month with most revenue
    //-----------------------------------
    @FXML
    private Label basicMovieNo;

    @FXML
    private Label basicPrice;

    @FXML
    private Label basicResolution;

    @FXML
    private Label basicRevenue;

    @FXML
    private Label basicTotalUser;
    @FXML
    private ImageView basicImage;

    @FXML
    private ScrollPane plansPane;

    @FXML
    private Label premiumMovieNo;

    @FXML
    private Label premiumPrice;

    @FXML
    private Label premiumResolution;

    @FXML
    private Label premiumRevenue;

    @FXML
    private Label premiumtotalUser;
    @FXML
    private ImageView premiumImage;

    @FXML
    private Label standardMovieNo;

    @FXML
    private Label standardPrice;


    @FXML
    private Label standardResolution;

    @FXML
    private Label standardRevenue;

    @FXML
    private Label standardTotalUser;
    @FXML
    private ImageView standardImage;

    private final int PRICE = 0;
    private final int USERNUMBER = 1;
    private final int REVENUE = 2;
    private final int MOVIENUMBER = 3;
    private final int RESOLUTION = 4;
    private final int STYLEDIMAGE = 5;
    private final int NORMALIMAGE = 6;
    @FXML
    private void initialize(){
        System.out.println(Integer.toString(Admin.basicPlanCounter));
        setNormalStyle(basicPrice,basicTotalUser,basicMovieNo,basicResolution,basicRevenue,basicImage,"basic");
        setNormalStyle(standardPrice,standardTotalUser,standardMovieNo,standardResolution,standardRevenue,standardImage,"standard");
        setNormalStyle(premiumPrice,premiumtotalUser,premiumMovieNo,premiumResolution,premiumRevenue,premiumImage,"premium");
        basicImage.setFitWidth(270);
        standardImage.setFitWidth(270);
        premiumImage.setFitWidth(270);
        basicImage.setFitHeight(170);
        standardImage.setFitHeight(170);
        premiumImage.setFitHeight(170);

    }

    private String[] basic = {"100 EGP",String.valueOf(Admin.basicPlanCounter), String.valueOf(Admin.basicPlanCounter * 100),"5","720p","assets/basicPressed.png","assets/basicNormal.png"};
    private String[] standard = {"200 EGP",String.valueOf(Admin.standardPlanCounter), String.valueOf(Admin.standardPlanCounter * 200),"10","1080p","assets/standardPressed.png","assets/standardNormal.png"};
    private String[] premium = {"300 EGP",String.valueOf(Admin.premiumPlanCounter), String.valueOf(Admin.premiumPlanCounter * 300),"30","4K+HDR","assets/premiumPressed.png","assets/premiumNormal.png"};

    @FXML
    private void basicPlanActive(){
        setStyle("basic");
    }
    @FXML
    private void basicPlanInactive(){
        setStyle("none");
    }
    @FXML
    private void standardPlanActive(){
        setStyle("standard");
    }
    @FXML
    private void standardPlanInactive(){
        setStyle("none");
    }

    @FXML
    private void premiumPlanActive(){
        setStyle("premium");
    }
    @FXML
    private void premiumPlanInactive(){
        setStyle("none");
    }
    private void setStyle(String plan){
        if(plan.equals("basic")){
            setStyle(basicPrice,basicTotalUser,basicMovieNo,basicResolution,basicRevenue,basicImage,"basic");
            setNormalStyle(standardPrice,standardTotalUser,standardMovieNo,standardResolution,standardRevenue,standardImage,"standard");
            setNormalStyle(premiumPrice,premiumtotalUser,premiumMovieNo,premiumResolution,premiumRevenue,premiumImage,"premium");
        }
        else if(plan.equals("standard")){
            setStyle(standardPrice,standardTotalUser,standardMovieNo,standardResolution,standardRevenue,standardImage,"standard");
            setNormalStyle(premiumPrice,premiumtotalUser,premiumMovieNo,premiumResolution,premiumRevenue,premiumImage,"premium");
            setNormalStyle(basicPrice,basicTotalUser,basicMovieNo,basicResolution,basicRevenue,basicImage,"basic");
        }
        else if(plan.equals("premium")){
            setStyle(premiumPrice,premiumtotalUser,premiumMovieNo,premiumResolution,premiumRevenue,premiumImage,"premium");
            setNormalStyle(standardPrice,standardTotalUser,standardMovieNo,standardResolution,standardRevenue,standardImage,"standard");
            setNormalStyle(basicPrice,basicTotalUser,basicMovieNo,basicResolution,basicRevenue,basicImage,"basic");

        }
        else{
            setNormalStyle(basicPrice,basicTotalUser,basicMovieNo,basicResolution,basicRevenue,basicImage,"basic");
            setNormalStyle(standardPrice,standardTotalUser,standardMovieNo,standardResolution,standardRevenue,standardImage,"standard");
            setNormalStyle(premiumPrice,premiumtotalUser,premiumMovieNo,premiumResolution,premiumRevenue,premiumImage,"premium");
        }
    }
    private void setNormalStyle(Label price, Label users, Label movie, Label resolution, Label revenue, ImageView image, String plan){
        labelNormalStyle(price);
        labelNormalStyle(users);
        labelNormalStyle(movie);
        labelNormalStyle(resolution);
        labelNormalStyle(revenue);
        if(plan.equals("basic"))
        {
            Image image1 = new Image(getClass().getResourceAsStream(basic[NORMALIMAGE]));
            image.setImage(image1);
        } else if (plan.equals("standard")) {
            Image image1 = new Image(getClass().getResourceAsStream(standard[NORMALIMAGE]));
            image.setImage(image1);
        }else{
            Image image1 = new Image(getClass().getResourceAsStream(premium[NORMALIMAGE]));
            image.setImage(image1);
        }
        setData();
    }
    private void setStyle(Label price, Label users, Label movie, Label resolution, Label revenue, ImageView image, String plan){
        labelStyled(price);
        labelStyled(users);
        labelStyled(movie);
        labelStyled(resolution);
        labelStyled(revenue);
        if(plan.equals("basic"))
        {
            Image image1 = new Image(getClass().getResourceAsStream(basic[STYLEDIMAGE]));
            image.setImage(image1);
        } else if (plan.equals("standard")) {
            Image image1 = new Image(getClass().getResourceAsStream(standard[STYLEDIMAGE]));
            image.setImage(image1);
        }else{
            Image image1 = new Image(getClass().getResourceAsStream(premium[STYLEDIMAGE]));
            image.setImage(image1);
        }
        setData();
    }

    private void labelNormalStyle(Label label){
        label.setStyle("-fx-text-fill:WHITE; -fx-font-size: 30;");
    }
    private void labelStyled(Label label){
        label.setStyle("-fx-text-fill:#FFC107; -fx-font-size: 30;");

    }

    private void setData() {
        basicPrice.setText(basic[PRICE]);
        basicMovieNo.setText(basic[MOVIENUMBER]);
        basicResolution.setText(basic[RESOLUTION]);
        basicRevenue.setText(basic[REVENUE]);
        basicTotalUser.setText(basic[USERNUMBER]);

        standardPrice.setText(standard[PRICE]);
        standardMovieNo.setText(standard[MOVIENUMBER]);
        standardResolution.setText(standard[RESOLUTION]);
        standardRevenue.setText(standard[REVENUE]);
        standardTotalUser.setText(standard[USERNUMBER]);

        premiumPrice.setText(premium[PRICE]);
        premiumMovieNo.setText(premium[MOVIENUMBER]);
        premiumResolution.setText(premium[RESOLUTION]);
        premiumRevenue.setText(premium[REVENUE]);
        premiumtotalUser.setText(premium[USERNUMBER]);
    }
}
