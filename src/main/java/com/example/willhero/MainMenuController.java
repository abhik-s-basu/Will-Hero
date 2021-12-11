package com.example.willhero;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

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

    private int settingsClickCount;
    private int soundClickCount;
    private int musicClickCount;
    private boolean hasGameStarted;

    public MainMenuController(){
        settingsClickCount = 0;
        soundClickCount = 0;
        musicClickCount = 0;
        hasGameStarted = false;
    }

    private void translateFloatingIsland(){
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(floatingIsland);
        translate.setDuration(Duration.millis(5000));
        translate.setCycleCount(TranslateTransition.INDEFINITE);
        translate.setByY(25);
        translate.setAutoReverse(true);
        translate.play();
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

    private void translateMainIsland(){
        TranslateTransition translate1 = new TranslateTransition();
        translate1.setNode(mainIsland);
        translate1.setDuration(Duration.millis(2000));
        translate1.setCycleCount(TranslateTransition.INDEFINITE);
        translate1.setByY(15);
        translate1.setAutoReverse(true);
        translate1.play();
    }

    private void translateHero(){
        TranslateTransition translate2 = new TranslateTransition();
        translate2.setNode(hero);
        translate2.setDuration(Duration.millis(550));
        translate2.setCycleCount(TranslateTransition.INDEFINITE);
        translate2.setByY(-60);
        translate2.setAutoReverse(true);
        translate2.play();
    }

    private void translateGreenOrc(){
        TranslateTransition translate3 = new TranslateTransition();
        translate3.setNode(greenOrc);
        translate3.setDelay(Duration.millis(50));
        translate3.setDuration(Duration.millis(700));
        translate3.setCycleCount(TranslateTransition.INDEFINITE);
        translate3.setByY(-75);
        translate3.setAutoReverse(true);
        translate3.play();
    }

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
        TranslateTransition translate5 = new TranslateTransition();
        translate5.setNode(screen);
        translate5.setDuration(Duration.millis(500));
        translate5.setByY(flag*(-800));
        translate5.setAutoReverse(false);
        translate5.setInterpolator(Interpolator.EASE_BOTH);
        translate5.play();
    }

    public void clickThrowingKnife(MouseEvent event){
        System.out.println("Knife clicked!");
    }

    public void clickThrowingAxe(MouseEvent event){
        System.out.println("Axe clicked!");
    }

    public void clickPause(MouseEvent event){
        System.out.println("Pause clicked!"); //save game, load game, sound, music
    }

    //progress bar

    private void setVolumeAndSound(){ //need to fix the double click bug
        TranslateTransition translate6 = new TranslateTransition();
        translate6.setNode(soundGroup);
        translate6.setDuration(Duration.millis(500));
        if (settingsClickCount % 2 == 0){
            translate6.setByX(75);
        }
        else{
            translate6.setByX(-75);
        }
        translate6.setAutoReverse(false);
        translate6.setInterpolator(Interpolator.EASE_BOTH);
        translate6.play();
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
        if (musicClickCount % 2 == 0){
            tempImage = new Image("file:src/main/resources/Assets/MusicOff.png");
            musicImage.setImage(tempImage);
        }
        else{
            tempImage = new Image("file:src/main/resources/Assets/MusicOn.png");
            musicImage.setImage(tempImage);
        }
        musicClickCount++;
    }

    public void pressSound(MouseEvent event){
        Image tempImage;
        if (soundClickCount % 2 == 0){
            tempImage = new Image("file:src/main/resources/Assets/mute_1.png");
            soundImage.setImage(tempImage);
        }
        else{
            tempImage = new Image("file:src/main/resources/Assets/volume_1.png");
            soundImage.setImage(tempImage);
        }
        soundClickCount++;
    }

    public void pressLeaderboard(MouseEvent event){
        System.out.println("Leaderboard displayed!");
    }

    public void pressSettings(MouseEvent event){ setVolumeAndSound(); settingsClickCount++; }

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
        if(settingsClickCount%2 == 1){
            TranslateTransition translate7 = new TranslateTransition();
            translate7.setNode(soundGroup);
            translate7.setDuration(Duration.millis(500));
            translate7.setByX(-75);
            translate7.setInterpolator(Interpolator.EASE_BOTH);
            translate7.setAutoReverse(false);
            translate7.play();
            settingsClickCount++;
        }
        if (!hasGameStarted){
            tapToBegin.setVisible(false);
            cursor.setVisible(false);
            bannerName.setVisible(false);
            toHideComponents.setVisible(false);
            startGameComponents.setVisible(true);
            /* load -> 20,610
            quit -> 243,605
            settings -> 0,-1
            leaderBoard -> 125, 605 */
            System.out.println("Start Game!");
            hasGameStarted = true;
        }
        else{
            playGame();
        }

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        translateFloatingIsland();
        translateCloud();
        rotateBannerName();
        translateMainIsland();
        translateHero();
        translateGreenOrc();
        scaleCursor();
        startGameComponents.setVisible(false);
    }
}
