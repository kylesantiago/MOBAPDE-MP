package com.example.kyles.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.MediaPlayer;

public class Bell implements GameObject{

    private Bitmap sprite1,sprite2,curSprite;
    private int xPos;
    private int yPos;
    private int counter;
    private final MediaPlayer bell;

    public Bell(Bitmap sprite1,Bitmap sprite2, int xPos, int yPos, Context context){
        this.sprite1 = sprite1;
        this.sprite2 = sprite2;
        curSprite = sprite1;
        this.xPos = xPos;
        this.yPos = yPos;

        bell  = MediaPlayer.create(context, R.raw.splat);
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

    public void touched(){
        if(curSprite.sameAs(sprite1)){
            curSprite = sprite2;
            counter = 60;
            bell.start();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(curSprite,xPos,yPos,null);
    }

    @Override
    public void update() {
        if(counter > 0) {
            counter--;
        }
        if(counter < 1){
            curSprite = sprite1;
        }
    }
}
