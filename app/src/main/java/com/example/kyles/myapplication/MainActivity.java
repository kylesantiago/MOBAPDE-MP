package com.example.kyles.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public Bitmap bmpPlayer1Back;
    public Bitmap bmpPlayer2Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bmpPlayer1Back = BitmapFactory.decodeResource(getResources(),R.drawable.cb_garden);
        bmpPlayer2Back = BitmapFactory.decodeResource(getResources(),R.drawable.cb_garden);
    }

    public void shop(View v){
        setContentView(R.layout.shop);

    }

    public void backShop(View v){
        setContentView(R.layout.activity_main);
    }

    public void setCardBackGarden(View v){
        bmpPlayer1Back = BitmapFactory.decodeResource(getResources(),R.drawable.cb_garden);
        bmpPlayer2Back = BitmapFactory.decodeResource(getResources(),R.drawable.cb_garden);
        Button button = findViewById(R.id.shopGarden);
        button.setText("Equipped");
    }

    public void setCardBackHalloween(View v){
        bmpPlayer1Back = BitmapFactory.decodeResource(getResources(),R.drawable.cb_halloween);
        bmpPlayer2Back = BitmapFactory.decodeResource(getResources(),R.drawable.cb_halloween);
        Button button = findViewById(R.id.shopHalloween);
        button.setText("Equipped");
    }

    public void setCardBackChristmas(View v){
        bmpPlayer1Back = BitmapFactory.decodeResource(getResources(),R.drawable.cb_christmas);
        bmpPlayer2Back = BitmapFactory.decodeResource(getResources(),R.drawable.cb_christmas);
        Button button = findViewById(R.id.shopChristmas);
        button.setText("Equipped");
    }

    public void playGame(View v){
        setContentView(new GameView(MainActivity.this, MainActivity.this));
    }
}
