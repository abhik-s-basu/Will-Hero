package com.example.willhero;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PauseGameMenu extends Application implements Screen {
    private static PauseGameMenu pauseMenu = null;
    Game currPausedGame;
    Stage stage;

    public static PauseGameMenu getInstance(){
        return pauseMenu;
    }
    public void resumeGame(){
//        currPausedGame.resumeGame(stage,false);
    }
    public void restartGame() throws IOException {
        Game game = new Game();
        game.startGame(stage,5.0,true,true);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.getIcons().add(new Image("file:src/main/resources/Assets/Knight.png"));
        AnchorPane gameEndPane = FXMLLoader.load(getClass().getResource("GameEndMenu.fxml"));
        stage.setScene(new Scene(gameEndPane,310,657));
        stage.show();
    }
}
