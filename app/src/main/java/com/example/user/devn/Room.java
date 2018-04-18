package com.example.user.devn;


import java.util.Random;

public class Room {
    double height;
    double width;
    double x;
    double y;
    double h;
    double w;
    double u;
    double t;
    double r;
    double radius;
    double s=10;

    Random rnd=new Random(System.currentTimeMillis());


    public Room(int radius){
        while ((s<=0) ||(s>1)) {
            h = (-1000 + rnd.nextInt(2000)) / 1000;
            w = (-1000 + rnd.nextInt(2000)) / 1000;
            s=h*h+w*w;
        }
        height=Math.ceil(h*Math.sqrt(-2*Math.log(s)/s)*5);
        width=Math.ceil(w*Math.sqrt(-2*Math.log(s)/s)*5);
        t = 2*Math.PI*Math.random();
        u = Math.random()+Math.random();
        if (u > 1)
            r = 2-u;
        else
            r = u;
        x=radius*r*Math.cos(t);
        y=radius*r*Math.sin(t);
    }
}
