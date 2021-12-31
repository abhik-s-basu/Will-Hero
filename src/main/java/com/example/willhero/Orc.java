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
        this.display();
        this.isAlive = true;
        this.coinsOnKill = coinsOnKill;
    }
    public int getCoinsOnKill(){
        return this.coinsOnKill;
    }
    public void killOrc(){
        //code tbd;
    }
    public void jumpInPlace(Game curGame){
        for (int i = 0; i < Math.abs(getYSpeed()); i++) {
            if (curGame.checkCollision(this, getYSpeed())){
                if (this.getClass().getName().equals("SmallOrc")){
                    System.out.println("LMOA");
                    setYSpeed(getYSpeed() - 10);
                }
                setYSpeed(getYSpeed() - 10);
//                System.out.println("flsmlfcmsORC");
            }
            getNode().setTranslateY(getNode().getTranslateY() + ((getYSpeed() > 0) ? 1 : -1)); //s = ut + 1/2 at^2
        }
    }

}
