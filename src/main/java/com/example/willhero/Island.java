package com.example.willhero;

import javafx.animation.KeyFrame;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Island extends GameObject{

    private boolean isFloating;
    Island (int x, int y, int length,
            int breadth,boolean isFloating,String imageURL){
        super(x,y,0,0,length,breadth,false,imageURL);
        this.isFloating = isFloating;
//        this.moveFloat();
//        this.display();
    }

    public void moveFloat(){
        if(isFloating){
            Timeline intro = new Timeline(new KeyFrame(Duration.millis(1), e -> {
                introTransition();
            }));
            new SequentialTransition(intro).play();
        }
    }

    private void introTransition(){
        System.out.println("lmao");
        for(Node n : getAll()){
            TranslateTransition translate1 = new TranslateTransition();
            translate1.setNode(n);
            translate1.setDuration(Duration.millis((int) (Math.random()*5000 + 2000)));
            translate1.setCycleCount(TranslateTransition.INDEFINITE);
            translate1.setByY((int) (Math.random()*10 + 20));
            translate1.setAutoReverse(true);
            translate1.play();
        }
    }
}
