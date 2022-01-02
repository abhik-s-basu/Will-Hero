package com.example.willhero;

import java.util.ArrayList;

public class SavedGames {
     ArrayList<Game> savedGamesList;
     private static SavedGames savedGames;
     SavedGames(){
         savedGamesList = new ArrayList<Game>();
         savedGames = this;
     }

     public static SavedGames getInstance(){
         return savedGames;
     }

     public ArrayList<Game> viewSavedGames(){
         return savedGamesList;
    }





}
