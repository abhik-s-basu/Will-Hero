package com.example.willhero;

public class TNTSmoke extends Obstacle{
    private boolean isSmoke;
    private TNT parent;
    TNTSmoke(int x,int y, String imageURL){
        super(x,y,150, 150,false, imageURL);
        this.isSmoke = false;
    }
    public boolean getSmoke() {
        return isSmoke;
    }

    public void setSmoke(boolean smoke) {
        this.isSmoke = smoke;
    }

    public void setParent(TNT p){
        this.parent = p;
    }

}
