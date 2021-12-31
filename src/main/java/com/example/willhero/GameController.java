package com.example.willhero;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML
    private Text HeroPositionCounter;

    @FXML
    private ImageView Island1;

    @FXML
    private Group pauseObjects;

    @FXML
    private ImageView chest;

    @FXML
    private Group cloud;

    @FXML
    private ImageView explosionSquare;

    @FXML
    private Group floatingIsland;

    @FXML
    private Group floatingIsland2;

    @FXML
    private ImageView greenOrc;

    @FXML
    private ImageView hero;

    @FXML
    private Group mainIsland;

    @FXML
    private ImageView musicImage;

    @FXML
    private Group savedGameScreen;

    @FXML
    private ImageView soundImage;

    @FXML
    private ImageView tnt;

    @FXML
    private ImageView tntSmoke;

    @FXML
    private Rectangle topBlocker;

    @FXML
    private Rectangle tempClicker;

    private boolean chestClickCount;
    private boolean pauseClickCount;
    private boolean soundClickCount;
    private boolean musicClickCount;

    private ArrayList<Node> islands;
    private Point2D heroVelocity;
    private Point2D orcVelocity;
    private boolean canJump;
    private boolean canJumpOrc;
    private AnimationTimer timer;
    private double gravity;


    public GameController(){
        chestClickCount = false;
        pauseClickCount = false;
        soundClickCount = false;
        musicClickCount = false;
        canJump = false;
        canJumpOrc = false;
        islands = new ArrayList<Node>();
        heroVelocity = new Point2D(0,0);
        orcVelocity = new Point2D(0,0);
        gravity = 0.25;
    }

    private void translateCloud(){
        TranslateTransition translate4 = new TranslateTransition();
        translate4.setNode(cloud);
        translate4.setDuration(Duration.millis(20000));
        translate4.setCycleCount(TranslateTransition.INDEFINITE);
        translate4.setByX(900);
        translate4.setAutoReverse(false);
        translate4.setInterpolator(Interpolator.LINEAR);
        translate4.play();
    }

    private void translateIsland(Group island, int duration, int translateY){
        TranslateTransition translate1 = new TranslateTransition();
        translate1.setNode(island);
        translate1.setDuration(Duration.millis(duration));
        translate1.setCycleCount(TranslateTransition.INDEFINITE);
        translate1.setByY(translateY);
        translate1.setAutoReverse(true);
        translate1.play();
    }

    public void clickChest(MouseEvent event){ //will be changed later
        Image tempImage;
        if (!chestClickCount){
            tempImage = new Image("file:src/main/resources/Assets/Chests/openChest.png");
        }
        else{
            tempImage = new Image("file:src/main/resources/Assets/Chests/closedChest.png");
        }
        chest.setImage(tempImage);
        chestClickCount = !chestClickCount;
    }

    public void clickTNT(MouseEvent event){ //will be changed later
        FadeTransition fade1 = new FadeTransition();
        fade1.setNode(tnt);
        fade1.setDuration(Duration.millis(250));
        fade1.setCycleCount(10);
        fade1.setFromValue(1.0);
        fade1.setToValue(0.0);
        fade1.setInterpolator(Interpolator.LINEAR);
        fade1.play();
        fade1.setOnFinished((e1)->{
            explosionSquare.setVisible(false);
            FadeTransition fade2 = new FadeTransition();
            tntSmoke.setVisible(true);
            fade2.setNode(tntSmoke);
            fade2.setDuration(Duration.millis(2000));
            fade2.setCycleCount(1);
            fade2.setFromValue(1.0);
            fade2.setToValue(0.0);
            fade2.play();
            fade2.setOnFinished((e2)->{
                tnt.setOpacity(1.0);
                tnt.setVisible(true);
                explosionSquare.setVisible(true);
                tntSmoke.setVisible(false);
            });
        });
    }

    public void BackToGame(MouseEvent event){
        setScreen(-1, savedGameScreen);
    }

    public void clickBackToMainMenu(MouseEvent event) throws IOException{
        System.out.println("Back to main menu");
        Stage stage = (Stage)  floatingIsland.getScene().getWindow();
        MainMenu mm = new MainMenu();
        mm.startMenu(stage, soundClickCount, musicClickCount);
    }

    public void clickSaveGame(MouseEvent event) {
        System.out.println("Game Saved"); //need to do
    }

    public void clickThrowingAxe(MouseEvent event) {
        System.out.println("Throwing Axe clicked");
    }

    public void clickThrowingKnife(MouseEvent event) {
        System.out.println("Throwing Knife");
    }

    public void loadSavedGame(MouseEvent event){
        if(((Text)event.getSource()).getText().equals("Empty Slot")){
            System.out.println("Cannot load game!");
        }
        else{
            System.out.println("Load saved game!");
        }
    }

    public void pressLoadGame(MouseEvent event){
        setScreen(1, savedGameScreen);
    }

    public void pressMusic(MouseEvent event){
        Image tempImage;
        if (!musicClickCount){
            tempImage = new Image("file:src/main/resources/Assets/MusicOff.png");//needs to be edited later
        }
        else{
            tempImage = new Image("file:src/main/resources/Assets/MusicOn.png");
        }
        musicImage.setImage(tempImage);
        musicClickCount = !musicClickCount;
    }

    public void pressPause(MouseEvent event){
        setSideElements(pauseObjects, pauseClickCount);
        pauseClickCount = !pauseClickCount;
    }

    public void pressSound(MouseEvent event){
        Image tempImage;
        if (!soundClickCount){
            tempImage = new Image("file:src/main/resources/Assets/mute_1.png");
        }
        else{
            tempImage = new Image("file:src/main/resources/Assets/volume_1.png");
        }
        soundImage.setImage(tempImage);
        soundClickCount = !soundClickCount;
    }

    private void setScreen(int flag, Group screen){
        topBlocker.setVisible(true);
        TranslateTransition translate5 = new TranslateTransition();
        translate5.setNode(screen);
        translate5.setDuration(Duration.millis(500));
        translate5.setByY(flag*(-800));
        translate5.setAutoReverse(false);
        translate5.setInterpolator(Interpolator.EASE_BOTH);
        translate5.play();
        translate5.setOnFinished(e->{
            topBlocker.setVisible(false);
        });
    }

    private void setSideElements(Group group, boolean count){
        topBlocker.setVisible(true);
        TranslateTransition translate6 = new TranslateTransition();
        translate6.setNode(group);
        translate6.setDuration(Duration.millis(500));
        if (!count){
            translate6.setByX(75);
        }
        else{
            translate6.setByX(-75);
        }
        translate6.setAutoReverse(false);
        translate6.setInterpolator(Interpolator.EASE_BOTH);
        translate6.play();
        translate6.setOnFinished(e->{
            topBlocker.setVisible(false);
        });
    }


