package com.example.willhero;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class GameObject {
    private double xCoordinate;
    private double yCoordinate;
    private int xSpeed;
    private int ySpeed;
    private double length;
    private double breadth;
    private boolean moves;
    private  String imageURL;
    private  Image image;
    private  ImageView imageView;

    GameObject(double x,double y,int _xSpeed , int _ySpeed , double length,
               double breadth,boolean moves, String imageURL){
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.xSpeed = _xSpeed;
        this.ySpeed = _ySpeed;
        this.length = length;
        this.breadth = breadth;
        this.moves = moves;
        this.imageURL = imageURL;
        this.display();
    }

    public void display(){
        image = new Image(imageURL);
        imageView = new ImageView(image);
        imageView.setX(xCoordinate);
        imageView.setY(yCoordinate);
        imageView.setFitHeight(breadth);
        imageView.setFitWidth(length);
        return;
    }
    public Node getNode(){
        return imageView;
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

    public int getXSpeed() {
        return xSpeed;
    }

    public int getYSpeed() {
        return ySpeed;
    }

    public void setXSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public void setYSpeed(int ySpeed) {
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
    public boolean isMoving(){
        return this.moves;
    }
    public void setMoving (boolean moves){
        this.moves = moves;
    }
    public void movement(){};

}