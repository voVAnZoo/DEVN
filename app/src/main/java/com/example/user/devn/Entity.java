package com.example.user.devn;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by user on 6/6/17.
 */

public class Entity {

    float mx;
    float my;
    float width;
    float height;
    int level;
    float hp;//не больше 100
    float speedX;
    float speedY;

    public Entity(float mx, float my, float width, float height, Context context) {
        this.mx = mx;
        this.my = my;
        this.width = width;
        this.height = height;
        this.hp = 100;
        this.level = 1;
        this.speedX = 0;
        this.speedY = 0;
    }

    public Entity(float mx, float my, float width, float height, int level, float hp, Context context) {
        this.mx = mx;
        this.my = my;
        this.width = width;
        this.height = height;
        this.hp = hp;
        this.level = level;
        this.speedX = 0;
        this.speedY = 0;
    }

    public float getMx() {
        return mx;
    }

    public float getMy() {
        return my;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public int getLevel() {
        return level;
    }

    public float getHp() {
        return hp;
    }

    public float getSpeedX() {
        return speedX;
    }

    public float getSpeedY() {
        return speedY;
    }



    public void setMx(float mx) {
        this.mx = mx;
    }

    public void setMy(float my) {
        this.my = my;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setHp(float hp) {
        this.hp = hp;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }


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
            //out.write("\n");
        }catch (IOException e){

        }
    }

    void onDraw(Canvas canvas, Paint paint) {}

    public void action(){}

}