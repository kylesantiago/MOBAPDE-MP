package com.example.kyles.myapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class CardBack implements GameObject{

    private Bitmap sprite;

    private int xPos;
    private int yPos;

    public CardBack(Bitmap sprite, int xPos, int yPos){
        this.sprite = sprite;
        this.xPos = xPos;
        this.yPos = yPos;
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
