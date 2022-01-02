package com.example.willhero;

import java.awt.*;

public class Hero extends GameObject{

    private Helmet helmet;
    private int heroNoOfJumps = 0;
    private Weapon curWeapon;
    private boolean isResurrected;
    private boolean isAlive;
    private Game curGame;

//    Hero (Helmet helmet, Game game, double x, double y,
//          String imageURL)

    Hero(double x, double y, Game game, String imageURL){
        super(x,y,50,0,29,29,true,imageURL);
        this.helmet = new Helmet(new ThrowingAxe(), new ThrowingKnife());
        isResurrected = false;
        curWeapon = null;
        this.curGame = game;
        super.getLower().setLayoutY(super.getY() + super.getBreadth() - 25);
        super.setLower(25);
    }

    public Helmet getHelmet() {
        return helmet;
    }

    public void resurrect(){
        // code tbd
    }
    public void killHero(){
        //code tbd
    }
    public void jumpInPlace() throws Exception {
        for (int i = 0; i < Math.abs(getYSpeed()); i++) {
            if (curGame.checkCollisionY(this, getYSpeed())){
                setYSpeed(-14);
//                System.out.println(getYSpeed());
            }
            getUpper().setTranslateY(getUpper().getTranslateY() + ((getYSpeed() > 0) ? 1 : -1));
            getLower().setTranslateY(getLower().getTranslateY() + ((getYSpeed() > 0) ? 1 : -1));
            getRight().setTranslateY(getRight().getTranslateY() + ((getYSpeed() > 0) ? 1 : -1));
            getLeft().setTranslateY(getLeft().getTranslateY() + ((getYSpeed() > 0) ? 1 : -1));
            getNode().setTranslateY(getNode().getTranslateY() + ((getYSpeed() > 0) ? 1 : -1)); //s = ut + 1/2 at^2
        }
    }

    public void jumpForward(){
        //screen moving back;
    }
    public void swapWeapon(){
        //code tbd
    }
    public void openChest(WeaponChest wc){
        curWeapon = wc.openChest();
    }

    public Weapon getCurWeapon(){
        return this.curWeapon;
    }

    public void setCurWeapon(Weapon curWeapon){
        this.curWeapon = curWeapon;
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
