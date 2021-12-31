package com.example.willhero;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
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
        Orc orc = new SmallOrc(40, 40, 40, 40, 0, 10,
                "GREEN", 5, 10,
                "file:src/main/resources/Assets/Coin.png");
        primaryStage.getIcons().add(new Image("file:src/main/resources/Assets/Knight.png"));
        gamePane = FXMLLoader.load(getClass().getResource("Game.fxml"));
        Group g = new Group();
        g.getChildren().add(orc.display());
//        playField = new Pane();
//        playField.setPrefSize(310,657);
        gamePane.getChildren().addAll(g);
        scene = new Scene(gamePane,310,657);
        primaryStage.setScene(scene);
        System.out.println("Game here part 2");
        primaryStage.show();
    }
}
