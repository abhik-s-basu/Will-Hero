package com.example.willhero;

public class WeaponChest extends Chest{
    private  Weapon weapon;
    private  Helmet helmet;
    WeaponChest(Helmet h, int x, int y, String imageURL){
        super(x,y,imageURL);
        this.helmet = h;
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
