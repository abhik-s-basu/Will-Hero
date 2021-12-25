package com.example.willhero;

public class Island extends GameObject{
    private boolean isFloating;
    Island (double x, double y, double length,
            double breadth,boolean isFloating,String imageURL){
        super(x,y,0,0,length,breadth,false,imageURL);
        this.isFloating = isFloating;
        this.moveFloat();
        this.display();
    }
    public void moveFloat(){
    // yaha pe manipulate karlenge image view pr transition daalke if need be
    }
}
