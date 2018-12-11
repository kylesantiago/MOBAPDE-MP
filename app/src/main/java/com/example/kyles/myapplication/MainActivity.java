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
        updateUser(0);

        bmpPlayer1Back = BitmapFactory.decodeResource(getResources(),R.drawable.cb_garden);
        bmpPlayer2Back = BitmapFactory.decodeResource(getResources(),R.drawable.cb_garden);
    }

    public void updateUser(int id){
        Cursor userData = db.getData(id);

        while(userData.moveToNext())
        {
            String name = userData.getString(userData.getColumnIndex("name"));
            int gold = userData.getInt(userData.getColumnIndex("gold"));
            int christmas = userData.getInt(userData.getColumnIndex("christmas"));
            int halloween = userData.getInt(userData.getColumnIndex("halloween"));
            String equipped = userData.getString(userData.getColumnIndex("equipped"));

            //set a user
            user = new UserModel(id, name, gold, christmas, halloween, equipped);
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

        int id = user.getId();
        String name = user.getName();
        int gold = user.getGold();
        int halloween = user.isHalloweenUnlocked();
        int christmas = user.isChristmasUnlocked();

        db.equipCardback( id, name, gold, halloween, christmas, "GARDEN" );
        updateUser(id);

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

        int id = user.getId();
        String name = user.getName();
        int gold = user.getGold();
        int halloween = user.isHalloweenUnlocked();
        int christmas = user.isChristmasUnlocked();

        if(halloween == 1)
            db.equipCardback(id, name, gold, halloween, christmas, "HALLOWEEN");
        else
            db.buyCardback(id, name, gold, halloween, christmas, "HALLOWEEN");

        updateUser(id);

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

        int id = user.getId();
        String name = user.getName();
        int gold = user.getGold();
        int halloween = user.isHalloweenUnlocked();
        int christmas = user.isChristmasUnlocked();

        if(christmas == 1)
            db.equipCardback(id, name, gold, halloween, christmas, "CHRISTMAS");
        else
            db.buyCardback(id, name, gold, halloween, christmas, "CHRISTMAS");

        updateUser(id);

        button = findViewById(R.id.shopHalloween);
        button.setText("Equip");
        button = findViewById(R.id.shopGarden);
        button.setText("Equip");
    }

    public void playGame(View v){
        setContentView(new GameView(MainActivity.this, MainActivity.this));
    }
}
