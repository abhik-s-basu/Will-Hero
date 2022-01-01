package com.example.willhero;

public class WeaponChest extends Chest{
    private  Weapon weapon;
    private int coins;
    private  Helmet helmet;
    WeaponChest(Helmet h, int x, int y, String imageURL){
        super(x,y,imageURL);
        this.helmet = h;
        this.coins = 10;
    }
    public Weapon openChest(){
        return this.weapon;
    }
    private void swapWeapon(Weapon w, Hero h){
        //code tbd
    }
    private Weapon equipWeapon(){
        //code tbd
        return weapon;
    }
}
