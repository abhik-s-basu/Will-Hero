package com.example.willhero;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PauseGameMenuController  {
    @FXML
    private ImageView resume;

    @FXML
    private ImageView musicToggle;

    @FXML
    private ImageView soundToggle;

    @FXML
    private ImageView mainMenuButton;

    @FXML
    private ImageView loadSavedGamesButton;

    @FXML
    private ImageView saveGameButton;

    private boolean musicClicked;
    private boolean soundClicked;



    public void resumeGame(){
//        Game.getInstance().resumeGame(new Stage(),false);
        PauseGameMenu.getInstance().resumeGame2();
    }


    public void pressMusic(){
        Image tempImage;
        if(!musicClicked){
            tempImage = new Image("file:src/main/resources/Assets/MusicOff.png");
            // sound ka bhi sort karna hai yaha pe
        }
        else{
            tempImage = new Image("file:src/main/resources/Assets/MusicOn.png");

        }
        musicToggle.setImage(tempImage);
        musicClicked = !musicClicked;
    }

   public void pressSound(){
        Image tempImage;
        if(!soundClicked){
            tempImage = new Image("file:src/main/resources/Assets/mute_1.png");
        }
        else{
            tempImage = new Image("file:src/main/resources/Assets/volume_1.png");
        }
        soundToggle.setImage(tempImage);
        soundClicked = !soundClicked;
    }

     public void pressMainMenu() throws IOException {
        Stage stage = (Stage)mainMenuButton.getScene().getWindow();
        MainMenu mainMenu = new MainMenu();
        mainMenu.start(stage);

     }

     public void viewSavedGames() throws Exception {
        Stage stage = (Stage)loadSavedGamesButton.getScene().getWindow();
        PauseGameMenu.getInstance().viewSavedGames();
     }

     public void savedGames () throws Exception {
        PauseGameMenu.getInstance().saveGame();
     }


}
