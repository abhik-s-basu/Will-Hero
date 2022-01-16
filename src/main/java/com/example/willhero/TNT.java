package com.example.willhero;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.util.Duration;

public class TNT extends Obstacle{
    private int numSecondsToDetonate;
    private boolean isCollided;
    TNTBlinker blinker;
    TNTSmoke smoke;
    TNT(int x,int y, String imageURL){
        super(x,y,45,45,true,imageURL);
        this.isCollided = false;
    }

    public void setBlinker(TNTBlinker tb){
        this.blinker = tb;
    }

    public void setSmoke(TNTSmoke ts){
        this.smoke = ts;
        ts.getNode().setVisible(false);
    }

    public  boolean getIsCollided(){
        return  this.isCollided;
    }
    public  void explode(){
        this.isCollided = true;
        FadeTransition fade1 = new FadeTransition();
        fade1.setNode(this.getNode());
        fade1.setDuration(Duration.millis(250));
        fade1.setCycleCount(10);
        fade1.setFromValue(1.0);
        fade1.setToValue(0.0);
        fade1.setInterpolator(Interpolator.LINEAR);
        fade1.play();
        fade1.setOnFinished((e1)->{
            smoke.setSmoke(true);
            blinker.getNode().setVisible(false);
            FadeTransition fade2 = new FadeTransition();
            smoke.getNode().setVisible(true);
            fade2.setNode(smoke.getNode());
            fade2.setDuration(Duration.millis(2000));
            fade2.setCycleCount(1);
            fade2.setFromValue(1.0);
            fade2.setToValue(0.0);
            fade2.play();
            fade2.setOnFinished((e2)->{
                smoke.setSmoke(false);
                this.getNode().setVisible(false);
                smoke.getNode().setVisible(false);
                blinker.getNode().setVisible(false);
            });
        });
    }

    public void countDown(){

    }
}
