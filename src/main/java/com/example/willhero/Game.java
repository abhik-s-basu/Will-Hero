package com.example.willhero;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Game implements Screen{

    private AnchorPane gamePane;
//    private Pane playField;
    private Scene scene;
    private int coinsCollected;
    private int score;
    private ArrayList<GameObject> gameObjects;
    private final int RESURRECT_COINS ;
    private final int WINNING_JUMP;
    private final int ABYSS;
    private final double GRAVITY;
    private AnimationTimer gameLoop;
    private Rectangle tempClicker;
    private Hero hero;


    Game(){
       this.coinsCollected = 0;
       this.score = 0;
       this.gameObjects = new ArrayList<GameObject>();
       this.RESURRECT_COINS = 100;
       this.ABYSS = 575;
       this.WINNING_JUMP = 122;
       this.GRAVITY = 0.25;
    }


    public void startGame(Stage primaryStage, double posHero, boolean sound, boolean music) throws IOException {
        System.out.println("Game here!!!");

        primaryStage.getIcons().add(new Image("file:src/main/resources/Assets/Knight.png"));
        gamePane = FXMLLoader.load(getClass().getResource("Game.fxml"));
        gameObjects.add(new Island(18,+444,270,135,
                false,"file:src/main/resources/Assets/Islands/T_Islands_02.png"));
        //file:src/main/resources/Assets/Islands/T_Islands_02.png
        tempClicker = new Rectangle();
        tempClicker.setLayoutX(0);
        tempClicker.setLayoutY(130);
        tempClicker.setWidth(10000);
        tempClicker.setHeight(448);
        tempClicker.setOpacity(0);
        hero = new Hero(70, 80,"file:src/main/resources/Assets/Knight.png");
        Group g = new Group();
//        gameObjects.add(hero);
        for(GameObject i : gameObjects){
            g.getChildren().add(i.display());
            gamePane.getChildren().add(i.display());

        }
        gamePane.getChildren().add(hero.display());
        gamePane.getChildren().add(tempClicker);



        scene = new Scene(gamePane,310,657);
        primaryStage.setScene(scene);
        System.out.println("Game here part 2");
        primaryStage.show();
        startGameLoop();
    }

    private void startGameLoop() {
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {

                update();
            }
        };
        gameLoop.start();
    }

    private void update(){

//        jumpHero();
//        hero.jumpInPlace();
//        jumpOrc();
        tempClicker.onMouseClickedProperty().set(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent e) {

                if (hero.getNode().getTranslateX() <= 1000) { //1000 needs to be changed
                    hero.jumpForward(); //40ms (change to 1s) this is moving 50
                }
            }
        });

        if (hero.getYSpeed()< 9){
           hero.setYSpeed(hero.getYSpeed() + GRAVITY); //v = u + at
//            System.out.println(hero.getTranslateY());
        }
//        if (orcVelocity.getY() < 9){
//            orcVelocity = orcVelocity.add(0, gravity); //v = u + at
////            System.out.println(hero.getTranslateY());
//        }
//        movePlayerY((int) heroVelocity.getY(), hero);
        hero.jumpInPlace();
//        movePlayerY((int) orcVelocity.getY(), greenOrc);
    }

    //s = ut + 1/2*at^2
    //v = u + at

//    private void movePlayerX(int XVelocity, Node node){
//        for (int i = 0; i < Math.abs(XVelocity); i++){
//            System.out.println(hero.getTranslateX() + 29 + " " + greenOrc.getTranslateX());
//            if (hero.getBoundsInParent().intersects(greenOrc.getBoundsInParent())) {
//                System.out.println("Collision");
//                if (XVelocity > 0) {
//                    XVelocity = -XVelocity;
//                    //move orc
//                    if (hero.getTranslateX() - 218 == greenOrc.getTranslateX()) {
//                        System.out.println("COLLIDED");
//                        return;
//                    }
//                } else {
//                    if (hero.getTranslateX() == greenOrc.getTranslateX() + 33) {
//                        System.out.println("COLLided");
//                        return;
//                    }
//                }
//            }
//            hero.setTranslateX(hero.getTranslateX() + ((XVelocity > 0) ? 1 : -1)); //s = ut + 1/2 at^2
//        }
//    }

//    private void movePlayerY(int YVelocity, Node node){
//        for (int i = 0; i < Math.abs(YVelocity); i++){
//            if (node.getBoundsInParent().intersects(Island1.getBoundsInParent())) {
//                if (YVelocity > 0) {
//                    if (node.getTranslateY() == Island1.getTranslateY()) {
//                        if(node == hero){
//                            canJump = true;
//                        }
//                        else if (node == greenOrc){
//                            canJumpOrc = true;
//                        }
//                        return;
//                    }
//                }
//            }
//            node.setTranslateY(node.getTranslateY() + ((YVelocity > 0) ? 1 : -1)); //s = ut + 1/2 at^2
//        }
//    }


//    private void jumpHero(){ //in hero
//        if (canJump){
//            heroVelocity = heroVelocity.add(0,-7.5);
//            canJump = false;
//        }
//    }
//
//    private void jumpOrc(){ //in orc
//        if (canJumpOrc){
//            orcVelocity = orcVelocity.add(0,-6.9);
//            canJumpOrc = false;
//        }
//    }
}