//
//    public Rectangle getTempClicker (){
//        return this.tempClicker;
//    }

//    private void update(){
//        jumpHero();
//        jumpOrc();
//        tempClicker.onMouseClickedProperty().set(new EventHandler<MouseEvent>(){
//            public void handle(MouseEvent e) {
//                if (hero.getTranslateX() <= 1000) { //1000 needs to be changed
//                    movePlayerX(50, hero); //40ms (change to 1s) this is moving 50
//                }
//            }
//        });
//
//        if (heroVelocity.getY() < 9){
//            heroVelocity = heroVelocity.add(0, gravity); //v = u + at
////            System.out.println(hero.getTranslateY());
//        }
//        if (orcVelocity.getY() < 9){
//            orcVelocity = orcVelocity.add(0, gravity); //v = u + at
////            System.out.println(hero.getTranslateY());
//        }
//        movePlayerY((int) heroVelocity.getY(), hero);
//        movePlayerY((int) orcVelocity.getY(), greenOrc);
//    }
//
//    //s = ut + 1/2*at^2
//    //v = u + at
//
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
//
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
//
//
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        translateCloud();
        translateIsland(mainIsland, 2000, 15); //for i in islands
        translateIsland(floatingIsland, 5000, 25);
        translateIsland(floatingIsland2, 4000, 10);
        topBlocker.setVisible(false); //pause menu
    }
}