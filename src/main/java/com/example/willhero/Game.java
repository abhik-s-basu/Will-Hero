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
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class Game implements Screen {

    private AnchorPane gamePane;
    private Scene scene;
    private Scene pausedScene;
    private Stage stage;
    private static Game game = null;
    private volatile int coinsCollected;
    private int score;
    private Text numCoins;
    private boolean gamePause = false;
    private ArrayList<GameObject> gameObjects;
    private final int RESURRECT_COINS;
    private final int WINNING_JUMP;
    private final int ABYSS;
    private final int GRAVITY;
    private AnimationTimer gameLoop;
    private Rectangle tempClicker;
    private Hero hero;
    private Hero princess;
    private ArrayList<Orc> orcs;
    private ArrayList<Island> islands;
    private ArrayList<Chest> chests;
    private ArrayList<Obstacle> obstacles;
    private GameObject collidedNode;
    private boolean flag;
    private int progCounter;

    Game() {
        this.coinsCollected = 0;
        this.score = 0;
        this.gameObjects = new ArrayList<GameObject>();
        this.orcs = new ArrayList<Orc>();
        this.islands = new ArrayList<Island>();
        this.game = this;
        this.chests = new ArrayList<Chest>();
        this.obstacles = new ArrayList<Obstacle>();
        this.RESURRECT_COINS = 100;
        this.ABYSS = 575;
        this.WINNING_JUMP = 60;
        this.GRAVITY = 1;
        this.collidedNode = null;
        this.flag = false;
    }

    public boolean checkCollisionY(GameObject go, int YSpeed) throws Exception {
        for (GameObject n : islands) {
            if (YSpeed > 0) {
                if (go.getLower().getBoundsInParent().intersects(n.getUpper().getBoundsInParent())) {
                    return true;
                }
            }
        }
        for (GameObject n : orcs) {
            if (((Orc) n).getAlive() && YSpeed > 0) {
                if (!go.equals(n)) {
                    if ((go.getLower().getBoundsInParent().intersects(n.getUpper().getBoundsInParent())) ||
                            n.getNode().getBoundsInParent().intersects(go.getNode().getBoundsInParent())){
                        return true;
                    }
                    //code change for attack w weapons
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
                        ((ImageView)(n.getNode())).setImage(new Image("file:src/main/resources/Assets/Chests/OpenChestNoBG.png"));
                        if(n instanceof CoinChest){
                            coinsCollected += ((CoinChest) n).openChest();
                            numCoins.setText(String.valueOf(coinsCollected));
                        }
                        else if (n instanceof WeaponChest){
                            hero.openChest((WeaponChest) n);
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
                            gamePane.getChildren().removeAll(go.getNode(),
                                    go.getLeft(), go.getLower(), go.getRight(), go.getUpper());
                            ((Orc) go).setAlive();
                            System.out.println("dead");
                            coinsCollected += ((Orc) go).getCoinsOnKill();
                            numCoins.setText(String.valueOf(coinsCollected));
                            orcs.remove((Orc) go);
                            gameObjects.remove(go);
                        }
                    }
                }
            }
        }
        if (YSpeed > 0) {
            if (go instanceof Orc) {
                if (go.getLower().getBoundsInParent().intersects(hero.getUpper().getBoundsInParent())) {
                    endgame();
                }
            }
        }
        return false;
    }

    private void endgame() throws Exception {

        gamePause = true;
        gameLoop.stop();
        pausedScene = scene;
        GameEndMenu gameEndMenu = new GameEndMenu(game,this.score,this.coinsCollected);
        gameEndMenu.start(stage);
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
            if(o.getY() + o.getNode().getTranslateY() >= ABYSS){
                dead = o;
                System.out.println("dead");
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


    private void updateClicker() {
        if (princess.getNode().getTranslateX() >= -3250) {
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
            //game won menu
        }
    }

    private void doStep(ActionEvent actionEvent) {
        for (GameObject j : gameObjects) {
            Node left = j.getLeft();
            if (!j.equals(hero)) {
//                        System.out.println(" " + left.getBoundsInParent().intersects(hero.getRight().getBoundsInParent()));
                if (j instanceof Orc &&
                        (left.getBoundsInParent().intersects(hero.getRight().getBoundsInParent()))) {
//                    System.out.println("Collided");
                    collidedNode = j;
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
//                        System.out.println("Whoops");
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
        return game;
    }

    public void pause() throws Exception {
        pauseGame();
    }
    private void pauseGame() throws Exception{
        gamePause = true;
        gameLoop.stop();
        pausedScene = scene;
        PauseGameMenu pm = new PauseGameMenu();
        pm.start(stage);
    }

    public void resumeGame(){
        stage.setScene(pausedScene);
        stage.show();
        gamePause = false;
        gameLoop.start();
    }

    private void islandGenerator(){
        islands.add(new Island(18,444,270,100,
                true,"file:src/main/resources/Assets/Islands/T_Islands_02.png"));
        orcs.add(new MediumOrc(140,400,40,40,
                0, 0, "RED", 10, 15,
                "file:src/main/resources/Assets/Orks/big_crimson_ork.png"));
        chests.add(new CoinChest(10,200,408,"file:src/main/resources/Assets/Chests/closedChest.png"));


        islands.add(new Island(375,425,200,75,
                true,"file:src/main/resources/Assets/Islands/T_Islands_03.png"));
        orcs.add(new SmallOrc(386,391,33,33,
                0, 0, "RED", 10, 10,
                "file:src/main/resources/Assets/Orks/big_green_ork.png"));
        orcs.add(new SmallOrc(507,391,33,33,
                0, 0, "RED", 5, 10,
                "file:src/main/resources/Assets/Orks/big_crimson_ork.png"));
        chests.add(new CoinChest(15,432,389,"file:src/main/resources/Assets/Chests/closedChest.png"));


        islands.add(new Island(623,407,150,75,
                true,"file:src/main/resources/Assets/Islands/T_Islands_07.png"));
        orcs.add(new MediumOrc(700,367,40,40,
                0, 0, "RED", 15, 10,
                "file:src/main/resources/Assets/Orks/big_crimson_ork.png"));
        chests.add(new WeaponChest(new Helmet(new ThrowingAxe(), new ThrowingKnife()),640,371,"file:src/main/resources/Assets/Chests/closedChest.png"));


        islands.add(new Island(790,500,225,125,
                true,"file:src/main/resources/Assets/Islands/T_Islands_04.png"));
        orcs.add(new SmallOrc(810,467,33,33,
                0, 0, "RED", 5, 10,
                "file:src/main/resources/Assets/Orks/big_green_ork.png"));
        orcs.add(new MediumOrc(920,460,40,40,
                0, 0, "RED", 5, 10,
                "file:src/main/resources/Assets/Orks/big_crimson_ork.png"));
        chests.add(new WeaponChest(new Helmet(new ThrowingAxe(),new ThrowingKnife()),860,464,"file:src/main/resources/Assets/Chests/closedChest.png"));


        islands.add(new Island(1068,444,270,100,
                true,"file:src/main/resources/Assets/Islands/T_Islands_01.png"));
        orcs.add(new MediumOrc(1270,400,40,40,
                0, 0, "RED", 20, 15,
                "file:src/main/resources/Assets/Orks/big_green_ork.png"));
        obstacles.add(new TNTBlinker(1100, 398, "file:src/main/resources/Assets/WhiteSquare.png"));
        TNT tnt1 = new TNT(1100,398,"file:src/main/resources/Assets/TNT.png");
        obstacles.add(tnt1);
        obstacles.add(new TNTSmoke(1050, 348, "file:src/main/resources/Assets/TNT_smoke-removebg-preview.png"));
        tnt1.setBlinker((TNTBlinker) obstacles.get(obstacles.size() - 3));
        tnt1.setSmoke((TNTSmoke) obstacles.get(obstacles.size() - 1));


        islands.add(new Island(1425,425,200,75,
                true,"file:src/main/resources/Assets/Islands/T_Islands_05.png"));
        orcs.add(new SmallOrc(1436,391,33,33,
                0, 0, "RED", 5, 10,
                "file:src/main/resources/Assets/Orks/big_green_ork.png"));
        orcs.add(new SmallOrc(1537,391,33,33,
                0, 0, "RED", 5, 10,
                "file:src/main/resources/Assets/Orks/big_green_ork.png"));
        chests.add(new WeaponChest(new Helmet(new ThrowingKnife(),new ThrowingAxe()),1480,389,"file:src/main/resources/Assets/Chests/closedChest.png"));


        islands.add(new Island(1673,407,150,75,
                true,"file:src/main/resources/Assets/Islands/T_Islands_11.png"));
        orcs.add(new SmallOrc(1750,367,40,40,
                0, 0, "RED", 5, 10,
                "file:src/main/resources/Assets/Orks/big_crimson_ork.png"));
        obstacles.add(new TNTBlinker(1700, 361, "file:src/main/resources/Assets/WhiteSquare.png"));
        TNT tnt2 = new TNT(1700,361,"file:src/main/resources/Assets/TNT.png");
        obstacles.add(tnt2);
        obstacles.add(new TNTSmoke(1650, 311, "file:src/main/resources/Assets/TNT_smoke-removebg-preview.png"));
        tnt2.setBlinker((TNTBlinker) obstacles.get(obstacles.size() - 3));
        tnt2.setSmoke((TNTSmoke) obstacles.get(obstacles.size() - 1));


        islands.add(new Island(1880,500,300,125,
                true,"file:src/main/resources/Assets/Islands/T_Islands_09.png"));
        orcs.add(new MediumOrc(1960,460,40,40,
                0, 0, "RED", 15, 10,
                "file:src/main/resources/Assets/Orks/big_crimson_ork.png"));
        orcs.add(new MediumOrc(2070,460,40,40,
                0, 0, "RED", 15, 10,
                "file:src/main/resources/Assets/Orks/big_green_ork.png"));
        chests.add(new CoinChest(30,1890,464,"file:src/main/resources/Assets/Chests/closedChest.png"));


        islands.add(new Island(2300,450,1100,300,
                true,"file:src/main/resources/Assets/Islands/T_Islands_06.png"));
        chests.add(new WeaponChest(new Helmet(new ThrowingAxe(),new ThrowingKnife()),2400,414,"file:src/main/resources/Assets/Chests/closedChest.png"));
        orcs.add(new BossOrc(2600,359,90,90,
                0, 0, "GREEN", 100, 10,
                "file:src/main/resources/Assets/Orks/big_green_ork.png"));

    }

    public void startGame(Stage primaryStage)
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

        Text scoreText = new Text();
        scoreText.setLayoutX(152);
        scoreText.setLayoutY(68);
        scoreText.setText(""+score);
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
        ImageView heroProgView = new ImageView(heroProg);
        heroProgView.setX(37);
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
        numCoins.setLayoutX(250);
        numCoins.setLayoutY(28);
        numCoins.setText(String.valueOf(coinsCollected));
        numCoins.setFont(Font.font("Comic Sans MS", 25));
        numCoins.setFill(Color.rgb(255, 249, 2));
        gamePane.getChildren().add(numCoins);

        tempClicker = new Rectangle();
        tempClicker.setLayoutX(50); tempClicker.setLayoutY(130); tempClicker.setWidth(250);
        tempClicker.setHeight(448); tempClicker.setOpacity(0);
        gamePane.getChildren().add(tempClicker);
        gamePane.getChildren().add(scoreText);

        tempClicker.onMouseClickedProperty().set(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent e) {
                updateClicker();
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


}
