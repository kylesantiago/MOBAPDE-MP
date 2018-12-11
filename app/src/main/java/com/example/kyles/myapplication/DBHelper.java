package com.example.kyles.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "SmashingVeggies.db";

    public static final String USER_TABLE_NAME = "users";
    public static final String USERS_COLUMN_ID = "id";
    public static final String USERS_COLUMN_NAME = "name";
    public static final String USERS_COLUMN_GOLD = "gold";
    public static final String USERS_COLUMN_CHRISTMAS = "christmas";
    public static final String USERS_COLUMN_HALLOWEEN = "halloween";
    public static final String USERS_COLUMN_EQUIPPED = "equipped";


    private HashMap hp;


    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table " + USER_TABLE_NAME +
                        "(" + USERS_COLUMN_ID +" integer primary key autoincrement,"+ USERS_COLUMN_NAME +" text," + USERS_COLUMN_GOLD + " integer," + USERS_COLUMN_HALLOWEEN + " integer," + USERS_COLUMN_CHRISTMAS + " integer," + USERS_COLUMN_EQUIPPED + " text)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertUser (String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERS_COLUMN_NAME, name);
        contentValues.put(USERS_COLUMN_GOLD, 150);
        contentValues.put(USERS_COLUMN_CHRISTMAS, 0);
        contentValues.put(USERS_COLUMN_HALLOWEEN, 0);
        contentValues.put(USERS_COLUMN_EQUIPPED, "GARDEN");
        db.insert(USER_TABLE_NAME, null, contentValues);

        return true;
    }

    public boolean equipCardback(Integer id, String name, Integer gold, Integer halloween, Integer christmas, String cardbackType){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(USERS_COLUMN_NAME, name);
        contentValues.put(USERS_COLUMN_GOLD, gold);
        contentValues.put(USERS_COLUMN_HALLOWEEN, halloween);
        contentValues.put(USERS_COLUMN_CHRISTMAS, christmas);

        if(cardbackType.equals("CHRISTMAS")){
            contentValues.put(USERS_COLUMN_EQUIPPED, "CHRISTMAS");
        }
        else if(cardbackType.equals("HALLOWEEN")){
            contentValues.put(USERS_COLUMN_EQUIPPED, "HALLOWEEN");
        }
        else{
            contentValues.put(USERS_COLUMN_EQUIPPED, "GARDEN");
        }


        db.update(USER_TABLE_NAME, contentValues, "id= ?", new String[]{Integer.toString(id)});

        return true;
    }

    public boolean buyCardback(Integer id, String name, Integer gold, Integer halloween, Integer christmas, String cardbackType){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(USERS_COLUMN_NAME, name);
        contentValues.put(USERS_COLUMN_GOLD, gold - 150);

        if(cardbackType.equals("CHRISTMAS")){
            contentValues.put(USERS_COLUMN_CHRISTMAS, 1);
            contentValues.put(USERS_COLUMN_EQUIPPED, "CHRISTMAS");

            contentValues.put(USERS_COLUMN_HALLOWEEN, halloween);
        }
        else if(cardbackType.equals("HALLOWEEN")){
            contentValues.put(USERS_COLUMN_HALLOWEEN, 1);
            contentValues.put(USERS_COLUMN_EQUIPPED, "HALLOWEEN");

            contentValues.put(USERS_COLUMN_CHRISTMAS, christmas);
        }


        db.update(USER_TABLE_NAME, contentValues, "id= ?", new String[] {Integer.toString(id)});

        return true;
    }

    public boolean giveGold(Integer id, String name, Integer gold){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(USERS_COLUMN_NAME, name);
        contentValues.put(USERS_COLUMN_GOLD, gold + 10);

        db.update(USER_TABLE_NAME, contentValues, "id= ?", new String[]{Integer.toString(id)});

        return true;
    }

    public int numberOfUsers(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, USER_TABLE_NAME);
        return numRows;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res =  db.rawQuery( "select * from " + USER_TABLE_NAME + " where id= ?", new String[] { Integer.toString(id) } );
        return res;
    }

    

    public ArrayList<String> getAllUsers() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + USER_TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(USERS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }
}
