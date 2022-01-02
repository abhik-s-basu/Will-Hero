package com.example.willhero;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class SavedGamesMenuController {

    @FXML
    ImageView mainMenuButton;

    @FXML
    void backToMainMenu() throws IOException {
        Stage stage = (Stage)mainMenuButton.getScene().getWindow();
        MainMenu mainMenu = new MainMenu();
        mainMenu.start(stage);
    }
}
