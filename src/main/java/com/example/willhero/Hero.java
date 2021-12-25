package com.example.willhero;

public class Hero extends GameObject{
    private Helmet helmet;
    private int heroNoOfJumps = 0;
//    private int heroHeight;
    private Weapon currWeapon = null;
    private boolean isRessurected = false;
    private boolean isAlive;
    private Game currGame;
    Hero (Helmet helmet, Game game, double x, double y,
          String imageURL){
        super(x,y,40,0,29,29,true,imageURL);
        this.helmet = helmet;
        this.currGame = game;
        this.display();
    }
    public void resurrect(){
        // code tbd
    }
    public void killHero(){
        //code tbd
    }
    public void jumpInPlace(){
        // code tbd
    }
    public void jumpForward(){
        //code tbd
    }
    public void swapWeaapon(){
        //code tbd
    }
    public void openChest(){
        //code tbd
    }
    public boolean isAlive(){
        return this.isAlive;
    }
    public boolean isRessurected(){
        return this.isRessurected;
    }
    public void useWeapon(){
        //code tbd
    }



}
