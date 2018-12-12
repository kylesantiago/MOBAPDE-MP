package com.example.kyles.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
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

        private final MediaPlayer card;

        private int turn;

        private boolean gameOver;

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

            turn = 1;
            gameOver = false;

            card  = MediaPlayer.create(getContext(), R.raw.playcard);

            Matrix matrix = new Matrix();
            matrix.postRotate(180);

            Bitmap temp2[][] = new Bitmap[][]{
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
                    {Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.chili_1),card_width,card_height,true),
                            Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.chili_2),card_width,card_height,true),
                            Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.chili_3),card_width,card_height,true),
                            Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.chili_4),card_width,card_height,true),
                            Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.chili_5),card_width,card_height,true)}};

            Bitmap temp1[][] = new Bitmap[][]{
                    {Bitmap.createBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.broc_1),card_width,card_height,true), 0, 0, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.broc_1),card_width,card_height,true).getWidth(), Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.broc_1),card_width,card_height,true).getHeight(), matrix, true),
                            Bitmap.createBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.broc_2),card_width,card_height,true), 0, 0, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.broc_2),card_width,card_height,true).getWidth(), Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.broc_2),card_width,card_height,true).getHeight(), matrix, true),
                            Bitmap.createBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.broc_3),card_width,card_height,true), 0, 0, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.broc_3),card_width,card_height,true).getWidth(), Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.broc_3),card_width,card_height,true).getHeight(), matrix, true),
                            Bitmap.createBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.broc_4),card_width,card_height,true), 0, 0, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.broc_4),card_width,card_height,true).getWidth(), Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.broc_4),card_width,card_height,true).getHeight(), matrix, true),
                            Bitmap.createBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.broc_5),card_width,card_height,true), 0, 0, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.broc_5),card_width,card_height,true).getWidth(), Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.broc_5),card_width,card_height,true).getHeight(), matrix, true)},
                    {Bitmap.createBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.carrot_1),card_width,card_height,true), 0, 0, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.carrot_1),card_width,card_height,true).getWidth(), Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.carrot_1),card_width,card_height,true).getHeight(), matrix, true),
                            Bitmap.createBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.carrot_2),card_width,card_height,true), 0, 0, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.carrot_2),card_width,card_height,true).getWidth(), Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.carrot_2),card_width,card_height,true).getHeight(), matrix, true),
                            Bitmap.createBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.carrot_3),card_width,card_height,true), 0, 0, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.carrot_3),card_width,card_height,true).getWidth(), Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.carrot_3),card_width,card_height,true).getHeight(), matrix, true),
                            Bitmap.createBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.carrot_4),card_width,card_height,true), 0, 0, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.carrot_4),card_width,card_height,true).getWidth(), Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.carrot_4),card_width,card_height,true).getHeight(), matrix, true),
                            Bitmap.createBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.carrot_5),card_width,card_height,true), 0, 0, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.carrot_5),card_width,card_height,true).getWidth(), Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.carrot_5),card_width,card_height,true).getHeight(), matrix, true)},
                    {Bitmap.createBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.egg_1),card_width,card_height,true), 0, 0, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.egg_1),card_width,card_height,true).getWidth(), Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.egg_1),card_width,card_height,true).getHeight(), matrix, true),
                            Bitmap.createBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.egg_2),card_width,card_height,true), 0, 0, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.egg_2),card_width,card_height,true).getWidth(), Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.egg_2),card_width,card_height,true).getHeight(), matrix, true),
                            Bitmap.createBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.egg_3),card_width,card_height,true), 0, 0, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.egg_3),card_width,card_height,true).getWidth(), Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.egg_3),card_width,card_height,true).getHeight(), matrix, true),
                            Bitmap.createBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.egg_4),card_width,card_height,true), 0, 0, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.egg_4),card_width,card_height,true).getWidth(), Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.egg_4),card_width,card_height,true).getHeight(), matrix, true),
                            Bitmap.createBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.egg_5),card_width,card_height,true), 0, 0, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.egg_5),card_width,card_height,true).getWidth(), Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.egg_5),card_width,card_height,true).getHeight(), matrix, true)},
                    {Bitmap.createBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.chili_1),card_width,card_height,true), 0, 0, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.chili_1),card_width,card_height,true).getWidth(), Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.chili_1),card_width,card_height,true).getHeight(), matrix, true),
                            Bitmap.createBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.chili_2),card_width,card_height,true), 0, 0, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.chili_2),card_width,card_height,true).getWidth(), Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.chili_2),card_width,card_height,true).getHeight(), matrix, true),
                            Bitmap.createBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.chili_3),card_width,card_height,true), 0, 0, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.chili_3),card_width,card_height,true).getWidth(), Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.chili_3),card_width,card_height,true).getHeight(), matrix, true),
                            Bitmap.createBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.chili_4),card_width,card_height,true), 0, 0, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.chili_4),card_width,card_height,true).getWidth(), Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.chili_4),card_width,card_height,true).getHeight(), matrix, true),
                            Bitmap.createBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.chili_5),card_width,card_height,true), 0, 0, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.chili_5),card_width,card_height,true).getWidth(), Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.chili_5),card_width,card_height,true).getHeight(), matrix, true)}};


            //rotated bitmaps
            Bitmap bellBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.mato),bell_width,bell_height,true);
            Bitmap bellBitmapRotated = Bitmap.createBitmap(bellBitmap, 0, 0, bellBitmap.getWidth(), bellBitmap.getHeight(), matrix, true);

            Bitmap backBitmap = Bitmap.createScaledBitmap(owner.bmpPlayer1Back,card_width,card_height,true);
            Bitmap backBitmapRotated = Bitmap.createBitmap(backBitmap, 0, 0, backBitmap.getWidth(), backBitmap.getHeight(), matrix, true);

            p1Front = new CardFront(temp1, (width/2) - 100, (height/2) - 350);
            p2Front = new CardFront(temp2, (width/2) - 100, (height/2) + 30);

            p1Bell = new Bell(bellBitmapRotated,Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.splat),bell_width,bell_height,true),width-width/3,60,getContext());
            p2Bell = new Bell(bellBitmap,Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.splat),bell_width,bell_height,true),width/6,height-260,getContext());
            bg = new Background(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.bg),width,height,true));

            p1Back = new CardBack(backBitmapRotated, width/6, 20);
            p2Back = new CardBack(backBitmap, width/2+50, height - 360);
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
            checkWinner();
        }

        public void checkWinner(){
            if(p2Back.getCount() < 1 || p1Back.getCount() < 1) {
                System.out.println("called");
                gameOver = true;
            }
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
                        if((p1Front.getFruit() == p2Front.getFruit() && p1Front.getNumber() + p2Front.getNumber() == 5) || ((p1Front.getNumber() == 5 || p2Front.getNumber() == 5) && p1Front.getFruit() != p2Front.getFruit())){
                            System.out.println("Correct");
                            p1Back.correct(p1Front.getCount());
                            p1Back.correct(p2Front.getCount());
                            p1Front.reset();
                            p2Front.reset();
                            System.out.println(p1Back.getCount());
                        }
                        else{
                            System.out.println("wrong");
                        }
                    } else if ( x > p2Bell.getxPos() && x < p2Bell.getxPos() + bell_width && y > p2Bell.getyPos() && y < p2Bell.getyPos() + bell_height ){
                        //p2bell touched
                        System.out.println("p2 bell");
                        p2Bell.touched();
                        if((p1Front.getFruit() == p2Front.getFruit() && p1Front.getNumber() + p2Front.getNumber() == 5) || ((p1Front.getNumber() == 5 || p2Front.getNumber() == 5) && p1Front.getFruit() != p2Front.getFruit())){
                            System.out.println("Correct");
                            p2Back.correct(p1Front.getCount());
                            p2Back.correct(p2Front.getCount());
                            p1Front.reset();
                            p2Front.reset();
                            System.out.println(p2Back.getCount());
                        }
                        else{
                            System.out.println("wrong");
                        }
                    }  else if ( x > p1Back.getxPos() && x < p1Back.getxPos() + bell_width && y > p1Back.getyPos() && y < p1Back.getyPos() + bell_height ){
                        //p1Back touched
                        System.out.println("Deck 1 clicked");
                        card.start();
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
                        card.start();
                        if(p2Back.getCount() > 0 && turn%2 == 1){
                            p2Front.touched();
                            p2Back.touched();
                            System.out.println(p2Front.getNumber());
                            turn++;
                        }
                        else{
                            System.out.println("DECK 2 EMPTY");
                        }
                    }else if(gameOver){
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("GAMEOVER", 1);
                        getContext().startActivity(intent);
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
            if(!gameOver) {
                // Draw line to divide board
                Paint paintLn = new Paint();
                paintLn.setColor(Color.BLACK);
                paintLn.setStrokeWidth(30);
                canvas.drawLine(0, (height / 2), width, (height / 2), paintLn);

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
            else{
                System.out.println("gameover");
                Bitmap temp;
                if(p1Back.getCount() < 1){
                    temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.p1win),width/3,height/4,true);
                }
                else{
                    temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.p2win),width/3,height/4,true);
                }
                canvas.drawBitmap(temp,width/2-temp.getWidth()/2,height/2-temp.getHeight()/2,null);
            }
        }
    }

