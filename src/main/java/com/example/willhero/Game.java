package com.example.willhero;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.ArrayList;

public class Game implements Screen, Serializable {

    private static final long serialVersionUID = 6592L;
    private transient AnchorPane gamePane;
    private transient Scene scene;
    private transient Scene pausedScene;
    private transient Stage stage;
    private static Game game = null;
    private volatile int coinsCollected;
    private int score;
    private transient Text numCoins;
    private ArrayList<GameObject> gameObjects;
    private final int ABYSS;
    private final int GRAVITY;
    private transient AnimationTimer gameLoop;
    private Hero hero;
    private Hero princess;
    private transient Text scoreText;
    private transient Rectangle tempClicker;
    private transient ImageView heroProgView;
    private transient ImageView axe;
    private transient ImageView knife;
    private transient Rectangle axeButton;
    private transient Rectangle knifeButton;
    private transient Text axeLevel;
    private transient Text knifeLevel;
    private ArrayList<Orc> orcs;
    private ArrayList<Island> islands;
    private ArrayList<Chest> chests;
    private ArrayList<Obstacle> obstacles;
    private GameObject collidedNode;
    private boolean flag;
    private int progCounter;
    private boolean endGameChecker;
    private boolean isResurrected;


    private transient String jumpSoundFile = "src/main/resources/Assets/Sounds/jump.wav";
    private transient Media jumpSound;
    private transient MediaPlayer jumpSoundPlayer;
    private transient String collisionSoundFile = "src/main/resources/Assets/Sounds/collision.wav";
    private transient Media collisionSound;
    private transient MediaPlayer collisionSoundPlayer;
    private transient String deadSoundFile ="src/main/resources/Assets/Sounds/dead.wav";
    private transient Media deadSound;
    private transient MediaPlayer deadSoundPlayer;


    Game() {
        this.coinsCollected = 0;
        this.score = 0;
        this.gameObjects = new ArrayList<GameObject>();
        this.orcs = new ArrayList<Orc>();
        this.islands = new ArrayList<Island>();
        this.game = this;
        this.chests = new ArrayList<Chest>();
        this.obstacles = new ArrayList<Obstacle>();
        this.ABYSS = 575;
        this.GRAVITY = 1;
        this.collidedNode = null;
        this.flag = false;
        this.endGameChecker = false;
        this.isResurrected = false;
    }


