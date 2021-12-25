package com.example.willhero;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class GameController {

//    public void clickChest(MouseEvent event){ //will be changed later
//        Image tempImage;
//        if (!chestClickCount){
//            tempImage = new Image("file:src/main/resources/Assets/Chests/openChest.png");
//            chest.setImage(tempImage);
//        }
//        else{
//            tempImage = new Image("file:src/main/resources/Assets/Chests/closedChest.png");
//            chest.setImage(tempImage);
//        }
//        chestClickCount = !chestClickCount;
//    }
//
//    public void clickTNT(MouseEvent event){ //will be changed later
//        FadeTransition fade1 = new FadeTransition();
//        fade1.setNode(tnt);
//        fade1.setDuration(Duration.millis(250));
//        fade1.setCycleCount(10);
//        fade1.setFromValue(1.0);
//        fade1.setToValue(0.0);
//        fade1.setInterpolator(Interpolator.LINEAR);
//        fade1.play();
//        fade1.setOnFinished((e1)->{
//            explosionSquare.setVisible(false);
//            FadeTransition fade2 = new FadeTransition();
//            tntSmoke.setVisible(true);
//            fade2.setNode(tntSmoke);
//            fade2.setDuration(Duration.millis(2000));
//            fade2.setCycleCount(1);
//            fade2.setFromValue(1.0);
//            fade2.setToValue(0.0);
//            fade2.play();
//            fade2.setOnFinished((e2)->{
//                tnt.setOpacity(1.0);
//                tnt.setVisible(true);
//                explosionSquare.setVisible(true);
//                tntSmoke.setVisible(false);
//            });
//        });
//    }
}
