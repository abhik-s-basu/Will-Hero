package com.example.willhero;

public abstract class Chest extends GameObject {
    Chest(double x, double y, String imageURL){
        super(x,y,20,0,50,35,true,imageURL);
        // need to check out the speeds later and make changes accordingly
    }
}
