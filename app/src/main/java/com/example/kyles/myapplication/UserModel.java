package com.example.kyles.myapplication;

public class UserModel {
    private int id;
    private String name;
    private int gold;
    private int christmas;
    private int halloween;
    private String equipped;

    public UserModel(Integer i, String n, int g, int c, int h, String e){
        id = i;
        name = n;
        gold = g;
        christmas = c;
        halloween = h;
        equipped = e;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public int getGold(){
        return gold;
    }

    public void setGold(int g){
        gold = g;
    }

    public void setChristmasUnlocked(){
        christmas = 1;
    }

    public int isChristmasUnlocked(){
        return christmas;
    }

    public void setHalloweenUnlocked(){
        halloween = 1;
    }

    public int isHalloweenUnlocked(){
        return halloween;
    }

    public void setEquipped(String e){
        equipped = e;
    }
}
