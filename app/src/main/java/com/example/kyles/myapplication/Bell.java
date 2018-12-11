package com.example.kyles.myapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Bell implements GameObject{

    private Bitmap sprite;
    private int xPos;
    private int yPos;

    public Bell(Bitmap sprite, int xPos, int yPos){
        setSprite(sprite);
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public Bitmap getSprite() {
        return sprite;
    }

    public void setSprite(Bitmap sprite) {
        this.sprite = sprite;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(sprite,xPos,yPos,null);
    }

    @Override
    public void update() {

    }
}
