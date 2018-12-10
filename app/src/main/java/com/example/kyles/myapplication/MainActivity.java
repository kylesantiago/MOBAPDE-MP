package com.example.kyles.myapplication;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public Bitmap bmpPlayer1Back;
    public Bitmap bmpPlayer2Back;
    public DBHelper db;
    public UserModel user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(this);
        setUser(0);

        bmpPlayer1Back = BitmapFactory.decodeResource(getResources(),R.drawable.cb_garden);
        bmpPlayer2Back = BitmapFactory.decodeResource(getResources(),R.drawable.cb_garden);
    }

    public void setUser(int id){
        Cursor userData = db.getData(id);

        while(userData.moveToNext())
        {
            String name = userData.getString(userData.getColumnIndex("name"));
            int gold = userData.getInt(userData.getColumnIndex("gold"));
            int christmas = userData.getInt(userData.getColumnIndex("christmas"));
            int christmasUnlocked = userData.getInt(userData.getColumnIndex("christmas_unlocked"));
            int halloween = userData.getInt(userData.getColumnIndex("halloween"));
            int halloweenUnlocked = userData.getInt(userData.getColumnIndex("halloween_unlocked"));

            //set a user
            user = new UserModel(id, name, gold, christmas, christmasUnlocked, halloween, halloweenUnlocked);
        }
        userData.close();
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
//        db.equipCardback(/* id, name, gold, GARDEN */);

        button = findViewById(R.id.shopHalloween);
        button.setText("Equip");
        button = findViewById(R.id.shopChristmas);
        button.setText("Equip");
    }

    public void setCardBackHalloween(View v){
        bmpPlayer1Back = BitmapFactory.decodeResource(getResources(),R.drawable.cb_halloween);
        bmpPlayer2Back = BitmapFactory.decodeResource(getResources(),R.drawable.cb_halloween);
        Button button = findViewById(R.id.shopHalloween);
        button.setText("Equipped");
//        db.equipCardback(/* id, name, gold, HALLOWEEN */);

        button = findViewById(R.id.shopChristmas);
        button.setText("Equip");
        button = findViewById(R.id.shopGarden);
        button.setText("Equip");
    }

    public void setCardBackChristmas(View v){
        bmpPlayer1Back = BitmapFactory.decodeResource(getResources(),R.drawable.cb_christmas);
        bmpPlayer2Back = BitmapFactory.decodeResource(getResources(),R.drawable.cb_christmas);
        Button button = findViewById(R.id.shopChristmas);
        button.setText("Equipped");
//        db.equipCardback(/* id, name, gold, CHRISTMAS */);
        button = findViewById(R.id.shopHalloween);
        button.setText("Equip");
        button = findViewById(R.id.shopGarden);
        button.setText("Equip");
    }

    public void playGame(View v){
        setContentView(new GameView(MainActivity.this, MainActivity.this));
    }
}
