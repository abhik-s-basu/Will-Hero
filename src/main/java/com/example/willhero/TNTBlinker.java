package com.example.willhero;

public class TNTBlinker extends Obstacle{
    private TNT parent;
    TNTBlinker(int x,int y,String imageURL){
        super(x,y,45 , 45,false, imageURL);
    }

    public void setParent(TNT p){
        this.parent = p;
    }


}
