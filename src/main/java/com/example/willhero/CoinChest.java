package com.example.willhero;

public class CoinChest extends Chest{
    private int numCoins;
    CoinChest(int coins, int x, int y, String imageURL){
        super(x,y,imageURL);
        this.numCoins = coins;
    }
    public int openChest(){
        return this.numCoins;
    }
}
