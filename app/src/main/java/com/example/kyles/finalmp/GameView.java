package com.example.kyles.finalmp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;

public class GameView extends View {

    private Bitmap bmpBg;
    private Bitmap bmpPlayer1Back;
    private Bitmap bmpPlayer2Back;

    private Bitmap bmpPlayer1Bell;
    private Bitmap bmpPlayer2Bell;

    private Bitmap bmpPlayer1Front;
    private Bitmap bmpPlayer2Front;

    private int width;
    private int height;

    public GameView(Context context){
        super(context);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        // Draw background
        bmpBg = BitmapFactory.decodeResource(getResources(),R.mipmap.bg);
        bmpBg = Bitmap.createScaledBitmap(bmpBg,width,height,true);
        canvas.drawBitmap(bmpBg, 0,0, null);

        // Draw line to divide board
        Paint paintLn = new Paint();
        paintLn.setColor(Color.BLACK);
        paintLn.setStrokeWidth(30);
        canvas.drawLine(0,(height/2),width,(height/2),paintLn);

        // Draw player1 deck
        bmpPlayer1Back = BitmapFactory.decodeResource(getResources(),R.mipmap.cb_garden);
        canvas.drawBitmap(bmpPlayer1Back, width/6, 20, null);
        // Draw player1 bell
        bmpPlayer1Bell = BitmapFactory.decodeResource(getResources(),R.mipmap.mato);
        canvas.drawBitmap(bmpPlayer1Bell,width-width/3,60,null);

        // Draw player2 deck
        bmpPlayer2Back = BitmapFactory.decodeResource(getResources(),R.mipmap.cb_garden);
        canvas.drawBitmap(bmpPlayer2Back, width/2+50, height - 360, null);
        // Draw player2 bell
        bmpPlayer2Bell = BitmapFactory.decodeResource(getResources(),R.mipmap.mato);
        canvas.drawBitmap(bmpPlayer2Bell,width/6,height-260,null);
    }
}
