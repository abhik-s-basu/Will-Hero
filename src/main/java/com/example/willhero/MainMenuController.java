package com.example.willhero;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
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
    @FXML
    private Group quitScreen;
    @FXML
    private Group savedGameScreen;

    private void translateFloatingIsland(){
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(floatingIsland);
        translate.setDuration(Duration.millis(5000));
        translate.setCycleCount(TranslateTransition.INDEFINITE);
        translate.setByY(25);
        translate.setAutoReverse(true);
        translate.play();
    }

    private void translateCloud(){
        TranslateTransition translate4 = new TranslateTransition();
        translate4.setNode(cloud);
        translate4.setDuration(Duration.millis(20000));
        translate4.setCycleCount(TranslateTransition.INDEFINITE);
        translate4.setByX(900);
        translate4.setAutoReverse(false);
        translate4.setInterpolator(Interpolator.LINEAR);
        translate4.play();
    }

    private void rotateBannerName(){
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(bannerName);
        rotate.setDuration(Duration.millis(2000));
        rotate.setCycleCount(TranslateTransition.INDEFINITE);
        rotate.setInterpolator(Interpolator.EASE_OUT);
        rotate.setByAngle(90);
        rotate.setAutoReverse(true);
        rotate.setAxis(Rotate.Y_AXIS);
        rotate.play();
    }

    private void translateMainIsland(){
        TranslateTransition translate1 = new TranslateTransition();
        translate1.setNode(mainIsland);
        translate1.setDuration(Duration.millis(2000));
        translate1.setCycleCount(TranslateTransition.INDEFINITE);
        translate1.setByY(15);
        translate1.setAutoReverse(true);
        translate1.play();
    }

    private void translateHero(){
        TranslateTransition translate2 = new TranslateTransition();
        translate2.setNode(hero);
        translate2.setDuration(Duration.millis(550));
        translate2.setCycleCount(TranslateTransition.INDEFINITE);
        translate2.setByY(-60);
        translate2.setAutoReverse(true);
        translate2.play();
    }

    private void translateGreenOrc(){
        TranslateTransition translate3 = new TranslateTransition();
        translate3.setNode(greenOrc);
        translate3.setDelay(Duration.millis(50));
        translate3.setDuration(Duration.millis(700));
        translate3.setCycleCount(TranslateTransition.INDEFINITE);
        translate3.setByY(-75);
        translate3.setAutoReverse(true);
        translate3.play();
    }

    private void scaleCursor(){
        ScaleTransition scale = new ScaleTransition();
        scale.setNode(cursor);
        scale.setDuration(Duration.millis(1000));
        scale.setCycleCount(TranslateTransition.INDEFINITE);
        scale.setInterpolator(Interpolator.LINEAR);
        scale.setByX(0.2);
        scale.setByY(0.2);
        scale.play();
    }

    private void setScreen(int flag, Group screen){
        TranslateTransition translate5 = new TranslateTransition();
        translate5.setNode(screen);
        translate5.setDuration(Duration.millis(500));
        translate5.setByY(flag*(-800));
        translate5.setAutoReverse(false);
        translate5.setInterpolator(Interpolator.EASE_BOTH);
        translate5.play();
    }

    public void startGame(MouseEvent event){
        System.out.println("Start Game!");
    }

    public void pressQuit(MouseEvent event){
        setScreen(1, quitScreen);
    }

    public void pressLoadGame(MouseEvent event){
        setScreen(1, savedGameScreen);
    }

    public void exitGame(MouseEvent event){
        Platform.exit();
        System.exit(0);
    }

    public void continueGame(MouseEvent event){
        setScreen(-1, quitScreen);
    }

    public void BackToGame(MouseEvent event){
        setScreen(-1, savedGameScreen);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        translateFloatingIsland();
        translateCloud();
        rotateBannerName();
        translateMainIsland();
        translateHero();
        translateGreenOrc();
        scaleCursor();

    }
}
