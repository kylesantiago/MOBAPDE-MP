package com.example.kyles.myapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Bell implements GameObject{

    private Bitmap sprite1,sprite2,curSprite;
    private int xPos;
    private int yPos;
    private int counter;

    public Bell(Bitmap sprite1,Bitmap sprite2, int xPos, int yPos){
        this.sprite1 = sprite1;
        this.sprite2 = sprite2;
        curSprite = sprite1;
        this.xPos = xPos;
        this.yPos = yPos;

        counter = 5;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public Bitmap getCurSprite() {
        return curSprite;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(curSprite,xPos,yPos,null);
    }

    @Override
    public void update() {

    }
}
