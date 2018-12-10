package com.example.kyles.myapplication;

public class UserModel {
    private int id;
    private String name;
    private int gold;
    private int christmas;
    private int christmasUnlocked;
    private int halloween;
    private int halloweenUnlocked;

    public UserModel(Integer i, String n, int g, int c, int cUnlocked, int h, int hUnlocked){
        id = i;
        name = n;
        gold = g;
        christmas = c;
        christmasUnlocked = cUnlocked;
        halloween = h;
        halloweenUnlocked = hUnlocked;
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

    public int isChristmasEquipped(){
        return christmas;
    }

    public void setChristmasEquipped(){
        if(christmas == 0)
            christmas = 1;
        else
            christmas = 0;
    }

    public int isChristmasUnlocked(){
        return christmasUnlocked;
    }

    public void setChristmasUnlocked(){
        christmasUnlocked = 1;
    }

    public void setHalloweenEquipped(){
        if(halloween == 0)
            halloween = 1;
        else
            halloween = 0;
    }

    public int isHalloweenEquipped(){
        return halloween;
    }

    public int isHalloweenUnlocked(){
        return halloweenUnlocked;
    }

    public void setHalloweenUnlocked(){
        halloweenUnlocked = 1;
    }
}
