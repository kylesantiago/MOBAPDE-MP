package com.example.kyles.myapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Background implements GameObject {

    private Bitmap sprite;

    public Background(Bitmap sprite){
        this.sprite = sprite;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(sprite, 0,0, null);
    }

    @Override
    public void update() {
    }
}
