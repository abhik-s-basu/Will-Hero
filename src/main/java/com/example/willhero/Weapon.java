package com.example.willhero;

import java.io.Serializable;

public abstract class Weapon implements Serializable { // need to figure out how this extends game object
    private final static long serialVersionUID = 3298;
    private  String type;
    private  int damage;
    private  int quantity;
    public Weapon (String type, int damage, int quantity){
        this.type = type;
        this.damage = damage;
        this.quantity = quantity;
    }
    public void upgradeWeapon(int count,int damage){
        this.damage += damage;
        this.quantity = count;
    }
    public int getDamage(){
        return this.damage*this.quantity;
    }
    public int getQuantity(){
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity += quantity;
    }
    public void useWeapon(){
        //code tbd
    }
}
