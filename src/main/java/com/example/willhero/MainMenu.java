package com.example.willhero;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenu implements Screen {
    private static MainMenu mainMenu;
    AnchorPane mainMenuPane;
    Scene scene;
    private int totalCoinsCollected;
    MainMenu(){
        mainMenu = this;
    }

    public void putCoins(int numCoins){
        totalCoinsCollected += numCoins;
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
        numCoins.setText(String.valueOf(totalCoinsCollected));
        numCoins.setFont(Font.font("Comic Sans MS", 25));
        numCoins.setFill(Color.rgb(255, 249, 2));
        mainMenuPane.getChildren().add(numCoins);
    }
    public void startMenu(Stage primaryStage, boolean sound, boolean music) throws IOException {
        primaryStage.getIcons().add(new Image("file:src/main/resources/Assets/Knight.png"));
        mainMenuPane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        showCoins();
        scene = new Scene(mainMenuPane,310,657);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static MainMenu getInstance(){
        return mainMenu;
    }
}
