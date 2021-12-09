package com.example.willhero;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    @FXML
    private Group floatingIsland;
    @FXML
    private ImageView bannerName;
    @FXML
    private  ImageView hero;
    @FXML
    private  Group mainIsland;
    @FXML
    private ImageView greenOrc;
    @FXML
    private  ImageView cursor;
    @FXML
    private  Group cloud;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(floatingIsland);
        translate.setDuration(Duration.millis(5000));
        translate.setCycleCount(TranslateTransition.INDEFINITE);
//        translate.setByX(300);
        translate.setByY(25);
        translate.setAutoReverse(true);
        translate.play();

        TranslateTransition translate4 = new TranslateTransition();
        translate4.setNode(cloud);
        translate4.setDuration(Duration.millis(20000));
        translate4.setCycleCount(TranslateTransition.INDEFINITE);
//        translate.setByX(300);
        translate4.setByX(700);
        translate4.setAutoReverse(false);
        translate4.play();

        RotateTransition rotate = new RotateTransition();
        rotate.setNode(bannerName);
        rotate.setDuration(Duration.millis(2000));
        rotate.setCycleCount(TranslateTransition.INDEFINITE);
        rotate.setInterpolator(Interpolator.EASE_OUT);
        rotate.setByAngle(90);
        rotate.setAutoReverse(true);
        rotate.setAxis(Rotate.Y_AXIS);
        rotate.play();

        TranslateTransition translate1 = new TranslateTransition();
        translate1.setNode(mainIsland);
        translate1.setDuration(Duration.millis(2000));
        translate1.setCycleCount(TranslateTransition.INDEFINITE);
//        translate.setByX(300);
        translate1.setByY(15);
        translate1.setAutoReverse(true);
        translate1.play();

        TranslateTransition translate2 = new TranslateTransition();
        translate2.setNode(hero);
        translate2.setDuration(Duration.millis(550));
        translate2.setCycleCount(TranslateTransition.INDEFINITE);
//        translate.setByX(300);
        translate2.setByY(-60);
        translate2.setAutoReverse(true);
        translate2.play();

        TranslateTransition translate3 = new TranslateTransition();
        translate3.setNode(greenOrc);
        translate3.setDelay(Duration.millis(50));
        translate3.setDuration(Duration.millis(700));
        translate3.setCycleCount(TranslateTransition.INDEFINITE);
//        translate.setByX(300);
        translate3.setByY(-75);
        translate3.setAutoReverse(true);
        translate3.play();

        ScaleTransition scale = new ScaleTransition();
        scale.setNode(cursor);
        scale.setDuration(Duration.millis(1000));
        scale.setCycleCount(TranslateTransition.INDEFINITE);
        scale.setInterpolator(Interpolator.LINEAR);
        scale.setByX(0.2);
        scale.setByY(0.2);
//        scale.setAutoReverse(true);
        scale.play();





    }
}
