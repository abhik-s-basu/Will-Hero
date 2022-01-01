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

    public void pressPause(MouseEvent event) throws Exception {
        Game.getInstance().pause();
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






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        translateCloud();
        translateIsland(mainIsland, 2000, 15); //for i in islands
        translateIsland(floatingIsland, 5000, 25);
        translateIsland(floatingIsland2, 4000, 10);
    }
}