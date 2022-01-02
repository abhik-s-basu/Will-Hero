package com.example.willhero;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class GameEndMenu  extends Application {

    Text currScore;
    Text numCoins;

    private static GameEndMenu gameEndMenu = null;
    int finalCoins;
    int totalCoins;
    int score;
    Stage stage;

    GameEndMenu(int _score, int _totalCoins){
        score = _score;
        totalCoins = _totalCoins;
        finalCoins = totalCoins;
        gameEndMenu = this;
    }

    public void restart() throws IOException {
        Game game = new Game();
        game.startGame(stage,0.5,true,true);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        primaryStage.getIcons().add(new Image("file:src/main/resources/Assets/Knight.png"));
        AnchorPane gameEndPane = FXMLLoader.load(getClass().getResource("GameEndMenu.fxml"));
        primaryStage.setScene(new Scene(gameEndPane,310,657));
        //scores ka scene bhi yaha sort karna hoga and display karna hoga
        currScore = new Text();
        currScore.setLayoutX(133);
        currScore.setLayoutY(250);
        currScore.setText(String.valueOf(score));
//        System.out.println(String.valueOf(score));
        currScore.setFont(Font.font("Lucida Console", 40));
        currScore.setFill(Color.BLACK);
        gameEndPane.getChildren().add(currScore);

        numCoins = new Text();
        numCoins.setLayoutX(133);
        numCoins.setLayoutY(400);
        numCoins.setText(String.valueOf((totalCoins)));
//        System.out.println(String.valueOf(numCoins));
        numCoins.setFont(Font.font("Lucida Console",40));
        numCoins.setFill(Color.BLACK);
        gameEndPane.getChildren().add(numCoins);

        primaryStage.show();

    }

    public static GameEndMenu getInstance(){
        return gameEndMenu;
    }
}
