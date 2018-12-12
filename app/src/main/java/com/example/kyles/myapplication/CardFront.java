package com.example.kyles.myapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;

public class CardFront implements GameObject {

    private Bitmap[][] sprites;
    private Bitmap curSprite;
    private Random rand;
    private int number;

    public CardFront(Bitmap[][] sprites){
        this.sprites = sprites;
        curSprite = null;
        rand = new Random();
    }

    @Override
    public void draw(Canvas canvas) {
        if(curSprite != null){
//            canvas.drawBitmap(curSprite);
        }
    }

    @Override
    public void update() {

    }
}
