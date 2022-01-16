package com.example.willhero;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class PauseGameMenu extends Application implements Screen {
    private static PauseGameMenu pauseMenu = null;
    private Game currPausedGame;
    private Stage stage;


    PauseGameMenu(Game currGame){
        currPausedGame = currGame;
        pauseMenu = this;
    }
    {
        pauseMenu = this;
    }


    public static PauseGameMenu getInstance(){
        return pauseMenu ;
    }
    public void resumeGame2(){
        currPausedGame.resumeGame(stage,false);
    }

//    public void restartGame() throws IOException {
//        Game game = new Game();
//        game.startGame(stage);
//    }

//    public void viewSavedGames() throws Exception {
//        savedGamesMenu.start(stage);
//    }

    public void saveGame(){
        try{
            FileOutputStream fileOutputStream = new FileOutputStream("main.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);

            out.writeObject(currPausedGame.getHero());
            for (GameObject i : currPausedGame.getAllObjects()){
                out.writeObject(i);
            }
            out.close();
            fileOutputStream.close();
            System.out.println("Serialisation hua hai");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        primaryStage.getIcons().add(new Image("file:src/main/resources/Assets/Knight.png"));
        AnchorPane gameEndPane = FXMLLoader.load(getClass().getResource("PauseGameMenu.fxml"));
        primaryStage.setScene(new Scene(gameEndPane,310,657));
        primaryStage.show();
    }
}
