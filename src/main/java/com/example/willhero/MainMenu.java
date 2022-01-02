package com.example.willhero;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.*;
import java.util.ArrayList;

public class MainMenu implements Screen {
    private static MainMenu mainMenu;
    AnchorPane mainMenuPane;
    Scene scene;
    int totalCoinsCollected = 20;
    ArrayList<Game> savedGamesList;

    private String backgroundMusicFile = "src/main/resources/Assets/Sounds/letithappen.wav";
    Media backgroundMusic;
    MediaPlayer backgroundMusicPlayer;

    public ArrayList<Game> viewSavedGames(){
        return savedGamesList;
    }
    MainMenu(){

        mainMenu = this;
        savedGamesList = new ArrayList<>();
    }

//    public void putCoins(int numCoins){
//        totalCoinsCollected += numCoins;
//    }

    public int getNumCoins(){
        return totalCoinsCollected;
    }
    public void setNumCoins(int _totalCoinsCollected){
        System.out.println(_totalCoinsCollected + "dc");
        totalCoinsCollected = _totalCoinsCollected;
//        System.out.println(totalCoinsCollected + "nds");
    }

    void showCoins(){
        Image coinPic = new Image("file:src/main/resources/Assets/Coin.png");
        ImageView coinView = new ImageView(coinPic);
        coinView.setFitWidth(25);
        coinView.setFitHeight(25);
        coinView.setLayoutX(280);
        coinView.setLayoutY(12);
        mainMenuPane.getChildren().add(coinView);

        Text numCoins = new Text();
        numCoins.setLayoutX(250);
        numCoins.setLayoutY(33);
        numCoins.setText(String.valueOf(getNumCoins()));
        numCoins.setFont(Font.font("Comic Sans MS", 25));
        numCoins.setFill(Color.rgb(255, 249, 2));
        mainMenuPane.getChildren().add(numCoins);
    }
    public void start(Stage primaryStage) throws IOException {
        primaryStage.getIcons().add(new Image("file:src/main/resources/Assets/Knight.png"));
        mainMenuPane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        showCoins();
        backgroundMusic = new Media(new File(backgroundMusicFile).toURI().toString());
        backgroundMusicPlayer = new MediaPlayer(backgroundMusic);
        backgroundMusicPlayer.setVolume(0.05);
        backgroundMusicPlayer.play();
        scene = new Scene(mainMenuPane,310,657);
        primaryStage.setScene(scene);
        primaryStage.show();
//        loadState();
    }

    public static MainMenu getInstance(){
        return mainMenu;
    }

    public void loadState(){
        System.out.println("load state pe hai");
//            RegenerateObstacles regen = new RegenerateObstacles();
//            regen.regenerateGameObjects("Game.txt");


    }
}
