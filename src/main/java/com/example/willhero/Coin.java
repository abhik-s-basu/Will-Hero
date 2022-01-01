package com.example.willhero;

public class Coin extends GameObject{
    Coin(double x,double y,double length,
               double breadth, String imageURL){
        super(x,y,0,0,length,breadth,false, imageURL);
    }
}
