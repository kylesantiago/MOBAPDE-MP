package com.example.kyles.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

    public class GameView extends SurfaceView implements SurfaceHolder.Callback {

        private MainActivity owner;
        private MainThread thread;

        private Bitmap bmpBg;
//        private Bitmap bmpPlayer1Back;
//        private Bitmap bmpPlayer2Back;

        private Bitmap bmpPlayer1Bell;
        private Bitmap bmpPlayer2Bell;

        private Bitmap bmpPlayer1Front;
        private Bitmap bmpPlayer2Front;

        private int width;
        private int height;

        public GameView(Context context, MainActivity owner){
            super(context);
            getHolder().addCallback(this);

            thread = new MainThread(getHolder(),this);

            this.owner = owner;
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            height = displayMetrics.heightPixels;
            width = displayMetrics.widthPixels;

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
            return super.onTouchEvent(event);
        }

        @Override
        public void draw(Canvas canvas){
            super.draw(canvas);

            // Draw background
            bmpBg = BitmapFactory.decodeResource(getResources(),R.drawable.bg);
            bmpBg = Bitmap.createScaledBitmap(bmpBg,width,height,true);
            canvas.drawBitmap(bmpBg, 0,0, null);

            // Draw line to divide board
            Paint paintLn = new Paint();
            paintLn.setColor(Color.BLACK);
            paintLn.setStrokeWidth(30);
            canvas.drawLine(0,(height/2),width,(height/2),paintLn);

            // Draw player1 deck
            //owner.bmpPlayer1Back = BitmapFactory.decodeResource(getResources(),R.drawable.cb_garden);
            owner.bmpPlayer1Back = Bitmap.createScaledBitmap(owner.bmpPlayer1Back,width/4,height/5,true);
            canvas.drawBitmap(owner.bmpPlayer1Back, width/6, 20, null);
            // Draw player1 bell
            bmpPlayer1Bell = BitmapFactory.decodeResource(getResources(),R.drawable.mato);
            bmpPlayer1Bell = Bitmap.createScaledBitmap(bmpPlayer1Bell,width/4,height/6,true);
            canvas.drawBitmap(bmpPlayer1Bell,width-width/3,60,null);

            // Draw player2 deck
            //owner.bmpPlayer2Back = BitmapFactory.decodeResource(getResources(),R.drawable.cb_garden);
            owner.bmpPlayer2Back = Bitmap.createScaledBitmap(owner.bmpPlayer2Back,width/4,height/5,true);
            canvas.drawBitmap(owner.bmpPlayer2Back, width/2+50, height - 360, null);
            // Draw player2 bell
            bmpPlayer2Bell = BitmapFactory.decodeResource(getResources(),R.drawable.mato);
            bmpPlayer2Bell = Bitmap.createScaledBitmap(bmpPlayer2Bell,width/4,height/6,true);
            canvas.drawBitmap(bmpPlayer2Bell,width/6,height-260,null);
        }
    }

