package com.example.user.devn;


import java.util.Random;

public class Room {
    int height;
    int width;
    int x;
    int y;
    double radius;

    public Room(int x,int y,int height,int width, double radius){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.radius = radius;
    }
}
