package com.example.willhero;

public class GameCannotBeContinuedException extends Exception {
    public  GameCannotBeContinuedException (){
        super("This game cannot be continued anymore, there is an error.");
    }
}
