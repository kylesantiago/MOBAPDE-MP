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

    public void setChristmasUnlocked(int c){
        christmas = c;
    }

    public int isChristmasUnlocked(){
        return christmas;
    }

    public void setHalloweenUnlocked(int h){
        halloween = h;
    }

    public int isHalloweenUnlocked(){
        return halloween;
    }

    public String getEquipped(){
        return equipped;
    }

    public void setEquipped(String e){
        equipped = e;
    }
}
