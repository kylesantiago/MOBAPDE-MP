package com.example.kyles.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    public GamePanel(Context context){
        super(context);
        getHolder().addCallback(this);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    @Override
    public boolean onTouchEvent (MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public void draw (Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.WHITE);

    }
}
