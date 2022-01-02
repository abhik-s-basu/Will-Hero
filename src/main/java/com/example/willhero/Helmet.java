package com.example.willhero;

public class Helmet {

    private  Weapon weapon1;
    private  Weapon weapon2;
    Helmet(Weapon weapon1, Weapon weapon2){
        this.weapon1 = weapon1; //axe
        this.weapon2 = weapon2; //knife
    }

    public Weapon getWeapon2() {
        return weapon2;
    }

    public Weapon getWeapon1() {
        return weapon1;
    }

    public void setWeapon1(Weapon weapon1) {
        this.weapon1 = weapon1;
    }

    public void setWeapon2(Weapon weapon2) {
        this.weapon2 = weapon2;
    }

}
