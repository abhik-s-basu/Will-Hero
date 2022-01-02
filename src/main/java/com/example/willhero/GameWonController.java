package com.example.willhero;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameWonController implements Initializable {
    @FXML
    Text congratsMessage;

    @FXML
    Text finalScore;

    @FXML
    Text finalCoins;

    @FXML
    Button mainMenuButton;

    @FXML
    Button restartButton;

    @FXML
    public void restartGame() throws IOException {
        GameEndMenu.getInstance().restart();
        ;//restart game bulaana hai
    }

    @FXML
    void mainMenu () throws Exception{
        Stage stage = (Stage) mainMenuButton.getScene().getWindow();
        MainMenu mainMenu = new MainMenu();
        mainMenu.start(stage); // just a fix
    }

    private static final String p_IDLE_BUTTON_STYLE = "-fx-background-color: #ffffff; -fx-border-color: #9CAAA9; -fx-border-width: 4;";
    private static final String p_HOVERED_BUTTON_STYLE = "-fx-background-color: #9CAAA9; -fx-border-color: #9CAAA9; -fx-border-width: 4;";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        restartButton.setStyle(p_IDLE_BUTTON_STYLE);
        mainMenuButton.setStyle(p_IDLE_BUTTON_STYLE);

        mainMenuButton.setOnMouseEntered(mouseEvent -> {
            mainMenuButton.setStyle(p_HOVERED_BUTTON_STYLE);
        });
        mainMenuButton.setOnMouseExited(mouseEvent -> mainMenuButton.setStyle(p_IDLE_BUTTON_STYLE));

        restartButton.setOnMouseEntered(mouseEvent -> {
            restartButton.setStyle(p_HOVERED_BUTTON_STYLE);
        });
        restartButton.setOnMouseExited(mouseEvent -> restartButton.setStyle(p_IDLE_BUTTON_STYLE));
    }
}
