package com.example.willhero;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import javafx.scene.shape.Rectangle;
import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    @FXML
    private Group floatingIsland;
    @FXML
    private ImageView bannerName;
    @FXML
    private ImageView hero;
    @FXML
    private Group mainIsland;
    @FXML
    private ImageView greenOrc;
    @FXML
    private ImageView cursor;
    @FXML
    private Group cloud;
    @FXML
    private Text tapToBegin;
    @FXML
    private Group soundGroup;
    @FXML
    private Group quitScreen;
    @FXML
    private Group toHideComponents;
    @FXML
    private Group savedGameScreen;
    @FXML
    private ImageView soundImage;
    @FXML
    private ImageView musicImage;
    @FXML
    private Group startGameComponents;
    @FXML
    private Group saveAndLoadGame;
    @FXML
    private ImageView tnt;
    @FXML
    private ImageView tntSmoke;
    @FXML
    private ImageView chest;
    @FXML
    private ImageView explosionSquare;
    @FXML
    private Rectangle bottomBlocker;
    @FXML
    private Rectangle topBlocker;

    private boolean settingsClickCount;
    private boolean soundClickCount;
    private boolean musicClickCount;
    private boolean hasGameStarted;
    private boolean pauseClickCount;
    private boolean chestClickCount;

    public MainMenuController(){
        settingsClickCount = false;
        soundClickCount = false;
        musicClickCount = false;
        hasGameStarted = false;
        pauseClickCount = false;
        chestClickCount = false;
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

    private void rotateBannerName(){
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(bannerName);
        rotate.setDuration(Duration.millis(2000));
        rotate.setCycleCount(TranslateTransition.INDEFINITE);
        rotate.setInterpolator(Interpolator.EASE_OUT);
        rotate.setByAngle(90);
        rotate.setAutoReverse(true);
        rotate.setAxis(Rotate.Y_AXIS);
        rotate.play();
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

//    private void translateHero(){
//        TranslateTransition translate2 = new TranslateTransition();
//        translate2.setNode(hero);
//        translate2.setDuration(Duration.millis(550));
//        translate2.setCycleCount(TranslateTransition.INDEFINITE);
//        translate2.setByY(-60);
//        translate2.setAutoReverse(true);
//        translate2.play();
//    }

//    private void translateGreenOrc(){ //very similar to upper function
//        TranslateTransition translate3 = new TranslateTransition();
//        translate3.setNode(greenOrc);
//        translate3.setDelay(Duration.millis(50));
//        translate3.setDuration(Duration.millis(700));
//        translate3.setCycleCount(TranslateTransition.INDEFINITE);
//        translate3.setByY(-75);
//        translate3.setAutoReverse(true);
//        translate3.play();
//    }

    private void scaleCursor(){
        ScaleTransition scale = new ScaleTransition();
        scale.setNode(cursor);
        scale.setDuration(Duration.millis(1000));
        scale.setCycleCount(TranslateTransition.INDEFINITE);
        scale.setInterpolator(Interpolator.LINEAR);
        scale.setByX(0.2);
        scale.setByY(0.2);
        scale.play();
    }

    private void setScreen(int flag, Group screen){
        bottomBlocker.setVisible(true);
        TranslateTransition translate5 = new TranslateTransition();
        translate5.setNode(screen);
        translate5.setDuration(Duration.millis(500));
        translate5.setByY(flag*(-800));
        translate5.setAutoReverse(false);
        translate5.setInterpolator(Interpolator.EASE_BOTH);
        translate5.play();
        translate5.setOnFinished(e->{
            bottomBlocker.setVisible(false);
        });
    }

    public void clickThrowingKnife(MouseEvent event){
        System.out.println("Knife clicked!");
    }

    public void clickThrowingAxe(MouseEvent event){
        System.out.println("Axe clicked!");
    }

    public void clickSaveGame(MouseEvent event){
        System.out.println("Game Saved!");
    }

    public void clickBackToMainMenu(MouseEvent event){
        tapToBegin.setVisible(true);
        cursor.setVisible(true);
        bannerName.setVisible(true);
        toHideComponents.setVisible(true);
        startGameComponents.setVisible(false);
        pressPause(event);
        hasGameStarted = false;
    }

    public void pressPause(MouseEvent event){
        setSideElements(soundGroup, pauseClickCount);
        setSideElements(saveAndLoadGame, pauseClickCount);
        pauseClickCount = !pauseClickCount;
    }

    public void clickChest(MouseEvent event){ //will be changed later
        Image tempImage;
        if (!chestClickCount){
            tempImage = new Image("file:src/main/resources/Assets/Chests/openChest.png");
            chest.setImage(tempImage);
        }
        else{
            tempImage = new Image("file:src/main/resources/Assets/Chests/closedChest.png");
            chest.setImage(tempImage);
        }
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

    public void exitGame(MouseEvent event){
        Platform.exit();
        System.exit(0);
    }

    public void continueGame(MouseEvent event){
        setScreen(-1, quitScreen);
    }

    public void BackToGame(MouseEvent event){
        setScreen(-1, savedGameScreen);
    }

    public void loadSavedGame(MouseEvent event){
        if(((Text)event.getSource()).getText().equals("Empty Slot")){
            System.out.println("Cannot load game!");
        }
        else{
            System.out.println("Load saved game!");
        }
    }

    public void pressMusic(MouseEvent event){
        Image tempImage;
        if (!musicClickCount){
            tempImage = new Image("file:src/main/resources/Assets/MusicOff.png");//needs to be edited later
            musicImage.setImage(tempImage);
        }
        else{
            tempImage = new Image("file:src/main/resources/Assets/MusicOn.png");
            musicImage.setImage(tempImage);
        }
        musicClickCount = !musicClickCount;
    }

    public void pressSound(MouseEvent event){
        Image tempImage;
        if (!soundClickCount){
            tempImage = new Image("file:src/main/resources/Assets/mute_1.png");
            soundImage.setImage(tempImage);
        }
        else{
            tempImage = new Image("file:src/main/resources/Assets/volume_1.png");
            soundImage.setImage(tempImage);
        }
        soundClickCount = !soundClickCount;
    }

    public void pressLeaderboard(MouseEvent event){
        System.out.println("Leaderboard displayed!");
    }

    public void pressSettings(MouseEvent event){
        setSideElements(soundGroup, settingsClickCount);
        settingsClickCount = !settingsClickCount;
    }

    public void pressQuit(MouseEvent event){
        setScreen(1, quitScreen);
    }

    public void pressLoadGame(MouseEvent event){
        setScreen(1, savedGameScreen);
    }

    public void playGame(){

        System.out.println("Game has begun!");
    }

    public void startGame(MouseEvent event){
        if(settingsClickCount){
            TranslateTransition translate7 = new TranslateTransition();
            translate7.setNode(soundGroup);
            translate7.setDuration(Duration.millis(500));
            translate7.setByX(-75);
            translate7.setInterpolator(Interpolator.EASE_BOTH);
            translate7.setAutoReverse(false);
            translate7.play();
            settingsClickCount = !settingsClickCount;
        }
        if (!hasGameStarted){
            tapToBegin.setVisible(false);
            cursor.setVisible(false);
            bannerName.setVisible(false);
            toHideComponents.setVisible(false);
            startGameComponents.setVisible(true);
            System.out.println("Start Game!");
            hasGameStarted = true;
        }
        else{
            playGame();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        translateCloud();
        rotateBannerName();
        translateIsland(mainIsland, 2000, 15);
        translateIsland(floatingIsland, 5000, 25);
//        translateHero();
//        translateGreenOrc();
        setAnimateListener
        scaleCursor();
        tntSmoke.setVisible(false);
        topBlocker.setVisible(false);
        bottomBlocker.setVisible(false);
        startGameComponents.setVisible(false);
    }
}
