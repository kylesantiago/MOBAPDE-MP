package com.example.kyles.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public Bitmap bmpPlayer1Back;
    public Bitmap bmpPlayer2Back;
    public DBHelper db;
    public UserModel user;

    public Button shopGarden;
    public Button shopHalloween;
    public Button shopChristmas;

    public TextView currentUser;
    public TextView goldText;
    public TextView equippedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bmpPlayer1Back = BitmapFactory.decodeResource(getResources(),R.drawable.cb_garden);
        bmpPlayer2Back = BitmapFactory.decodeResource(getResources(),R.drawable.cb_garden);

        db = new DBHelper(this);

        if(db.numberOfUsers() == 0){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Input your username");

            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(!input.getText().toString().trim().equals("")){ // not blank
                        db.insertUser(input.getText().toString());

                        initializeUser(1);

                        currentUser = findViewById(R.id.textView);
                        currentUser.setText(user.getName());
                    }
                    else{
                        Toast toast = Toast.makeText(getApplicationContext(), "Enter a valid string!", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            });

            builder.show();
        }
        else{
            initializeUser(1);

            currentUser = findViewById(R.id.textView);
            currentUser.setText(user.getName());
        }

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras!=null)
            if(extras.containsKey("GAMEOVER")) {
                initializeUser(intent.getIntExtra("GAMEOVER", 1));
                currentUser = findViewById(R.id.textView);
                currentUser.setText(user.getName());
                endGame();
            }

    }

    public void createFirstUser(){

    }


    public void initializeUser(int id){
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

    public void updateUser(int id){
        Cursor userData = db.getData(id);

        while(userData.moveToNext())
        {
            int gold = userData.getInt(userData.getColumnIndex("gold"));
            int christmas = userData.getInt(userData.getColumnIndex("christmas"));
            int halloween = userData.getInt(userData.getColumnIndex("halloween"));
            String equipped = userData.getString(userData.getColumnIndex("equipped"));

            //set a user
            user.setGold(gold);
            user.setChristmasUnlocked(christmas);
            user.setHalloweenUnlocked(halloween);
            user.setEquipped(equipped);
        }
        userData.close();

    }

    public void shop(View v){
        setContentView(R.layout.shop);

        shopGarden = findViewById(R.id.shopGarden);
        shopHalloween = findViewById(R.id.shopHalloween);
        shopChristmas = findViewById(R.id.shopChristmas);

        goldText = findViewById(R.id.goldText);
        goldText.setText("GOLD: " + Integer.toString(user.getGold()));

        equippedText = findViewById(R.id.equippedTxt);
        equippedText.setText("EQUIPPED: " + user.getEquipped());

        //        setting text in shop
        if(user.getEquipped().equals("GARDEN"))
            shopGarden.setText("EQUIPPED");
        else
            shopGarden.setText("EQUIP");

        if(user.getEquipped().equals("HALLOWEEN"))
            shopHalloween.setText("EQUIPPED");
        else
            if(user.isHalloweenUnlocked() == 1)
                shopHalloween.setText("EQUIP");
            else
                shopHalloween.setText("150 COINS");

        if(user.getEquipped().equals("CHRISTMAS"))
            shopChristmas.setText("EQUIPPED");
        else
            if(user.isChristmasUnlocked() == 1)
                shopChristmas.setText("EQUIP");
            else
                shopChristmas.setText("150 COINS");
    }

    public void backShop(View v){
        setContentView(R.layout.activity_main);
        currentUser = findViewById(R.id.textView);
        currentUser.setText(user.getName());
    }

    public void setCardBackGarden(View v){
        bmpPlayer1Back = BitmapFactory.decodeResource(getResources(),R.drawable.cb_garden);
        bmpPlayer2Back = BitmapFactory.decodeResource(getResources(),R.drawable.cb_garden);
//        Button button = findViewById(R.id.shopGarden);


        int id = user.getId();
        String name = user.getName();
        int gold = user.getGold();
        int halloween = user.isHalloweenUnlocked();
        int christmas = user.isChristmasUnlocked();

        db.equipCardback( id, name, gold, halloween, christmas, "GARDEN" );

        updateUser(id);
        shopGarden.setText("EQUIPPED");
        equippedText.setText("EQUIPPED: " + user.getEquipped());

//        button = findViewById(R.id.shopHalloween);
//        button.setText("Equip");
//        button = findViewById(R.id.shopChristmas);
//        button.setText("Equip");

        if(christmas == 1)
            shopChristmas.setText("EQUIP");
        else if(christmas == 0)
            shopChristmas.setText("150 COINS");

        if(halloween == 1)
            shopHalloween.setText("EQUIP");
        else if(christmas == 0)
            shopHalloween.setText("150 COINS");
    }

    public void setCardBackHalloween(View v){
        bmpPlayer1Back = BitmapFactory.decodeResource(getResources(),R.drawable.cb_halloween);
        bmpPlayer2Back = BitmapFactory.decodeResource(getResources(),R.drawable.cb_halloween);
//        Button button = findViewById(R.id.shopHalloween);


        int id = user.getId();
        String name = user.getName();
        int gold = user.getGold();
        int halloween = user.isHalloweenUnlocked();
        int christmas = user.isChristmasUnlocked();

        //buys or equips, depending on if it is unlocked
        if(halloween == 1) {
            db.equipCardback(id, name, gold, halloween, christmas, "HALLOWEEN");
            shopHalloween.setText("EQUIPPED");

            if(christmas == 1)
                shopChristmas.setText("EQUIP");
            else
                shopChristmas.setText("150 COINS");

            shopGarden.setText("EQUIP");
        }

        else if(halloween == 0 && gold >= 150) {
            db.buyCardback(id, name, gold, halloween, christmas, "HALLOWEEN");
            shopHalloween.setText("EQUIPPED");

            if(christmas == 1)
                shopChristmas.setText("EQUIP");
            else
                shopChristmas.setText("150 COINS");

            shopGarden.setText("EQUIP");
        }

        else if(halloween == 0 && gold < 150){
            Toast toast = Toast.makeText(getApplicationContext(), "Insufficient Gold", Toast.LENGTH_SHORT);
            toast.show();
        }


        updateUser(id);
        goldText.setText("GOLD: " + Integer.toString(user.getGold()));
        equippedText.setText("EQUIPPED: " + user.getEquipped());
//        button = findViewById(R.id.shopChristmas);
//        button.setText("Equip");
//        button = findViewById(R.id.shopGarden);
//        button.setText("Equip");

    }

    public void setCardBackChristmas(View v){
        bmpPlayer1Back = BitmapFactory.decodeResource(getResources(),R.drawable.cb_christmas);
        bmpPlayer2Back = BitmapFactory.decodeResource(getResources(),R.drawable.cb_christmas);
//        Button button = findViewById(R.id.shopChristmas);

        int id = user.getId();
        String name = user.getName();
        int gold = user.getGold();
        int halloween = user.isHalloweenUnlocked();
        int christmas = user.isChristmasUnlocked();

        //buys or equips, depending on if it is unlocked
        if(christmas == 1) {
            db.equipCardback(id, name, gold, halloween, christmas, "CHRISTMAS");
            shopChristmas.setText("Equipped");

            if(halloween == 1)
                shopHalloween.setText("EQUIP");
            else
                shopHalloween.setText("150 COINS");

            shopGarden.setText("EQUIP");
        }
        else if(christmas == 0 && gold >= 150) {
            db.buyCardback(id, name, gold, halloween, christmas, "CHRISTMAS");
            shopChristmas.setText("Equipped");

            if(halloween == 1)
                shopHalloween.setText("EQUIP");
            else
                shopHalloween.setText("150 COINS");

            shopGarden.setText("EQUIP");
        }

        else if(christmas == 0 && gold < 150){
            Toast toast = Toast.makeText(getApplicationContext(), "Insufficient Gold", Toast.LENGTH_SHORT);
            toast.show();
        }

        updateUser(id);
        goldText.setText("GOLD: " + Integer.toString(user.getGold()));
        equippedText.setText("EQUIPPED: " + user.getEquipped());
//        button = findViewById(R.id.shopHalloween);
//        button.setText("Equip");
//        button = findViewById(R.id.shopGarden);
//        button.setText("Equip");


    }

    public void playGame(View v){
        setContentView(new GameView(MainActivity.this, MainActivity.this));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("USER_ID", user.getId());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        initializeUser(savedInstanceState.getInt("USER_ID"));
    }

    public void changeUser(View v){
        Log.d("Vendetta","changeUser");
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        final CharSequence [] options = db.getAllUsers().toArray(new CharSequence[db.numberOfUsers() + 1]);
        options[db.numberOfUsers()] = "Add new user";

        builder.setTitle("Choose User")
                .setItems(options, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        if(which == db.numberOfUsers()) {
                            LayoutInflater li = LayoutInflater.from(MainActivity.this);
                            View promptsView = li.inflate(R.layout.prompt, null);
                            final EditText userInput = (EditText) promptsView
                                    .findViewById(R.id.input);
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                    MainActivity.this);
                            alertDialogBuilder.setView(promptsView);
                            alertDialogBuilder
                                    .setCancelable(false)
                                    .setPositiveButton("OK",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    // get user input and set it to result
                                                    // edit text
                                                    if(!Arrays.asList(options).contains(userInput.getText().toString()))
                                                        db.insertUser(userInput.getText().toString());
                                                    else{
                                                        Toast toast = Toast.makeText(getApplicationContext(), "User Exists", Toast.LENGTH_SHORT);
                                                        toast.show();
                                                    }
                                                }
                                            })
                                    .setNegativeButton("Cancel",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    dialog.cancel();
                                                }
                                            });
                            AlertDialog alertDialog = alertDialogBuilder.create();
                            alertDialog.show();
                        } else {
                            initializeUser(which+1);
                            currentUser = findViewById(R.id.textView);
                            currentUser.setText(user.getName());
                        }
                    }
                });
        builder.show();
    }

    public void endGame(){
        db.giveGold(user.getId(), user.getName(), user.getGold());
        updateUser(user.getId());
        Toast toast = Toast.makeText(getApplicationContext(), "You earned 10 gold!", Toast.LENGTH_LONG);
        toast.show();
    }

    public void help(View v){
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setMessage("2 player game. Play your cards, smash the tomato when there's 5 of the same fruit");
        dlgAlert.setTitle("HELP");
        dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //dismiss the dialog
            }
        });
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();

    }
}
