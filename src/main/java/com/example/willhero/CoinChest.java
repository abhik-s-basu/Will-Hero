package com.example.willhero;

public class CoinChest extends Chest{
    private Coin numCoins;
    CoinChest(Coin coins, int x, int y, String imageURL){
        super(x,y,imageURL);
        this.numCoins = coins;
        // need to figure out the coin animation
    }
    public Coin openChest(){
        // maybe yaha pe imageview change karke ek sikka randomly laa sakte hai
        return this.numCoins;
        // maybe yahi se int banake getCoins karlena chahiye
    }
}
