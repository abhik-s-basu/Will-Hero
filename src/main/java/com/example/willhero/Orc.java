package com.example.willhero;

public abstract class Orc extends  GameObject {
    private  String color;
//    private  int size;
    private  int coinsOnKill;
    private int health; //looks useless rn
    private boolean isAlive;
    Orc(double x, double y,double length, double breadth,double xSpeed,
        double ySpeed, String color, int coinsOnKill, int health,String imageURL){
        super(x,y,xSpeed,ySpeed,length, breadth,true,imageURL);
        this.display();
        this.isAlive = true;
        this.coinsOnKill = coinsOnKill;
    }
    public int getCoinsOnKill(){
        return this.coinsOnKill;
    }
    public void killOrc(){
        //code tbd;
    }
    public void jumpInPlace(){
        //code tbd
    }

}
