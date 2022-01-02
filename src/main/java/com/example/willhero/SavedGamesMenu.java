package com.example.willhero;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class SavedGamesMenu extends Application {

    ArrayList<Label> gameLabelArrayList;
    ArrayList<Label> scoreLabelArrayList;

    AnchorPane anchorPane;
    Stage stage;

    SavedGamesMenu(){
        gameLabelArrayList = new ArrayList<>();
        scoreLabelArrayList = new ArrayList<>();
    }


    public void displaySavedGames(){
        if (MainMenu.savedGamesList.size() == 0){
            System.out.println("List empty");
        }
        for(int i=0;i<MainMenu.savedGamesList.size();i++){
            Label temp = new Label("Game" + (i+1));
            temp.setTextFill(Color.BLACK);
            temp.setLayoutX(36);
            temp.setLayoutY(160 + 100*i);
            temp.setFont(Font.font("Lucida Console", 24));
            anchorPane.getChildren().add(temp);
            gameLabelArrayList.add(temp);

            Label tempScore = new Label(String.valueOf(20));
            tempScore.setTextFill(Color.BLACK);
            tempScore.setLayoutX(250);
            tempScore.setLayoutY(184 + i*100);
            tempScore.setFont(Font.font("Lucida Console",18));
            anchorPane.getChildren().add(tempScore);
            scoreLabelArrayList.add(tempScore);
        }
    }

    private void startSelectedGame(){
        int x = MainMenu.savedGamesList.size();
        if(x==1) {
            gameLabelArrayList.get(0).setOnMouseClicked(e -> {
                try {
                    MainMenu.getInstance().loadState();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
                MainMenu.savedGamesList.remove(0);
//                try {
//                    game.startGame(stage);
//                } catch (Exception exception) {
//                    exception.printStackTrace();
//                }
//                System.out.println(1);
            });
        }
        else if(x==2) {
            gameLabelArrayList.get(0).setOnMouseClicked(e -> {
                Game game = new Game(MainMenu.savedGamesList.get(0));
                MainMenu.savedGamesList.remove(0);
                try {
                    game.startGame(stage);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                System.out.println(1);
            });
            gameLabelArrayList.get(1).setOnMouseClicked(e -> {
                Game game = new Game(MainMenu.savedGamesList.get(1));
                MainMenu.savedGamesList.remove(1);
                try {
                    game.startGame(stage);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                System.out.println(2);
            });
        }
        else if(x==3) {
            gameLabelArrayList.get(0).setOnMouseClicked(e -> {
                Game game = new Game(MainMenu.savedGamesList.get(0));
                MainMenu.savedGamesList.remove(0);
                try {
                    game.startGame(stage);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                System.out.println(1);
            });
            gameLabelArrayList.get(1).setOnMouseClicked(e -> {
                Game game = new Game(MainMenu.savedGamesList.get(1));
                MainMenu.savedGamesList.remove(1);
                try {
                    game.startGame(stage);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                System.out.println(2);
            });
            gameLabelArrayList.get(2).setOnMouseClicked(e -> {
                Game game = new Game(MainMenu.savedGamesList.get(2));
                MainMenu.savedGamesList.remove(2);
                try {
                    game.startGame(stage);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                System.out.println(3);
            });
        }
        else if(x==4) {
            gameLabelArrayList.get(0).setOnMouseClicked(e -> {
                Game game = new Game(MainMenu.savedGamesList.get(0));
                MainMenu.savedGamesList.remove(0);
                try {
                    game.startGame(stage);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                System.out.println(1);
            });
            gameLabelArrayList.get(1).setOnMouseClicked(e -> {
                Game game = new Game(MainMenu.savedGamesList.get(1));
                MainMenu.savedGamesList.remove(1);
                try {
                    game.startGame(stage);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                System.out.println(2);
            });
            gameLabelArrayList.get(2).setOnMouseClicked(e -> {
                Game game = new Game(MainMenu.savedGamesList.get(2));
                MainMenu.savedGamesList.remove(2);
                try {
                    game.startGame(stage);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                System.out.println(3);
            });
            gameLabelArrayList.get(3).setOnMouseClicked(e -> {
                Game game = new Game(MainMenu.savedGamesList.get(3));
                MainMenu.savedGamesList.remove(3);
                try {
                    game.startGame(stage);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                System.out.println(4);
            });
        }
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        primaryStage.getIcons().add(new Image("file:src/main/resources/Assets/Knight.png"));
        AnchorPane root = FXMLLoader.load(getClass().getResource("SavedGamesMenu.fxml"));
        primaryStage.setScene(new Scene(root,310,657));
        anchorPane = root;
        displaySavedGames();
        primaryStage.show();
        startSelectedGame();

    }
}