    public boolean checkCollisionY(GameObject go, int YSpeed) throws Exception {
        for (GameObject n : islands) {
            if (YSpeed > 0) {
                if (go.getLower().getBoundsInParent().intersects(n.getUpper().getBoundsInParent())) {
                    if(go == hero){
                        jumpSound = new Media(new File(jumpSoundFile).toURI().toString());
                        jumpSoundPlayer = new MediaPlayer(jumpSound);
                        jumpSoundPlayer.play();
                    }
                    return true;
                }
            }
        }
        for (GameObject n : orcs) {
            if (((Orc) n).getAlive() && YSpeed > 0) {
                if (!go.equals(n)) {
                    if ((go.getLower().getBoundsInParent().intersects(n.getUpper().getBoundsInParent()))){
                        return true;
                    }
                }
            }
        }
        for (GameObject n : chests) {
            if (YSpeed > 0) {
                if (go.equals(hero)) {
                    if (go.getNode().getBoundsInParent().intersects(n.getNode().getBoundsInParent())) {
                        if(n.isMoving()){
                            break;
                        }
                        n.setMoving(true);
                        if(n instanceof CoinChest){
                            n.getNode().setImage(new Image("file:src/main/resources/Assets/Chests/OpenChestNoBG.png"));
                            coinsCollected += ((CoinChest) n).openChest();
                            numCoins.setText(String.valueOf(coinsCollected));
                        }
                        else if (n instanceof WeaponChest){
                            n.getNode().setImage(new Image("file:src/main/resources/Assets/Chests/WeaponChest.png"));
                            hero.openChest((WeaponChest) n);
                            Weapon weapon = hero.getCurWeapon();
                            if (weapon instanceof ThrowingAxe){
                                hero.getHelmet().getWeapon1().setQuantity(1); //axe
                                weapon = hero.getHelmet().getWeapon1(); //updating weapon status
                                axeLevel.setText(String.valueOf(weapon.getQuantity()));
                                if (weapon.getQuantity() == 1){
                                    axe.setOpacity(1);
                                    axeButton.setOpacity(1);
                                    axeLevel.setOpacity(1);
                                }
                                axeButtonClicked();
                            }
                            if (weapon instanceof ThrowingKnife){
                                hero.getHelmet().getWeapon2().setQuantity(1); //knife
                                weapon = hero.getHelmet().getWeapon2(); //updating weapon status
                                knifeLevel.setText(String.valueOf(weapon.getQuantity()));
                                if (weapon.getQuantity() == 1){
                                    knife.setOpacity(1);
                                    knifeButton.setOpacity(1);
                                    knifeLevel.setOpacity(1);
                                }
                                knifeButtonClicked();
                            }
                            hero.setCurWeapon(weapon);
                        }
                    }
                }
            }
            for (GameObject g : obstacles){
                if (g instanceof TNT){
                    if (!((TNT) g).getIsCollided() && go.getNode().getBoundsInParent().intersects(g.getNode().getBoundsInParent())) {
                        ((TNT) g).explode();
                    }
                }
                if (g instanceof TNTSmoke){
                    if (((TNTSmoke) g).getSmoke() && go.getNode().getBoundsInParent().intersects(g.getNode().getBoundsInParent())) {
                        if(go == hero){
                            endgame();
                        }
                        if (go instanceof Orc){
                            ((Orc) go).setAlive();
                            return false;
                        }
                    }
                }
            }
        }
        if (YSpeed > 0) {
            if (go instanceof Orc) {
                if (go.getLower().getBoundsInParent().intersects(hero.getUpper().getBoundsInParent())) {
                    endgame();
                    return false;
                }
            }
        }
        return false;
    }



    private void endgame() throws Exception {
        if (!endGameChecker) {
            deadSound = new Media(new File(deadSoundFile).toURI().toString());
            deadSoundPlayer = new MediaPlayer(deadSound);
            deadSoundPlayer.play();

            gameLoop.stop();
            pausedScene = scene;
            GameEndMenu gameEndMenu = new GameEndMenu(game, this.score, this.coinsCollected, this.isResurrected);
            gameEndMenu.start(stage);
        }
        endGameChecker = true;
    }



    private void update() throws Exception {
        if (hero.getYSpeed() < 30) {
            hero.setYSpeed(hero.getYSpeed() + GRAVITY); //v = u + at
        }
        hero.jumpInPlace();

        if(hero.getY() + hero.getNode().getTranslateY() >= ABYSS){
            endgame();
        }

        Orc dead = null;
        for (Orc o : orcs) {
            if (o.getYSpeed() < 30) {
                o.setYSpeed(o.getYSpeed() + GRAVITY); //v = u + at
            }
            o.jumpInPlace(this);
            if(!o.getAlive() || o.getY() + o.getNode().getTranslateY() >= ABYSS){
                dead = o;
                System.out.println("Orc is dead");
                coinsCollected += dead.getCoinsOnKill();
                numCoins.setText(String.valueOf(coinsCollected));
            }
        }

        if (dead != null){
            gamePane.getChildren().removeAll(dead.getNode(),
                    dead.getLeft(), dead.getLower(), dead.getRight(), dead.getUpper());
            dead.setAlive();
            orcs.remove(dead);
            gameObjects.remove(dead);
        }
    }



