package com.example.willhero;

public class GameNotFoundException extends Exception {
    public GameNotFoundException(){
        super("Sorry this game cannot be found there is an error.");
    }
}
