package com.example.kyles.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

    public class GameView extends SurfaceView implements SurfaceHolder.Callback {

        private MainThread thread;

        private Bitmap bmpBg;
//        private Bitmap bmpPlayer1Back;
//        private Bitmap bmpPlayer2Back;

        private CardBack p1Back;
        private CardBack p2Back;

        private Bell p1Bell;
        private Bell p2Bell;

        private Background bg;

        private Bitmap bmpPlayer1Front;
        private Bitmap bmpPlayer2Front;

        private int width;
        private int height;

        private int bell_height;
        private int bell_width;

        private int card_height;
        private int card_width;

        public GameView(Context context, MainActivity owner){
            super(context);
            getHolder().addCallback(this);

            thread = new MainThread(getHolder(),this);

            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            height = displayMetrics.heightPixels;
            width = displayMetrics.widthPixels;

            bell_height = height/6;
            bell_width = width/4;

            card_height = height/5;
            card_width = width/4;

            p1Bell = new Bell(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.mato),bell_width,bell_height,true),width-width/3,60);
            p2Bell = new Bell(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.mato),bell_width,bell_height,true),width/6,height-260);

            bg = new Background(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.bg),width,height,true));

            p1Back = new CardBack(Bitmap.createScaledBitmap(owner.bmpPlayer1Back,card_width,card_height,true), width/6, 20);
            p2Back = new CardBack(Bitmap.createScaledBitmap(owner.bmpPlayer2Back,card_width,card_height,true), width/2+50, height - 360);
            setFocusable(true);
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            thread = new MainThread(getHolder(),this);

            thread.setRunning(true);
            thread.start();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            boolean retry = true;
            while (retry) {
                try {
                    thread.setRunning(false);
                    thread.join();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                retry = false;
            }
        }

        public void update(){

        }

        @Override
        public boolean onTouchEvent (MotionEvent event) {
            float x = event.getX();
            float y = event.getY();
            switch(event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    //Check if the x and y position of the touch is inside the bitmap
                    if( x > p1Bell.getxPos() && x < p1Bell.getxPos() + bell_width && y > p1Bell.getyPos() && y < p1Bell.getyPos() + bell_height )
                    {
                        //p1bell touched
                        Log.d("Click", "Bell 1 Clicked");
                    } else if ( x > p2Bell.getxPos() && x < p2Bell.getxPos() + bell_width && y > p2Bell.getyPos() && y < p2Bell.getyPos() + bell_height ){
                        //p2bell touched
                        Log.d("Click", "Bell 2 Clicked");
                    }
                    return true;
            }
            return false;
        }

        @Override
        public void draw(Canvas canvas){
            super.draw(canvas);

            // Draw background
            bg.draw(canvas);

            // Draw line to divide board
            Paint paintLn = new Paint();
            paintLn.setColor(Color.BLACK);
            paintLn.setStrokeWidth(30);
            canvas.drawLine(0,(height/2),width,(height/2),paintLn);

            // Draw player1 deck
            p1Back.draw(canvas);
            // Draw player1 bell
            p1Bell.draw(canvas);
            // Draw player2 deck
            p2Back.draw(canvas);
            // Draw player2 bell
            p2Bell.draw(canvas);
        }
    }

