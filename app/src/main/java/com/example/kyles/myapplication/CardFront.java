package com.example.kyles.myapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;

public class CardFront implements GameObject {

    private Bitmap[][] sprites;
    private Bitmap curSprite;
    private Random rand;

    private int count;

    private int number;
    private int xPos;
    private int yPos;

    public CardFront(Bitmap[][] sprites, int x, int y){
        this.sprites = sprites;
        curSprite = null;
        rand = new Random();

        number = -1;
        xPos = x;
        yPos = y;

        count = 0;
    }

    public int getCount() {
        return count;
    }

    public void touched(){
        number = rand.nextInt(5);
        curSprite = sprites[rand.nextInt(4)][number];
        count++;
    }

    public void reset(){
        curSprite = null;
        count = 0;
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
