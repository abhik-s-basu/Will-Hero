package com.example.willhero;

public class Hero extends GameObject{
    private Helmet helmet;
    private int heroNoOfJumps = 0;
//    private int heroHeight;
    private Weapon currWeapon = null;
    private boolean isResurrected = false;
    private boolean isAlive;
    private Game curGame;
//    Hero (Helmet helmet, Game game, double x, double y,
//          String imageURL)
      Hero(double x, double y, Game game, String imageURL){
        super(x,y,50,0,29,29,true,imageURL);
//        this.helmet = helmet;
        this.curGame = game;
//        this.display();// super mein hi daldenge
    }
    public void resurrect(){
        // code tbd
    }
    public void killHero(){
        //code tbd
    }
    public void jumpInPlace() {
        for (int i = 0; i < Math.abs(getYSpeed()); i++) {
            if (curGame.checkCollision(this, getYSpeed())){
                setYSpeed(getYSpeed() - 15);
                System.out.println("flsmlfcms");
            }
            getNode().setTranslateY(getNode().getTranslateY() + ((getYSpeed() > 0) ? 1 : -1)); //s = ut + 1/2 at^2
        }
    }

    public void jumpForward(){
        //screen moving back;
    }
    public void swapWeapon(){
        //code tbd
    }
    public void openChest(){
        //code tbd
    }
    public boolean isAlive(){
        return this.isAlive;
    }
    public boolean isResurrected(){
        return this.isResurrected;
    }
    public void useWeapon(){
        //code tbd
    }



}
