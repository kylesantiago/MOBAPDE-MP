package com.example.kyles.myapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;

public class CardFront implements GameObject {

    private Bitmap[][] sprites;
    private Bitmap curSprite;
    private Random rand;

    private int number;
    private int xPos;
    private int yPos;

    public CardFront(Bitmap[][] sprites, Integer x, Integer y){
        this.sprites = sprites;
        curSprite = null;
        rand = new Random();

        number = -1;
        xPos = x;
        yPos = y;
    }

    public void touched(){
        number = rand.nextInt(5);
        curSprite = sprites[rand.nextInt(4)][number];
    }

    public int getNumber() {
        return number+1;
    }

    @Override
    public void draw(Canvas canvas) {
        if(curSprite != null){
            canvas.drawBitmap(curSprite,xPos,yPos,null);
        }
    }

    @Override
    public void update() {

    }
}
