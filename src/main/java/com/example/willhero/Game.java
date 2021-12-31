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
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Game implements Screen {

    private AnchorPane gamePane;
    private Scene scene;
    private int coinsCollected;
    private int score;
    private ArrayList<GameObject> gameObjects;
    private final int RESURRECT_COINS ;
    private final int WINNING_JUMP;
    private final int ABYSS;
    private final int GRAVITY;
    private AnimationTimer gameLoop;
    private Rectangle tempClicker;
    private Hero hero;
    private ArrayList<Orc> orcs;
    private ArrayList<Island> islands;
    Group groupOfObjects;

    Game(){
       this.coinsCollected = 0;
       this.score = 0;
       this.gameObjects = new ArrayList<GameObject>();
       this.orcs = new ArrayList<Orc>();
       this.islands = new ArrayList<Island>();
       this.RESURRECT_COINS = 100;
       this.ABYSS = 575;
       this.WINNING_JUMP = 122;
       this.GRAVITY = 1;
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





    public boolean checkCollision(GameObject go, int YSpeed){
        for (GameObject n : gameObjects){
            Node node = n.getNode();
//            System.out.println(go.getNode().getBoundsInParent().intersects(node.getBoundsInParent()));
            if (YSpeed > 0) {
                if (go.getNode().getBoundsInParent().intersects(node.getBoundsInParent())) {
//                    System.out.println("get in bounds");
//                    System.out.println(go.getNode().getTranslateY() + " " + node.getTranslateY());
                    if (Math.abs( go.getNode().getTranslateY() + go.getY() + go.getLength() + 1 - n.getY() - node.getTranslateY()) < 4) {
//                        go.setYSpeed(go.getYSpeed());
//                        System.out.println("koodo");
                        return true;
                    }
                }
            }
        }
        return false;
    }



    private void update(){
        if (hero.getYSpeed() < 30){
            hero.setYSpeed(hero.getYSpeed() + GRAVITY); //v = u + at
        }
        hero.jumpInPlace();

        for(Orc o : orcs){
            if (o.getYSpeed() < 30){
                o.setYSpeed(o.getYSpeed() + GRAVITY); //v = u + at
            }
            o.jumpInPlace(this);
        }
    }






    private void startGameLoop() {
        gameLoop = new AnimationTimer() {
            private long lastUpdate = 0;
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 20_000_000) {
                    update();
                    lastUpdate = now ;
                }
            }
        };
        gameLoop.start();
    }



    private void islandGenerator(){
        islands.add(new Island(18,444,270,100,
                true,"file:src/main/resources/Assets/Islands/T_Islands_02.png"));
        orcs.add(new SmallOrc(265,410,33,33,
                0, 0, "GREEN", 5, 10,
                "file:src/main/resources/Assets/Orks/big_crimson_ork.png"));

        islands.add(new Island(375,425,200,75,
                true,"file:src/main/resources/Assets/Islands/T_Islands_03.png"));
        orcs.add(new SmallOrc(386,391,33,33,
                0, 0, "GREEN", 5, 10,
                "file:src/main/resources/Assets/Orks/big_crimson_ork.png"));
        orcs.add(new SmallOrc(487,391,33,33,
                0, 0, "GREEN", 5, 10,
                "file:src/main/resources/Assets/Orks/big_crimson_ork.png"));

        islands.add(new Island(623,407,150,75,
                true,"file:src/main/resources/Assets/Islands/T_Islands_07.png"));

        islands.add(new Island(790,500,300,125,
                true,"file:src/main/resources/Assets/Islands/T_Islands_04.png"));
    }





    public void startGame(Stage primaryStage, double posHero, boolean sound, boolean music)
            throws IOException {

        primaryStage.getIcons().add(new Image("file:src/main/resources/Assets/Knight.png"));
        gamePane = FXMLLoader.load(getClass().getResource("Game.fxml"));

        islandGenerator();

        for (Island i : islands){
            gameObjects.add(i);
        }
        for (Orc o : orcs){
            gameObjects.add(o);
        }
        for(GameObject go : gameObjects) {
            gamePane.getChildren().add(go.getNode());
        }

        Text scoreText = new Text();
        scoreText.setLayoutX(152);
        scoreText.setLayoutY(68);
        scoreText.setText(""+score);
        scoreText.setFont(Font.font("Comic Sans MS", 26));
        scoreText.setFill(Color.rgb(255, 102, 196));

        tempClicker = new Rectangle();
        tempClicker.setLayoutX(50); tempClicker.setLayoutY(130); tempClicker.setWidth(250);
        tempClicker.setHeight(448); tempClicker.setOpacity(0);
        hero = new Hero(40, 414,this ,"file:src/main/resources/Assets/Knight.png");
//        gameObjects.add(hero);
//        groupOfObjects = new Group();
//        for(GameObject i : gameObjects){
//            groupOfObjects.getChildren().add(i.getNode());
//        }
//        gamePane.getChildren().add(groupOfObjects);
        gamePane.getChildren().add(hero.getNode());
        gamePane.getChildren().add(tempClicker);
        gamePane.getChildren().add(scoreText);

        tempClicker.onMouseClickedProperty().set(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent e) {
                if (hero.getNode().getTranslateX() <= 10000) { //1000 needs to be changed
                    hero.jumpForward(); //40ms (change to 1s) this is moving 50
                    for (int i = 0; i < Math.abs(hero.getXSpeed()); i++){
                        for(GameObject j : gameObjects) {
                            j.getNode().setTranslateX((j.getNode().getTranslateX() + ((hero.getXSpeed() < 0) ? 1 : -1))); //s = ut + 1/2 at^2
                        }
                    }
                }
                score++;
                scoreText.setText(""+score);
            }
        });

        scene = new Scene(gamePane,310,657);
        primaryStage.setScene(scene);
        primaryStage.show();
        startGameLoop();
    }

}