    private void startGameLoop() {
        gameLoop = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 20_000_000) {
                    try {
                        update();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    lastUpdate = now;
                }
            }
        };
        gameLoop.start();
    }



    private void gameWon() throws Exception {
        gameLoop.stop();
        pausedScene = scene;
        GameWon gameWonMenu = new GameWon(game,this.score,this.coinsCollected);
        gameWonMenu.start(stage);
    }



    private void updateClicker() throws Exception {
        System.out.println(princess.getX() + princess.getNode().getTranslateX());
        if (hero.getNode().getLayoutX() + hero.getX() <= princess.getX() + princess.getNode().getTranslateX()) {
            score++;
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2), this::doStep));
            timeline.setCycleCount(hero.getXSpeed());
            timeline.play();
            timeline.setOnFinished(e->{
                collidedNode = null;
                flag = false;
            });
        }
        else{
            System.out.println(hero.getNode().getLayoutX() + hero.getX() + " " +
                    (int) (princess.getX() + princess.getNode().getTranslateX()));
            gameWon();
        }
    }



    private void doStep(ActionEvent actionEvent) {
        for (GameObject j : gameObjects) {
            Node left = j.getLeft();
            if (!j.equals(hero)) {
                if (j instanceof Orc &&
                        (left.getBoundsInParent().intersects(hero.getRight().getBoundsInParent()))) {
                    collidedNode = j;
                    ((Orc) j).setHealth(hero.getCurWeapon() != null && !flag ? hero.getCurWeapon().getDamage() : 0);
                    flag = true;
                    if (((Orc) j).getHealth() <= 0){
                        ((Orc) j).setAlive();
                        collidedNode = null;
                    }
                    System.out.println("Health remaining in the collided Orc = "
                            + ((Orc) j).getHealth());
                    collisionSound = new Media(new File(collisionSoundFile).toURI().toString());
                    collisionSoundPlayer = new MediaPlayer(collisionSound);
                    collisionSoundPlayer.play();
                }
                if(collidedNode == null){
                    j.getNode().setTranslateX((j.getNode().getTranslateX() +
                            ((hero.getXSpeed() < 0) ? 1 : -1))); //s = ut + 1/2 at^2
                    j.getUpper().setTranslateX((j.getUpper().getTranslateX() +
                            ((hero.getXSpeed() < 0) ? 1 : -1))); //s = ut + 1/2 at^2
                    j.getLower().setTranslateX((j.getLower().getTranslateX() +
                            ((hero.getXSpeed() < 0) ? 1 : -1))); //s = ut + 1/2 at^2
                    j.getRight().setTranslateX((j.getRight().getTranslateX() +
                            ((hero.getXSpeed() < 0) ? 1 : -1))); //s = ut + 1/2 at^2
                    j.getLeft().setTranslateX((j.getLeft().getTranslateX() +
                            ((hero.getXSpeed() < 0) ? 1 : -1))); //s = ut + 1/2 at^2
                }
                else{
                    if (j != collidedNode) {
                        j.getNode().setTranslateX((j.getNode().getTranslateX() +
                                ((hero.getXSpeed() > 0) ? 1 : -1))); //s = ut + 1/2 at^2
                        j.getUpper().setTranslateX((j.getUpper().getTranslateX() +
                                ((hero.getXSpeed() > 0) ? 1 : -1))); //s = ut + 1/2 at^2
                        j.getLower().setTranslateX((j.getLower().getTranslateX() +
                                ((hero.getXSpeed() > 0) ? 1 : -1))); //s = ut + 1/2 at^2
                        j.getRight().setTranslateX((j.getRight().getTranslateX() +
                                ((hero.getXSpeed() > 0) ? 1 : -1))); //s = ut + 1/2 at^2
                        j.getLeft().setTranslateX((j.getLeft().getTranslateX() +
                                ((hero.getXSpeed() > 0) ? 1 : -1))); //s = ut + 1/2 at^2
                    }
                    else{
                        collidedNode.getNode().setTranslateX((collidedNode.getNode().getTranslateX() +
                                ((hero.getXSpeed() > 0) ? 3 : -1))); //s = ut + 1/2 at^2
                        collidedNode.getUpper().setTranslateX((collidedNode.getUpper().getTranslateX() +
                                ((hero.getXSpeed() > 0) ? 3 : -1))); //s = ut + 1/2 at^2
                        collidedNode.getLower().setTranslateX((collidedNode.getLower().getTranslateX() +
                                ((hero.getXSpeed() > 0) ? 3 : -1))); //s = ut + 1/2 at^2
                        collidedNode.getRight().setTranslateX((collidedNode.getRight().getTranslateX() +
                                ((hero.getXSpeed() > 0) ? 3 : -1))); //s = ut + 1/2 at^2
                        collidedNode.getLeft().setTranslateX((collidedNode.getLeft().getTranslateX() +
                                ((hero.getXSpeed() > 0) ? 3 : -1))); //s = ut + 1/2 at^2
                    }
                }
            }
        }
    }




    public static Game getInstance(){
        //conditions to maintain OOP concepts
        return game;
    }



    public Hero getHero(){
        return hero;
    }



    public ArrayList<GameObject> getAllObjects(){
        return gameObjects;
    }




    public void pause() throws Exception{
        gameLoop.stop();
        pausedScene = scene;
        PauseGameMenu pm = new PauseGameMenu(this);
        pm.start(stage);
    }




    public void resumeGame(Stage stage, boolean extralife){
        endGameChecker = false;
        stage.setScene(pausedScene);
        stage.show();
        if(extralife && !(hero.isResurrected())){
            coinsCollected = 0;
            for(Node i : hero.getAll()){
                i.setTranslateY(-500);
                hero.setYSpeed(0);
                hero.setResurrected(true);
            }
        }
        gameLoop.start();
    }

    private void axeButtonClicked() {
        if (!(axeButton.getOpacity() == 0.5)){
            hero.setCurWeapon(hero.getHelmet().getWeapon1());
            hero.getNode().setImage(new Image("file:src/main/resources/Assets/KnightAxe.png"));
//            hero.setImageURL("file:src/main/resources/Assets/KnightAxe.png");
            hero.getNode().setFitWidth(70);
            hero.getNode().setFitHeight(45);
            hero.setRight(30);
            hero.getNode().setLayoutY(-10);
            hero.getNode().setLayoutX(-12);
        }
    }

    private void knifeButtonClicked() {
        if (!(knifeButton.getOpacity() == 0.5)) {
            hero.setCurWeapon(hero.getHelmet().getWeapon2());
            hero.getNode().setImage(new Image("file:src/main/resources/Assets/KnightKnife.png"));
//            hero.setImageURL("file:src/main/resources/Assets/KnightKnife.png");
            hero.getNode().setFitWidth(57);
            hero.getNode().setFitHeight(35);
            hero.setRight(25);
            hero.getNode().setLayoutY(-2);
            hero.getNode().setLayoutX(-2);
        }
    }

    private void islandGenerator() throws IOException {
        islands.add(new Island(18,444,270,100,
                true,"file:src/main/resources/Assets/Islands/T_Islands_02.png"));
        orcs.add(new MediumOrc(140,400,40,40,
                0, 0, "RED", 10, 20,
                "file:src/main/resources/Assets/Orks/big_crimson_ork.png"));
        chests.add(new CoinChest(10,200,408,"file:src/main/resources/Assets/Chests/closedChest.png"));


        islands.add(new Island(375,425,200,75,
                true,"file:src/main/resources/Assets/Islands/T_Islands_03.png"));
        orcs.add(new SmallOrc(386,391,33,33,
                0, 0, "RED", 10, 10,
                "file:src/main/resources/Assets/Orks/big_green_ork.png"));
        orcs.add(new SmallOrc(507,391,33,33,
                0, 0, "RED", 20, 10,
                "file:src/main/resources/Assets/Orks/big_crimson_ork.png"));
        chests.add(new CoinChest(20,432,389,"file:src/main/resources/Assets/Chests/closedChest.png"));


        islands.add(new Island(623,407,150,75,
                true,"file:src/main/resources/Assets/Islands/T_Islands_07.png"));
        orcs.add(new MediumOrc(700,367,40,40,
                0, 0, "RED", 20, 20,
                "file:src/main/resources/Assets/Orks/big_crimson_ork.png"));
        chests.add(new WeaponChest(new Helmet(new ThrowingAxe(), new ThrowingKnife()),640,371,"file:src/main/resources/Assets/Chests/closedChest.png"));


        islands.add(new Island(790,500,225,125,
                true,"file:src/main/resources/Assets/Islands/T_Islands_04.png"));
        orcs.add(new SmallOrc(810,467,33,33,
                0, 0, "RED", 30, 15,
                "file:src/main/resources/Assets/Orks/big_green_ork.png"));
        orcs.add(new MediumOrc(920,460,40,40,
                0, 0, "RED", 30, 25,
                "file:src/main/resources/Assets/Orks/big_crimson_ork.png"));
        chests.add(new WeaponChest(new Helmet(new ThrowingAxe(),new ThrowingKnife()),860,464,"file:src/main/resources/Assets/Chests/closedChest.png"));


        islands.add(new Island(1068,444,270,100,
                true,"file:src/main/resources/Assets/Islands/T_Islands_01.png"));
        orcs.add(new MediumOrc(1270,400,40,40,
                0, 0, "RED", 30, 25,
                "file:src/main/resources/Assets/Orks/big_green_ork.png"));
        TNTBlinker tntB1 = new TNTBlinker(1100, 398, "file:src/main/resources/Assets/WhiteSquare.png");
        obstacles.add(tntB1);
        TNT tnt1 = new TNT(1100,398,"file:src/main/resources/Assets/TNT.png");
        obstacles.add(tnt1);
        tntB1.setParent(tnt1);
        TNTSmoke tntS1 = new TNTSmoke(1050, 348, "file:src/main/resources/Assets/TNT_smoke-removebg-preview.png");
        obstacles.add(tntS1);
        tntS1.setParent(tnt1);
        tnt1.setBlinker(tntB1);
        tnt1.setSmoke(tntS1);


        islands.add(new Island(1425,425,200,75,
                true,"file:src/main/resources/Assets/Islands/T_Islands_05.png"));
        orcs.add(new SmallOrc(1436,391,33,33,
                0, 0, "RED", 30, 30,
                "file:src/main/resources/Assets/Orks/big_green_ork.png"));
        orcs.add(new SmallOrc(1537,391,33,33,
                0, 0, "RED", 30, 15,
                "file:src/main/resources/Assets/Orks/big_green_ork.png"));
        chests.add(new WeaponChest(new Helmet(new ThrowingAxe(),new ThrowingKnife()),1480,389,"file:src/main/resources/Assets/Chests/closedChest.png"));


        islands.add(new Island(1673,407,150,75,
                true,"file:src/main/resources/Assets/Islands/T_Islands_11.png"));
        orcs.add(new MediumOrc(1750,367,40,40,
                0, 0, "RED", 30, 30,
                "file:src/main/resources/Assets/Orks/big_crimson_ork.png"));
        TNTBlinker tntB2 = new TNTBlinker(1700, 361, "file:src/main/resources/Assets/WhiteSquare.png");
        obstacles.add(tntB2);
        TNT tnt2 = new TNT(1700,361,"file:src/main/resources/Assets/TNT.png");
        obstacles.add(tnt2);
        tntB2.setParent(tnt2);
        TNTSmoke tntS2 = new TNTSmoke(1650, 311, "file:src/main/resources/Assets/TNT_smoke-removebg-preview.png");
        obstacles.add(tntS2);
        tntS2.setParent(tnt2);
        tnt2.setBlinker(tntB2);
        tnt2.setSmoke(tntS2);


        islands.add(new Island(1880,500,300,125,
                true,"file:src/main/resources/Assets/Islands/T_Islands_09.png"));
        orcs.add(new MediumOrc(1960,460,40,40,
                0, 0, "RED", 40, 30,
                "file:src/main/resources/Assets/Orks/big_crimson_ork.png"));
        orcs.add(new MediumOrc(2070,460,40,40,
                0, 0, "RED", 40, 30,
                "file:src/main/resources/Assets/Orks/big_green_ork.png"));
        chests.add(new CoinChest(30,1890,464,"file:src/main/resources/Assets/Chests/closedChest.png"));


        islands.add(new Island(2300,450,1100,300,
                true,"file:src/main/resources/Assets/Islands/T_Islands_06.png"));
        chests.add(new WeaponChest(new Helmet(new ThrowingAxe(),new ThrowingKnife()),2400,414,"file:src/main/resources/Assets/Chests/closedChest.png"));
        orcs.add(new BossOrc(2600,359,90,90,
                0, 0, "GREEN", 100, 100,
                "file:src/main/resources/Assets/Orks/big_green_ork.png"));
        for (Island i : islands){
            gameObjects.add(i);
        }
        for (Orc o : orcs){
            gameObjects.add(o);
        }
        for (Chest c: chests){
            gameObjects.add(c);
        }
        for (Obstacle obs: obstacles){
            gameObjects.add(obs);
        }

        hero = new Hero(40, 414,this ,"file:src/main/resources/Assets/Knight.png");
        princess = new Hero (3250, 418, this,
                "file:src/main/resources/Assets/Princess/Princess_happy.png");
        gameObjects.add(princess);
        gameObjects.add(hero);


        for(GameObject go : gameObjects) {
            gamePane.getChildren().addAll(go.getNode(), go.getUpper(),
                    go.getLower(), go.getRight(), go.getLeft());
        }

    }

    public void startGame(Stage primaryStage)
            throws IOException {

        primaryStage.getIcons().add(new Image("file:src/main/resources/Assets/Knight.png"));
        gamePane = FXMLLoader.load(getClass().getResource("Game.fxml"));

        islandGenerator();
        initButtons();


        tempClicker.onMouseClickedProperty().set(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent e) {
                try {
                    updateClicker();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                scoreText.setText(""+score);
                progCounter += 3;
                progCounter = Math.min(progCounter,260);
                heroProgView.setX(progCounter);
                numCoins.setText(String.valueOf(coinsCollected));
            }
        });

        scene = new Scene(gamePane,310,657);
        primaryStage.setScene(scene);
        primaryStage.show();
        stage = primaryStage;
        startGameLoop();
    }



