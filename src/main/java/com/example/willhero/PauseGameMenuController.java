package com.example.willhero;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
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

    PauseGameMenuController(){
        musicClicked = false;
        soundClicked = false;
    }

    @FXML
    void resumeGame(MouseEvent event){
        PauseGameMenu.getInstance().resumeGame();
    }

    void pressMusic(MouseEvent event){
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

    void pressSound(MouseEvent event){
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

     void pressMainMenu(MouseEvent event){
        Stage stage = (Stage)mainMenuButton.getScene().getWindow();
        MainMenu mainMenu = new MainMenu();
//        mainMenu.start(stage);

     }

     void viewSavedGames(MouseEvent event){
        //karna hai
     }

     void savedGames (MouseEvent event){
        //karna hai
     }


}
