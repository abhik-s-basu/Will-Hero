package com.example.willhero;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenu implements Screen {
    AnchorPane mainMenuPane;
    Scene scene;

    public void startMenu(Stage primaryStage, boolean sound, boolean music) throws IOException {
        primaryStage.getIcons().add(new Image("file:src/main/resources/Assets/Knight.png"));
        mainMenuPane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        scene = new Scene(mainMenuPane,310,657);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
