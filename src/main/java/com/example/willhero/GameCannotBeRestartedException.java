package com.example.willhero;

public class GameCannotBeRestartedException extends Exception {
    public GameCannotBeRestartedException(){
        super("The game cannot be restarted there is an error.");
    }
}
