package com.example.willhero;

public class Hero extends GameObject{
    private Helmet helmet;
    private int heroNoOfJumps = 0;
//    private int heroHeight;
    private Weapon currWeapon = null;
    private boolean isRessurected = false;
    private boolean isAlive;
    private Game currGame;
//    Hero (Helmet helmet, Game game, double x, double y,
//          String imageURL)
      Hero(double x, double y, String imageURL){
        super(x,y,50,0,29,29,true,imageURL);
//        this.helmet = helmet;
//        this.currGame = game;
//        this.display();// super mein hi daldenge
    }
    public void resurrect(){
        // code tbd
    }
    public void killHero(){
        //code tbd
    }
    public void jumpInPlace() {
        if (isMoving()) {
            setYSpeed(getYSpeed() - 7.5);
            setMoving(false);
        }
        for (int i = 0; i < Math.abs(getYSpeed()); i++) {
//            if (node.getBoundsInParent().intersects(Island1.getBoundsInParent())) {
//                if (YVelocity > 0) {
//                    if (node.getTranslateY() == Island1.getTranslateY()) {
//                        if(node == hero){
//                            canJump = true;
//                        }
//                        else if (node == greenOrc){
//                            canJumpOrc = true;
//                        }
//                        return;
//                    }
//                }
//            }
            getNode().setTranslateY(getNode().getTranslateY() + ((getYSpeed() > 0) ? 1 : -1)); //s = ut + 1/2 at^2
        }
    }
    public void jumpForward(){
        System.out.println("here");
        for (int i = 0; i < Math.abs(getXSpeed()); i++){
            getNode().setTranslateX(getNode().getTranslateX() + ((getXSpeed() > 0) ? 1 : -1)); //s = ut + 1/2 at^2
        }
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
