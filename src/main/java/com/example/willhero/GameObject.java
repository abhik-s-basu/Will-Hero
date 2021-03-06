package com.example.willhero;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

//weapon buttons
//hero image

//coins in main screen and updated - abhik
//saved games menu, game won menu - abhik
//serializable - TO BE DONE - abhik
//music - not priority - abhik
//presentation - abhik
//touch ups - end


public abstract class GameObject implements Serializable {
    private static final long serialVersionUID = 1729L;
    private double xCoordinate;
    private double yCoordinate;
    private int xSpeed;
    private int ySpeed;
    private double length;
    private double breadth;
    private boolean moves;
    private String imageURL;
    private transient Image image;
    private transient ImageView imageView;
    private double deltaX;
    private double deltaY;

    private transient Rectangle upper;
    private transient Rectangle lower;
    private transient Rectangle left;
    private transient Rectangle right;

    public GameObject(double x,double y,int _xSpeed , int _ySpeed , double length,
               double breadth,boolean moves, String imageURL){
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.xSpeed = _xSpeed;
        this.ySpeed = _ySpeed;
        this.length = length;
        this.breadth = breadth;
        this.moves = moves;
        this.imageURL = imageURL;
        this.deltaX = 0;
        this.deltaY = 0;
        this.display();
    }

    public String getImageURL() {
        return imageURL;
    }
    public void setImageURL(String s){this.imageURL = s;}

    private void display(){

        image = new Image(imageURL);
        imageView = new ImageView(image);
        imageView.setX(xCoordinate);
        imageView.setY(yCoordinate);
        imageView.setFitHeight(breadth);
        imageView.setFitWidth(length);
        imageView.setTranslateX(deltaX);
        imageView.setTranslateY(deltaY);

        upper = new Rectangle();
        upper.setLayoutX(xCoordinate);
        upper.setLayoutY(yCoordinate);
        upper.setHeight(5);
        upper.setWidth(length);
        upper.setOpacity(0);
        upper.setTranslateX(deltaX);
        upper.setTranslateY(deltaY);

        lower = new Rectangle();
        lower.setLayoutX(xCoordinate);
        lower.setLayoutY(yCoordinate + breadth - 4);
        lower.setHeight(4);
        lower.setWidth(length);
        lower.setOpacity(0);
        lower.setTranslateX(deltaX);
        lower.setTranslateY(deltaY);

        left = new Rectangle();
        left.setLayoutX(xCoordinate);
        left.setLayoutY(yCoordinate);
        left.setHeight(breadth);
        left.setWidth(4);
        left.setOpacity(0);
        left.setTranslateX(deltaX);
        left.setTranslateY(deltaY);

        right = new Rectangle();
        right.setLayoutX(xCoordinate + length - 4);
        right.setLayoutY(yCoordinate);
        right.setHeight(breadth);
        right.setWidth(4);
        right.setOpacity(0);
        imageView.setTranslateX(deltaX);
        imageView.setTranslateY(deltaY);

    }

    public ArrayList<Node> getAll(){
        ArrayList<Node> temp = new ArrayList<Node>();
        temp.add(upper); temp.add(lower); temp.add(right);
        temp.add(left); temp.add(imageView);
        return temp;
    }

    public ImageView getNode(){
        return imageView;
    }
    public Node getUpper(){
        return upper;
    }
    public Node getLower(){
        return lower;
    }
    public Node getRight(){
        return right;
    }
    public Node getLeft(){
        return left;
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
    public void setLower(int i){
        this.lower.setHeight(i);
    }
    public void setUpper(int i){
        this.upper.setHeight(i);
    }
    public void setRight(int i){
        this.right.setWidth(i);
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