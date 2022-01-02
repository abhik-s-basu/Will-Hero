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
    private static SavedGamesMenu savedGamesMenu = new SavedGamesMenu();
    Game currPausedGame;
    Stage stage;


    PauseGameMenu(Game currGame){
        currPausedGame = currGame;
        pauseMenu = this;
//        savedGamesMenu = new SavedGamesMenu();
    }
    {
        pauseMenu = this;
    }


    public static PauseGameMenu getInstance(){
        return pauseMenu ;
    }
    public void resumeGame2(){
//        System.out.println();
        currPausedGame.resumeGame(stage,false);
    }
    public void restartGame() throws IOException {
        Game game = new Game();
        game.startGame(stage);
    }

    public void viewSavedGames() throws Exception {
        savedGamesMenu.start(stage);
    }
    public void saveGame(){
        int gameListSize = MainMenu.getInstance().savedGamesList.size();
        if(gameListSize<4) {
            MainMenu.getInstance().savedGamesList.add(currPausedGame);
        }
        else{
            for(int i=1;i<=3;i++){
                MainMenu.getInstance().savedGamesList.set(i-1,
                        MainMenu.getInstance().savedGamesList.get(i));
            }
            MainMenu.getInstance().savedGamesList.set(3,currPausedGame);
        }
        System.out.println(MainMenu.getInstance().savedGamesList.size());
        System.out.println(MainMenu.getInstance().savedGamesList.get(0).getHero().getX());
        saveState();
    }

    public void saveState(){
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