//    private void writeObject(ObjectOutputStream out) throws IOException
//    {
//
//        out.defaultWriteObject();
//    }
//    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
//    {


//        in.defaultReadObject();
//        islandGenerator();
//        initButtons();
//        startGame(stage);


//    }
    private void initButtons() throws IOException {
//        this.gamePane = FXMLLoader.load(getClass().getResource("Game.fxml"));;
        scoreText = new Text();
        scoreText.setLayoutX(152);
        scoreText.setLayoutY(68);
        scoreText.setText(""+score); //isko kar
        scoreText.setFont(Font.font("Comic Sans MS", 26));
        scoreText.setFill(Color.rgb(255, 102, 196));

//        progressBarCreator();
        Line progressLine = new Line(42,90,262,90);
        progressLine.setStrokeWidth(5);
        progressLine.setStroke(Color.rgb(255,102,196));

        Rectangle heroSide = new Rectangle(15,15);
        heroSide.setLayoutX(39);
        heroSide.setLayoutY(82);
        heroSide.setStroke(Color.rgb(255,102,196));
        heroSide.setFill(Color.rgb(255,102,196));

        Rectangle orcSide = new Rectangle(15,15);
        orcSide.setLayoutX(265);
        orcSide.setLayoutY(82);
        orcSide.setStroke(Color.rgb(255,102,196));
        orcSide.setFill(Color.rgb(255,102,196));
        progCounter = 40;

        Image heroProg =  new Image("file:src/main/resources/Assets/Knight.png");
        heroProgView = new ImageView(heroProg);
        heroProgView.setX(37);
        heroProgView.setTranslateX(score*3);
        System.out.println(score);
        heroProgView.setY(70);
        heroProgView.setFitHeight(20);
        heroProgView.setFitWidth(20);

        Image orcProg =  new Image("file:src/main/resources/Assets/Orks/big_green_ork.png");
        ImageView orcProgView = new ImageView(orcProg);
        orcProgView.setX(263);
        orcProgView.setY(70);
        orcProgView.setFitHeight(20);
        orcProgView.setFitWidth(20);

        Group barGroup = new Group();
        barGroup.getChildren().add(progressLine);
        barGroup.getChildren().add(heroSide);
        barGroup.getChildren().add(orcSide);
        barGroup.getChildren().add(orcProgView);
        barGroup.getChildren().add(heroProgView);
        gamePane.getChildren().add(barGroup);
//end of progress bar

        Image coinPic = new Image("file:src/main/resources/Assets/Coin.png");
        ImageView coinView = new ImageView(coinPic);
        coinView.setFitWidth(25);
        coinView.setFitHeight(25);
        coinView.setLayoutX(280);
        coinView.setLayoutY(12);
        gamePane.getChildren().add(coinView);

        numCoins = new Text();
        numCoins.setLayoutX(230);
        numCoins.setLayoutY(35);
        numCoins.setText(String.valueOf(coinsCollected)); //isko kar
        numCoins.setFont(Font.font("Comic Sans MS", 25));
        numCoins.setFill(Color.rgb(255, 249, 2));
        gamePane.getChildren().add(numCoins);

        tempClicker = new Rectangle();
        tempClicker.setLayoutX(50); tempClicker.setLayoutY(130); tempClicker.setWidth(250);
        tempClicker.setHeight(550); tempClicker.setOpacity(0);
        gamePane.getChildren().add(tempClicker);
        gamePane.getChildren().add(scoreText);

        knifeButton = new Rectangle();
        knifeButton.setLayoutX(180); knifeButton.setLayoutY(593); knifeButton.setWidth(43);
        knifeButton.setHeight(43); knifeButton.setRotate(45);
        knifeButton.setOpacity(hero.getHelmet().getWeapon2().getQuantity() == 0 ? 0.5 : 1);
        knifeButton.setStroke(Color.BLACK);
        knifeButton.setFill(Color.rgb(175, 216,255));
        knifeButton.setOnMouseClicked(e->{
            knifeButtonClicked();
        });
        gamePane.getChildren().add(knifeButton);

        axeButton = new Rectangle();
        axeButton.setLayoutX(250); axeButton.setLayoutY(593); axeButton.setWidth(43);
        axeButton.setHeight(43); axeButton.setRotate(45);
        axeButton.setOpacity(hero.getHelmet().getWeapon1().getQuantity() == 0 ? 0.5 : 1);
        axeButton.setStroke(Color.BLACK);
        axeButton.setFill(Color.rgb(175, 216,255));
        axeButton.setOnMouseClicked(e->{
            axeButtonClicked();
        });
        gamePane.getChildren().add(axeButton);

        knife = new ImageView(new Image(
                "file:src/main/resources/Assets/Throwing Knives/ThrowingKnife2.png"));
        knife.setLayoutX(198); knife.setLayoutY(596); knife.setFitWidth(8);
        knife.setFitHeight(35);
        knife.setOpacity(hero.getHelmet().getWeapon2().getQuantity() == 0 ? 0.5 : 1);
        knife.setRotate(45);
        knife.setOnMouseClicked(e->{
            knifeButtonClicked();
        });
        gamePane.getChildren().add(knife);

        axe = new ImageView(new Image(
                "file:src/main/resources/Assets/Throwing Axes/ThrowingAxeNew.png"));
        axe.setLayoutX(261); axe.setLayoutY(596); axe.setFitWidth(20);
        axe.setFitHeight(36);
        axe.setOpacity(hero.getHelmet().getWeapon1().getQuantity() == 0 ? 0.5 : 1);
        axe.setOnMouseClicked(e->{
            axeButtonClicked();
        });
        gamePane.getChildren().add(axe);

        knifeLevel = new Text(""+hero.getHelmet().getWeapon2().getQuantity());
        knifeLevel.setFont(Font.font("Comic Sans MS", 13));
        knifeLevel.setLayoutX(205); knifeLevel.setLayoutY(630);
        knifeLevel.setStrokeWidth(3);
        knifeLevel.setOpacity(hero.getHelmet().getWeapon2().getQuantity() == 0 ? 0.5 : 1);
        knifeLevel.setOnMouseClicked(e->{
            knifeButtonClicked();
        });
        gamePane.getChildren().add(knifeLevel);

        axeLevel = new Text(""+hero.getHelmet().getWeapon1().getQuantity());
        axeLevel.setFont(Font.font("Comic Sans MS", 13));
        axeLevel.setLayoutX(213+63); axeLevel.setLayoutY(630);
        axeLevel.setStrokeWidth(3);
        axeLevel.setOpacity(hero.getHelmet().getWeapon1().getQuantity() == 0 ? 0.5 : 1);
        axeLevel.setOnMouseClicked(e->{
            axeButtonClicked();
        });
        gamePane.getChildren().add(axeLevel);
    }

    public Game (Game prevGame){
//        this.coinsCollected = 0;
//        this.score = 0;
        this.gameObjects = new ArrayList<GameObject>();
        this.orcs = new ArrayList<Orc>();
        this.islands = new ArrayList<Island>();
        this.game = this;
        this.chests = new ArrayList<Chest>();
        this.obstacles = new ArrayList<Obstacle>();
        this.ABYSS = 575;
        this.GRAVITY = 1;
        this.collidedNode = null;
        this.flag = false;
//        this.gamePane = new AnchorPane();
    }


    public void startSavedGame(Stage primaryStage, ArrayList<GameObject> tempList) throws IOException {
        primaryStage.getIcons().add(new Image("file:src/main/resources/Assets/Knight.png"));
        gamePane = FXMLLoader.load(getClass().getResource("Game.fxml"));
        gameObjects = tempList;
        for (GameObject go : gameObjects){
            if (go instanceof Orc){
                orcs.add((Orc) go);
            }
            if (go instanceof Chest){
                chests.add((Chest) go);
            }
            if (go instanceof Obstacle){
                obstacles.add((Obstacle) go);
            }
            if (go instanceof Island){
                islands.add((Island) go);
            }
            if (go instanceof Hero){
                if (go.getX() == 40){
                    hero = (Hero) go;
                    System.out.println(hero.getHelmet().getWeapon1().getQuantity());
                }
                else{
                    princess = (Hero) go;
                }
            }
            gamePane.getChildren().addAll(go.getAll());
        }

        initButtons();

        tempClicker.onMouseClickedProperty().set(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent e) {
                try {
                    updateClicker();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                scoreText.setText(""+score);
                progCounter += 3;
                progCounter = Math.min(progCounter,260);
                heroProgView.setX(progCounter);
                numCoins.setText(String.valueOf(coinsCollected));
            }
        });

        scene = new Scene(gamePane,310,657);
        primaryStage.setScene(scene);
        primaryStage.show();
        stage = primaryStage;
        startGameLoop();
    }

    public int getCoinsCollected(){
        return coinsCollected;
    }

    public int getScore(){
        return score;
    }

    public void setScore(String s){
        score = Integer.parseInt(s);
    }

    public void setCoinsCollected(String s){
        coinsCollected = Integer.parseInt(s);
    }
}
