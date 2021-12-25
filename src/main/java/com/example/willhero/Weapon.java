package com.example.willhero;

public abstract class Weapon  { // need to figure out how this extends game object
    private  String type;
    private  int damage;
    private  int quantity;
    Weapon (String type, int damage, int quantity){
        this.type = type;
        this.damage = damage;
        this.quantity = quantity;
    }
    public void upgradeWeapon(int count,int damage){
        this.damage += damage;
        this.quantity = count;
    }
    public int getDamage(){
        return this.damage;
    }
    public int getQuantity(){
        return this.quantity;
    }
    public void useWeapon(){
        //code tbd
    }
}
