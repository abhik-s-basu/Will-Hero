package com.example.willhero;

public abstract class Orc extends  GameObject {
    private  String color;
    private  int size;
    private  int coinsOnKill;
    private int health; //looks useless rn
    private boolean isAlive;
    private Game game;
    Orc(double x, double y,double length, double breadth,int xSpeed,
        int ySpeed, String color, int coinsOnKill, int health,String imageURL){
        super(x,y,xSpeed,ySpeed,length, breadth,true,imageURL);
        this.isAlive = true;
        this.coinsOnKill = coinsOnKill;
    }
    public int getCoinsOnKill(){
        return this.coinsOnKill;
    }
    public void killOrc(){
        //code tbd;
    }
    public void jumpInPlace(Game curGame) throws Exception {
        for (int i = 0; i < Math.abs(getYSpeed()); i++) {
            if (curGame.checkCollisionY(this, getYSpeed())){
                if (this.getClass().getName().equals("com.example.willhero.SmallOrc")){
                    setYSpeed( -(int) (Math.random()*7 + 12));
                }
                else if (this.getClass().getName().equals("com.example.willhero.MediumOrc")){
                    setYSpeed(-(int) (Math.random()*5 + 10));
                }
                else if (this.getClass().getName().equals("com.example.willhero.BossOrc")){
                    setYSpeed(-(int) (Math.random()*3 + 13));
                }
            }
            getUpper().setTranslateY(getUpper().getTranslateY() + ((getYSpeed() > 0) ? 1 : -1));
            getLower().setTranslateY(getLower().getTranslateY() + ((getYSpeed() > 0) ? 1 : -1));
            getRight().setTranslateY(getRight().getTranslateY() + ((getYSpeed() > 0) ? 1 : -1));
            getLeft().setTranslateY(getLeft().getTranslateY() + ((getYSpeed() > 0) ? 1 : -1));
            getNode().setTranslateY(getNode().getTranslateY() + ((getYSpeed() > 0) ? 1 : -1)); //s = ut + 1/2 at^2
        }
    }
    public void setAlive(){
        this.isAlive = false;
    }

    public boolean getAlive(){
        return this.isAlive;
    }
}
