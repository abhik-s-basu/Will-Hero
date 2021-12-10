package com.example.willhero;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartMenu extends Application implements Initializable {

    @Override
    public void start(Stage stage) throws IOException {
        try{
            stage.getIcons().add(new Image("file:src/main/resources/Assets/Knight.png"));
            Parent root = FXMLLoader.load(getClass().getResource("StartMenu.fxml"));
            Scene scene = new Scene (root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}