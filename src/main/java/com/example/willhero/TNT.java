package com.example.willhero;

import java.util.Timer;

public class TNT extends Obstacle{
    private int numSecondsToDetonate;
    private boolean isCollided;
    private static Timer clock;
    TNT(int x,int y, String imageURL){
        super(x,y,45,45,true,imageURL);
        this.isCollided = false;
    }
    public  boolean getIsCollided(){
        return  this.isCollided;
    }
    public  void explode(){
        //code tbd
    }
    public void countDown(){
        //code tbd
    }
}
