package com.example.kyles.myapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;

public class Background implements GameObject {

    private Bitmap sprite;
    private Boolean gameOver;

    public Background(Bitmap sprite){
        this.sprite = sprite;
        gameOver = false;
    }

    @Override
    public void draw(Canvas canvas) {
        if(!gameOver)
            canvas.drawBitmap(sprite, 0,0, null);
        else
            canvas.drawColor(Color.WHITE);
    }

    @Override
    public void update() {
        gameOver = true;
    }
}
