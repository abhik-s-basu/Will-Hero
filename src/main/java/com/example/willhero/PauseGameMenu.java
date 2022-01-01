package com.example.willhero;

import javafx.application.Application;
import javafx.stage.Stage;

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
//    public void restartGame(){
//        Game game = new Game();
//        game.startGame(stage,5.0,);
//    }

    @Override
    public void start(Stage stage) throws Exception {

    }
}
