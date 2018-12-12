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


        private CardBack p1Back;
        private CardBack p2Back;

        private Bell p1Bell;
        private Bell p2Bell;

        private Background bg;

        private CardFront p1Front;
        private CardFront p2Front;

        private int width;
        private int height;

        private int bell_height;
        private int bell_width;

        private int card_height;
        private int card_width;

        private int turn;

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

            turn = 0;

            Bitmap temp[][] = new Bitmap[][]{
                    {Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.broc_1),card_width,card_height,true),
                            Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.broc_2),card_width,card_height,true),
                            Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.broc_3),card_width,card_height,true),
                            Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.broc_4),card_width,card_height,true),
                            Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.broc_5),card_width,card_height,true)},
                    {Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.carrot_1),card_width,card_height,true),
                            Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.carrot_2),card_width,card_height,true),
                            Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.carrot_3),card_width,card_height,true),
                            Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.carrot_4),card_width,card_height,true),
                            Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.carrot_5),card_width,card_height,true)},
                    {Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.egg_1),card_width,card_height,true),
                            Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.egg_2),card_width,card_height,true),
                            Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.egg_3),card_width,card_height,true),
                            Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.egg_4),card_width,card_height,true),
                            Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.egg_5),card_width,card_height,true)},
                    {Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.tomato_1),card_width,card_height,true),
                            Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.tomato_2),card_width,card_height,true),
                            Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.tomato_3),card_width,card_height,true),
                            Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.tomato_4),card_width,card_height,true),
                            Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.tomato_5),card_width,card_height,true)}};

            p1Front = new CardFront(temp, (width/2) - 100, (height/2) - 350);
            p2Front = new CardFront(temp, (width/2) - 100, (height/2) + 30);

            p1Bell = new Bell(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.mato),bell_width,bell_height,true),Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.splat),bell_width,bell_height,true),width-width/3,60);
            p2Bell = new Bell(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.mato),bell_width,bell_height,true),Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.splat),bell_width,bell_height,true),width/6,height-260);

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
            p1Bell.update();
            p2Bell.update();
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
                        System.out.println("p1 bell");
                        p1Bell.touched();
                    } else if ( x > p2Bell.getxPos() && x < p2Bell.getxPos() + bell_width && y > p2Bell.getyPos() && y < p2Bell.getyPos() + bell_height ){
                        //p2bell touched
                        System.out.println("p2 bell");
                        p2Bell.touched();
                    }  else if ( x > p1Back.getxPos() && x < p1Back.getxPos() + bell_width && y > p1Back.getyPos() && y < p1Back.getyPos() + bell_height ){
                        //p1Back touched
                        System.out.println("Deck 1 clicked");
                        if(p1Back.getCount() > 0 && turn%2 == 0){
                            p1Front.touched();
                            p1Back.touched();
                            System.out.println(p1Front.getNumber());
                            turn++;
                        }
                        else{
                            System.out.println("DECK 1 EMPTY");
                        }
                    } else if ( x > p2Back.getxPos() && x < p2Back.getxPos() + card_width && y > p2Back.getyPos() && y < p2Back.getyPos() + card_height ) {
                        //p2Back touched
                        System.out.println("Deck 2 clicked");
                        if(p2Back.getCount() > 0 && turn%2 == 1){
                            p2Front.touched();
                            p2Back.touched();
                            System.out.println(p2Front.getNumber());
                            turn++;
                        }
                        else{
                            System.out.println("DECK 2 EMPTY");
                        }
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
            p1Front.draw(canvas);
            // Draw player1 bell
            p1Bell.draw(canvas);
            // Draw player2 deck
            p2Back.draw(canvas);
            p2Front.draw(canvas);
            // Draw player2 bell
            p2Bell.draw(canvas);
        }
    }

