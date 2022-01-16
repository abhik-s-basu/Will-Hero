package com.example.willhero;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameWonController implements Initializable {

    @FXML
    private Button mainMenuButton;

    @FXML
    private Button restartButton;

    private static final String p_IDLE_BUTTON_STYLE = "-fx-background-color: #ffffff; -fx-border-color: #9CAAA9; -fx-border-width: 4;";
    private static final String p_HOVERED_BUTTON_STYLE = "-fx-background-color: #9CAAA9; -fx-border-color: #9CAAA9; -fx-border-width: 4;";

    @FXML
    public void restartGame() throws IOException, GameCannotBeRestartedException {
        GameEndMenu.getInstance().restart();
    }

    @FXML
    public void mainMenu () throws Exception{
        Stage stage = (Stage) mainMenuButton.getScene().getWindow();
        MainMenu mainMenu = new MainMenu();
        mainMenu.start(stage);
    }

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
