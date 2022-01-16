package com.example.willhero;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML
    private Group cloud;

    @FXML
    private Group floatingIsland;

    @FXML
    private Group floatingIsland2;


    public GameController(){}

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

    private void translateIsland(Group island, int duration, int translateY){
        TranslateTransition translate1 = new TranslateTransition();
        translate1.setNode(island);
        translate1.setDuration(Duration.millis(duration));
        translate1.setCycleCount(TranslateTransition.INDEFINITE);
        translate1.setByY(translateY);
        translate1.setAutoReverse(true);
        translate1.play();
    }

    public void pressPause(MouseEvent event) throws Exception {
        Game.getInstance().pause();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        translateCloud();
        translateIsland(floatingIsland, 5000, 25);
        translateIsland(floatingIsland2, 4000, 10);
    }
}