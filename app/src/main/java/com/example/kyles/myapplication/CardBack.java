package com.example.kyles.myapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class CardBack implements GameObject{

    private Bitmap sprite;

    private int xPos;
    private int yPos;

    private int count;

    public CardBack(Bitmap sprite, int xPos, int yPos){
        this.sprite = sprite;
        this.xPos = xPos;
        this.yPos = yPos;

        count = 2;
    }

    public void correct(int num){
        count += num;
    }

    public void touched(){
        count--;
    }

    public int getCount() {
        return count;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(sprite,xPos,yPos,null);
    }

    @Override
    public void update() {

    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }
}
