package com.example.user.devn;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by user on 6/6/17.
 */

public class Entity {

    public float mx;
    public float my;
    public int width;
    public int height;
    public int level;
    public int defence;
    public int damage;
    public float hp;//не больше 100
    public float speedX;
    public float speedY;
    public GameMap gm;

    public Entity(float mx, float my, int width, int height, GameMap gm) {
        this.mx = mx;
        this.my = my;
        this.width = width;
        this.height = height;
        this.hp = 100;
        this.level = 1;
        this.defence=level*5;
        this.speedX = 0;
        this.speedY = 0;
        this.gm = gm;
    }

    public Entity(float mx, float my, int width, int height, int level, float hp, GameMap gm) {
        this.mx = mx;
        this.my = my;
        this.width = width;
        this.height = height;
        this.hp = hp;
        this.level = level;
        this.speedX = 0;
        this.speedY = 0;
        this.gm = gm;
    }

    public Entity(){}

    public void addMx(float dx){
        mx += dx;
    }

    public void addMy(float dy){
        my += dy;
    }

    public void addWidth(float dWidth){
        width += dWidth;
    }

    public void addHeight(float dHeight){
        height += dHeight;
    }

    public void addLevel(int dLevel){
        level += dLevel;
    }

    public void addHp(float dHp){
        hp += dHp;
    }

    public void addSpeedX(float dSpeedX){
        speedX += dSpeedX;
    }

    public void addSpeedY(float dSpeedY){
        speedY += dSpeedY;
    }

    public void save(FileWriter out){
        try {
            out.write(Float.toString(mx) + " ");
            out.write(Float.toString(my) + " ");
            out.write(Float.toString(width) + " ");
            out.write(Float.toString(height) + " ");
            out.write(Integer.toString(level) + " ");
            out.write(Float.toString(hp) + " ");
        }catch (IOException e){

        }
    }

    void onDraw(Canvas canvas, Paint paint) {}

    public void action(){}

    public void death(){
        gm.entitys.remove(this);
    }

}