package com.example.willhero;

public abstract class Obstacle extends GameObject {
    Obstacle(int x,int y,int length,int breadth,boolean isMoving, String imageURL){
        super(x,y,20,0,length,breadth,isMoving,imageURL);
    }
}
