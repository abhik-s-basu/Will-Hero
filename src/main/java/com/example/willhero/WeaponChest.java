package com.example.willhero;

public class WeaponChest extends Chest{
    private  Weapon weapon;
    private int coins;
    private Helmet helmet;
    WeaponChest(Helmet h, int x, int y, String imageURL){
        super(x,y,imageURL);
        this.helmet = h;
        this.coins = 10;
        this.initWeapon();
    }

    private void initWeapon() {
        int weaponNumber = (int) (Math.random() * 2);
        if (weaponNumber == 0){
            weapon = new ThrowingAxe();
        }
        else{
            weapon = new ThrowingKnife();
        }
    }

    public Weapon openChest(){
        return this.weapon;
    }
}
