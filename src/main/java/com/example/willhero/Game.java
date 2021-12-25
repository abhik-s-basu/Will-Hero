package com.example.willhero;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Game implements Screen{

    AnchorPane gamePane;
    Pane playField;
    Scene scene;

    public void startGame(Stage primaryStage, double posHero, boolean sound, boolean music) throws IOException {
        System.out.println("Game here!!!");
        primaryStage.getIcons().add(new Image("file:src/main/resources/Assets/Knight.png"));
        gamePane = FXMLLoader.load(getClass().getResource("Game.fxml"));
//        playField = new Pane();
//        playField.setPrefSize(310,657);
//        mainMenuPane.getChildren().addAll(playField);
        scene = new Scene(gamePane,310,657);
        primaryStage.setScene(scene);
        System.out.println("Game here part 2");
        primaryStage.show();
    }
}
