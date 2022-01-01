package com.example.willhero;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameEndMenu  extends Application {
    Text currScore;
    Text totalCoinsText;


    private static GameEndMenu gameEndMenu = null;
    int finalCoins;
    int totalCoins;
    int score;
    Stage stage;

    GameEndMenu(){
        gameEndMenu = this;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.getIcons().add(new Image("file:src/main/resources/Assets/Knight.png"));
        AnchorPane gameEndPane = FXMLLoader.load(getClass().getResource("GameEndMenu.fxml"));
        stage.setScene(new Scene(gameEndPane,310,657));
        //scores ka scene bhi yaha sort karna hoga and display karna hoga
        stage.show();
    }

    public static GameEndMenu getInstance(){
        return gameEndMenu;
    }
}
