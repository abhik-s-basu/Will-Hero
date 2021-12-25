package com.example.willhero;

public abstract class GameObject {
    private double xCoordinate;
    private double yCoordinate;
    private double xSpeed;
    private double ySpeed;
    private double length;
    private double breadth;
    private boolean moves;

    GameObject(double x,double y,double _xSpeed , double _ySpeed , double length, double breadth,boolean moves){
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.xSpeed = _xSpeed;
        this.ySpeed = _ySpeed;
        this.length = length;
        this.breadth = breadth;
        this.moves = moves;
    }
    public double getX(){
        return this.xCoordinate;
    }
    public double getY(){
        return this.yCoordinate;
    }
    public void setX (double x){
        this.xCoordinate = x;
    }
    public  void setY (double y){
        this.yCoordinate = y;
    }

    public double getXSpeed() {
        return xSpeed;
    }

    public double getYSpeed() {
        return ySpeed;
    }

    public void setXSpeed(double xSpeed) {
        this.xSpeed = xSpeed;
    }

    public void setYSpeed(double ySpeed) {
        this.ySpeed = ySpeed;
    }

    public double getLength() {
        return length;
    }

    public double getBreadth() {
        return breadth;
    }
    public void setLength(double length){
        this.length = length;
    }

    public void setBreadth(double breadth) {
        this.breadth = breadth;
    }

}