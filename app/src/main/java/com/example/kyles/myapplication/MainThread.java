package com.example.kyles.myapplication;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread{
    public static final int MAX_FPS = 30;
    private double averageFPS;
    private SurfaceHolder holder;
    private GameView gameView;
    private boolean running;
    public static Canvas canvas;

    public MainThread(SurfaceHolder holder, GameView gameView){
        super();
        this.holder = holder;
        this.gameView = gameView;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run(){
        long startTime;
        long timeMillis = 1000/MAX_FPS;
        long waitTime;
        int frameCount = 0;
        long totalTime = 0;
        long targetTime = 1000/MAX_FPS;

        while(running){
            startTime = System.nanoTime();
            canvas = null;

            try{
                canvas = this.holder.lockCanvas();
                synchronized (holder){
                    this.gameView.update();
                    this.gameView.draw(canvas);
                }
            }catch (Exception e ){
                e.printStackTrace();
            }finally {
                if(canvas != null){
                    try{
                        holder.unlockCanvasAndPost(canvas);
                    }catch (Exception e){e.printStackTrace();}
                }
            }
            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - timeMillis;

            // cap the frame rate
            try {
                if (waitTime > 0) {
                    this.sleep(waitTime);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if (frameCount == MAX_FPS) {
                averageFPS = 1000 / ((totalTime / frameCount) / 1000000);
                frameCount = 0;
                totalTime = 0;
                System.out.println(averageFPS);
            }
        }
    }
}
