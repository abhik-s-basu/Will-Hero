package com.example.willhero;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenu extends Application implements Screen {
    AnchorPane mainMenuPane;
    Pane playField;
    Scene scene;
    @Override
    public void start(Stage primaryStage) throws IOException {
        System.out.println("here!!!");
        primaryStage.getIcons().add(new Image("file:src/main/resources/Assets/Knight.png"));
        mainMenuPane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        playField = new Pane();
        playField.setPrefSize(310,657);
//        mainMenuPane.getChildren().addAll(playField);
        scene = new Scene(mainMenuPane,310,657);
        primaryStage.setScene(scene);
        System.out.println("here part 2");
        primaryStage.show();
    }
}
